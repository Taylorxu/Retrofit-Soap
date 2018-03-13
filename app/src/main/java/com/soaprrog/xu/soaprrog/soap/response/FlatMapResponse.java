package com.soaprrog.xu.soaprrog.soap.response;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class FlatMapResponse<Data> implements Function<Response<Data>, ObservableSource<Data>> {
    @Override
    public ObservableSource<Data> apply(Response<Data> response) throws Exception {
        if (response.isSuccessful()) {
            return Observable.just(response.body());
        } else {
            try {
                return Observable.error(new Error(response.code(), response.errorBody().string()));
            } catch (IOException e) {
                return Observable.error(new Error(response.code(), e.getMessage()));
            }
        }
    }
}
