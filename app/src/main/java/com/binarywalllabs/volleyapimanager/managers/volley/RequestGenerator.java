package com.binarywalllabs.volleyapimanager.managers.volley;

import com.binarywalllabs.volleyapimanager.constants.Constants;

import java.util.HashMap;

/**
 * Created by Arun on 22-11-2015.
 */
public class RequestGenerator {


    public static RequestModel getRequestModelForLogin(ApiModel apiModel) {
        RequestModel requestModel = new RequestModel();
        requestModel.BASE_URL = Constants.BASE_URL;
        requestModel.WEBSERVICE_NAME = Constants.WEB_SERVICE_POST;
        requestModel.method = RequestModel.RequestType.METHOD_TYPE_POST;
        requestModel.headers = getHeaders();

        HashMap<String,String> postParameters = new HashMap<>();
        postParameters.put("name", apiModel.postModel.name);
        postParameters.put("body", apiModel.postModel.name);
        postParameters.put("type", apiModel.postModel.name);
        postParameters.put("reg_id", apiModel.postModel.regId);
        requestModel.postParameters = postParameters;

        return requestModel;
    }

    private static HashMap<String, String> getHeaders()
    {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("X-Authorization","356b1e90e9cc539bd710873469b9d3e6");
        return headers;
    }
}
