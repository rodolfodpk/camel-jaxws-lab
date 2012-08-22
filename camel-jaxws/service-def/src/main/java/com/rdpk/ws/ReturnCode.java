package com.rdpk.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public enum ReturnCode {
    OK(0,"Ok"),
    ERROR_VALIDATION(1,"Validation Error"),
    ERROR_TRANSPORT(2, "Transport Error"),
    ERROR_CASE_01(101, "Business situation #01"),
    ERROR_CASE_02(102, "Business situation #02"),
    ERROR_CASE_03(103, "Business situation #03");

    @XmlElement(nillable=false, required=true)
    private Integer code = 0;

    @XmlElement(nillable=false, required=true)
    private String message = null;

    private ReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
