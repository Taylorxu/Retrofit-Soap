package com.soaprrog.xu.soaprrog.soap.response;

import com.soaprrog.xu.soaprrog.soap.beans.LoginBean;
import com.soaprrog.xu.soaprrog.soap.beans.ResultModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/3/13.
 */

public class FlatMapResultModel<Data> implements Function<ResultModel<Data>, ObservableSource<Data>> {
    @Override
    public ObservableSource<Data> apply(ResultModel<Data> dataResultModel) throws Exception {
        if (dataResultModel.getReturnState().equals("0")) {
            return Observable.just(dataResultModel.getReturnValue());
        } else {
            return Observable.error(dataResultModel.getError());
        }
    }
}
