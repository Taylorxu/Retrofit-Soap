package com.soaprrog.xu.soaprrog.soap;

import com.soaprrog.xu.soaprrog.soap.beans.LoginBean;
import com.soaprrog.xu.soaprrog.soap.beans.ResultModel;
import com.soaprrog.xu.soaprrog.soap.request.RequestEnvelope;


import retrofit2.Response;
import retrofit2.http.Body;
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
