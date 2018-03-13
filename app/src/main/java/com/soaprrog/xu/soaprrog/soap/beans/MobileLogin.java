package com.soaprrog.xu.soaprrog.soap.beans;

import android.os.Bundle;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * Created by Administrator on 2018/3/13.
 */

@Root
public class MobileLogin {
    public static MobileLogin newInstance() {
        MobileLogin fragment = new MobileLogin();
        return fragment;
    }


    @Attribute(name = "xmlns:n0")
    String nameSpace;

    @Element(name = "arg0")
    public String userName;
    @Element(name = "arg1")
    public String userPassward;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassward() {
        return userPassward;
    }

    public void setUserPassward(String userPassward) {
        this.userPassward = userPassward;
    }
}
