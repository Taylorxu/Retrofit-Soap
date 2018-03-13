package com.soaprrog.xu.soaprrog.soap;

import com.soaprrog.xu.soaprrog.soap.beans.LoginBean;
import com.soaprrog.xu.soaprrog.soap.beans.ResultModel;
import com.soaprrog.xu.soaprrog.soap.request.RequestEnvelope;
import com.soaprrog.xu.soaprrog.soap.response.ResponseEnvelope;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;


import java.util.Observable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * soap 的请求接口发起
 * Created by xuzhiguang on 2018/2/1.
 */

public interface ApiService {
    @Headers({
            "Content-Type: text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("YxywService?wsdl")
//    Call<ResponseEnvelope> login(@Body RequestEnvelope requestEnvelope);
    io.reactivex.Observable<Response<ResultModel<LoginBean>>> login(@Body RequestEnvelope requestEnvelope);




}
