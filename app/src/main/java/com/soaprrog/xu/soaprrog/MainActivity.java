package com.soaprrog.xu.soaprrog;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soaprrog.xu.soaprrog.databinding.ActivityMainBinding;
import com.soaprrog.xu.soaprrog.soap.NectConfig;
import com.soaprrog.xu.soaprrog.soap.beans.LoginBean;
import com.soaprrog.xu.soaprrog.soap.beans.ResultModel;
import com.soaprrog.xu.soaprrog.soap.request.RequestBody;
import com.soaprrog.xu.soaprrog.soap.request.RequestEnvelope;
import com.soaprrog.xu.soaprrog.soap.request.RequestModel;
import com.soaprrog.xu.soaprrog.soap.response.ResponseEnvelope;
import com.soaprrog.xu.soaprrog.soap.response.UnderBodyModel;

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

    public void sing(View view) {

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

                   }
               });

           }


    }


