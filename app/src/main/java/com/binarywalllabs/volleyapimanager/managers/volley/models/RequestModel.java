package com.binarywalllabs.volleyapimanager.managers.volley.models;

import android.app.DownloadManager;

import com.android.volley.Request;
import com.binarywalllabs.volleyapimanager.managers.volley.VolleyCallback;

import java.util.HashMap;

/**
 * Created by Arun on 22-11-2015.
 */
public class RequestModel {
    public String BASE_URL;
    public String WEBSERVICE_NAME;
    public VolleyCallback volleyCallback;
    public HashMap<String,String> postParameters;
    public HashMap<String,String> filesParameters;
    public HashMap<String,String> headers;
    public String responseData;
    public String errorData;
    public RequestType method;

    public enum RequestType{
        METHOD_TYPE_POST,
        METHOD_TYPE_GET,
        METHOD_TYPE_MULTIPART_POST
    }

}
