package com.example.companyservice.controller;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Response implements Serializable {
    int code;
    String msg;
    Object data;
 
    public Response() {
    }
 
    public Response(ResponseCode tmResponseCode, Object data) {
        this.code = tmResponseCode.getCode();
        this.msg = tmResponseCode.getMsg();
        this.data = data;
    }
 
    public Response ResponseSucess() {
        Response response = new Response();
        response.code = ResponseCode.SUCCESS.getCode();
        response.msg = ResponseCode.SUCCESS.getMsg();
        return response;
    }
 
    public Response ResponseError() {
        Response response = new Response();
        response.code = ResponseCode.ERROR.getCode();
        response.msg = ResponseCode.ERROR.getMsg();
        return response;
    }
 
    public Response ResponseErrorData(Object data) {
        return new Response(ResponseCode.ERROR, data);
    }
 
    public Response ResponseSucessData(Object data) {
        return new Response(ResponseCode.SUCCESS, data);
    }
    
    public int getCode() {
    	return this.code;
    }
    
    public String getMsg() {
    	return this.msg;
    	
    }
}
