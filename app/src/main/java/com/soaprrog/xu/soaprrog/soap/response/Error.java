package com.soaprrog.xu.soaprrog.soap.response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class Error extends Throwable {

    private int code;

    public Error(int theCode) {
        super();
        this.code = theCode;
    }

    public Error(int theCode, String theMessage) {
        super(theMessage);
        this.code = theCode;
    }

    public int getCode() {
        return this.code;
    }

    public static Error get(Throwable e) {
        if (e instanceof Error) return (Error) e;
        return new Error(0, e.getMessage());
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                '}';
    }
}
