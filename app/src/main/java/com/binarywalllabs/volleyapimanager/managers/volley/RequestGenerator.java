package com.binarywalllabs.volleyapimanager.managers.volley;

import com.binarywalllabs.volleyapimanager.constants.Constants;
import com.binarywalllabs.volleyapimanager.models.ApiModel;
import com.binarywalllabs.volleyapimanager.managers.volley.models.RequestModel;

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

        //post parameters
        HashMap<String,String> postParameters = new HashMap<>();
        postParameters.put("name", apiModel.postModel.name);
        postParameters.put("body", apiModel.postModel.name);
        postParameters.put("type", apiModel.postModel.name);
        postParameters.put("reg_id", apiModel.postModel.regId);
        requestModel.postParameters = postParameters;

        return requestModel;
    }

    public static RequestModel getRequestModelForGetPosts(ApiModel apiModel) {
        RequestModel requestModel = new RequestModel();
        requestModel.BASE_URL = Constants.BASE_URL;
        requestModel.WEBSERVICE_NAME = Constants.WEB_SERVICE_POST;
        requestModel.method = RequestModel.RequestType.METHOD_TYPE_GET;
        requestModel.headers = getHeaders();

        return requestModel;
    }

    public static RequestModel getRequestModelForUploadPhoto(ApiModel apiModel) {
        RequestModel requestModel = new RequestModel();
        requestModel.BASE_URL = Constants.BASE_URL;
        requestModel.WEBSERVICE_NAME = Constants.WEB_SERVICE_UPLOAD_PHOTO;
        requestModel.method = RequestModel.RequestType.METHOD_TYPE_MULTIPART_POST;
        requestModel.headers = getHeaders();
        requestModel.filesParameters = new HashMap<>();
        requestModel.filesParameters.put(apiModel.mediaModel.mediaName,apiModel.mediaModel.filePath);

        HashMap<String,String> headers = new HashMap<>();
        headers.put("X-Authorization","356b1e90e9cc539bd710873469b9d3e6");
        requestModel.headers = headers;

        return requestModel;
    }

    private static HashMap<String, String> getHeaders()
    {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json; charset=utf-8"); //content type for json request body
        headers.put("X-Authorization","356b1e90e9cc539bd710873469b9d3e6"); //sample authorization key for rest api
        return headers;
    }
}
