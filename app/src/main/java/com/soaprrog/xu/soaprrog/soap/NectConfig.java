package com.soaprrog.xu.soaprrog.soap;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Administrator on 2018/2/2.
 */

public class NectConfig implements Interceptor {
    static NectConfig nectConfig;
    private static Gson gson;

    public static NectConfig newInstance() {
        if (nectConfig == null) {
            nectConfig = new NectConfig();
        }

        return nectConfig;
    }

    public static ApiService weatherInterfaceApi;

    private static Strategy strategy = new AnnotationStrategy();
    private static Serializer serializer = new Persister(strategy);

    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .baseUrl(Path.BASE_URL);

    public static Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder()
                    .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                    .create();
        return gson;
    }

    public <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = okHttpClient.connectTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new LogInterceptor())
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(this)
                .build();
        Retrofit retrofit = retrofitBuilder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public ApiService getInterfaceApi() {
        if (weatherInterfaceApi == null) {
            weatherInterfaceApi = createService(ApiService.class);
        }
        return weatherInterfaceApi;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("Content-Type", "text/xml;charset=UTF-8")
                .method(original.method(), original.body());
        Request request = requestBuilder.build();
        Response response = chain.proceed(request);
        okhttp3.MediaType mediaType = response.body().contentType();
        if ("text/xml;charset=ISO-8859-1".equals(mediaType.toString())) {
            String content = response.body().string();
            Log.e("拦截中response", content);
            String rebuildResult = content.substring(content.indexOf(">{") + 1, content.indexOf("</return>"));
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, rebuildResult))
                    .build();
        }
        return response;
    }
}
