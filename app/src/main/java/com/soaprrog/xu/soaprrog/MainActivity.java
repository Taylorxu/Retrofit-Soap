package com.soaprrog.xu.soaprrog;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soaprrog.xu.soaprrog.databinding.ActivityMainBinding;
import com.soaprrog.xu.soaprrog.soap.NectConfig;
import com.soaprrog.xu.soaprrog.soap.Path;
import com.soaprrog.xu.soaprrog.soap.beans.LoginBean;
import com.soaprrog.xu.soaprrog.soap.beans.MobileLogin;
import com.soaprrog.xu.soaprrog.soap.beans.ResultModel;
import com.soaprrog.xu.soaprrog.soap.request.RequestBody;
import com.soaprrog.xu.soaprrog.soap.request.RequestEnvelope;
import com.soaprrog.xu.soaprrog.soap.request.RequestModel;
import com.soaprrog.xu.soaprrog.soap.response.ResponseEnvelope;
import com.soaprrog.xu.soaprrog.soap.response.UnderBodyModel;

import org.simpleframework.xml.Element;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    /*public void sing(View view) {

               RequestModel.getRequestModel().userName="itil";
               RequestModel.getRequestModel().userPassward="1";
               RequestBody.getRequestBody().setMobileLogin(RequestModel.getRequestModel());
               RequestEnvelope.getRequestEnvelope().setBody(RequestBody.getRequestBody());
               Call<ResponseEnvelope> call= NectConfig.getInterfaceApi().login(RequestEnvelope.getRequestEnvelope());
               call.enqueue(new Callback<ResponseEnvelope>() {
                   @Override
                   public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {
                       ResultModel resultModel=new Gson().fromJson(response.body().getResponseBody().getUnderBodyModel().result,ResultModel.class);
                       if (null!=resultModel ) {
                           LoginBean loginBean=new Gson().fromJson(resultModel.getReturnValue().toString(),new TypeToken<LoginBean>(){}.getType());
                           mainBinding.showResult.setText("userID:"+loginBean.getUserId());
                       }
                   }

                   @Override
                   public void onFailure(Call<ResponseEnvelope> call, Throwable t) {
                            Log.e("报错",t.getMessage());
                   }
               });

           }*/


    public void sing(View view) {
        MobileLogin mobileLogin = MobileLogin.newInstance();
        mobileLogin.setUserName("itil");
        mobileLogin.setUserPassward("1");
        mobileLogin.setNameSpace(Path.NAMESPACE);

        RequestEnvelope.getRequestEnvelope().setBody(new RequestBody<>(mobileLogin));
        NectConfig.newInstance().getInterfaceApi().login(RequestEnvelope.getRequestEnvelope())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Response<ResultModel<LoginBean>>, ObservableSource<ResultModel<LoginBean>>>() {
                    @Override
                    public ObservableSource<ResultModel<LoginBean>> apply(Response<ResultModel<LoginBean>> resultModelResponse) throws Exception {
                        return io.reactivex.Observable.just(resultModelResponse.body());
                    }
                })
                .flatMap(new Function<ResultModel<LoginBean>, ObservableSource<LoginBean>>() {
                    @Override
                    public ObservableSource<LoginBean> apply(ResultModel<LoginBean> loginBeanResultModel) throws Exception {
                        return io.reactivex.Observable.just(loginBeanResultModel.getReturnValue());
                    }
                })
                .subscribe(new Subject<LoginBean>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super LoginBean> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
                        mainBinding.showResult.setText("userID:" + value.getUserId());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}


