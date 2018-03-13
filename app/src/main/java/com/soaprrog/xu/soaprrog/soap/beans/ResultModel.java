package com.soaprrog.xu.soaprrog.soap.beans;

import android.util.Log;

import java.io.Serializable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;

/**
 * Created by xu on 2018/2/5.
 */

public class ResultModel<Data>   {
    private String returnMsg;
    private Data returnValue;
    private String returnState;

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }


    public String getReturnState() {
        return returnState;
    }

    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }


    public Data getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Data returnValue) {
        this.returnValue = returnValue;
    }

    public Error getError() {
        return new Error(returnMsg);
    }

}
