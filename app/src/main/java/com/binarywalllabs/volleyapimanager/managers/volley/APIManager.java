package com.binarywalllabs.volleyapimanager.managers.volley;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.binarywalllabs.volleyapimanager.managers.volley.models.CustomJsonObjectRequest;
import com.binarywalllabs.volleyapimanager.managers.volley.models.MultipartRequest;
import com.binarywalllabs.volleyapimanager.managers.volley.models.RequestModel;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Arun on 22-11-2015.
 */
public class APIManager {
    private RequestQueue queue;
    private Context context;
    public APIManager(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public void callWebservice(RequestModel requestModel, VolleyCallback callback)
    {
        String completeUrl = createWebserviceUri(requestModel);
        requestModel.volleyCallback = callback;
        switch (requestModel.method)
        {
            case METHOD_TYPE_POST:

                postData(completeUrl, requestModel);
                break;

            case METHOD_TYPE_GET:
                getData(completeUrl, requestModel);
                break;

            case METHOD_TYPE_MULTIPART_POST:
                postMultipartData(completeUrl, requestModel);
                break;
        }
    }

    private String createWebserviceUri(RequestModel requestModel) {
        String url = requestModel.BASE_URL + requestModel.WEBSERVICE_NAME;
        return url;
    }

    //method post to server
    public void postData(String url, final RequestModel requestModel) {

        Gson gson = new Gson();
        String result = gson.toJson(requestModel.postParameters);
        CustomJsonObjectRequest jsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST,url, result , requestModel.headers,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(requestModel.volleyCallback!=null)
                            requestModel.volleyCallback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(requestModel.volleyCallback!=null)
                    requestModel.volleyCallback.onError(error.getMessage());
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(jsonObjectRequest);
    }

    private void postMultipartData(String completeUrl, final RequestModel requestModel) {
        MultipartRequest multipartRequest = new MultipartRequest(completeUrl, requestModel.postParameters , requestModel.filesParameters, requestModel.headers,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if(requestModel.volleyCallback!=null)
                            requestModel.volleyCallback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(requestModel.volleyCallback!=null)
                    requestModel.volleyCallback.onError(error.getMessage());
            }
        });

        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(multipartRequest);
    }


    //get data method
    public void getData(String url, final RequestModel requestModel) {

        CustomJsonObjectRequest jsonObjectRequest = new CustomJsonObjectRequest(Request.Method.GET, url, null , requestModel.headers,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(requestModel.volleyCallback!=null)
                            requestModel.volleyCallback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(requestModel.volleyCallback!=null)
                    requestModel.volleyCallback.onError(error.getMessage());
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(jsonObjectRequest);
    }
}
