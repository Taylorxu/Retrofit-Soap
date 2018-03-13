package com.soaprrog.xu.soaprrog.soap.request;

import android.os.Bundle;

import com.soaprrog.xu.soaprrog.soap.beans.LoginBean;
import com.soaprrog.xu.soaprrog.soap.beans.MobileLogin;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Root;

/**
 * Created by xuzhiguang on 2018/2/1.
 */
@Root(name = "v:Body")
@Default(DefaultType.FIELD)
public class RequestBody<Data> {

    public RequestBody(Data requestModel) {
        this.requestModel = requestModel;
    }

    @ElementUnion({
            @Element(name = "n0:mobileLogin", type = MobileLogin.class),
            @Element(name = "mobileLogin1", type = LoginBean.class),
    })
    public Data requestModel;

}
