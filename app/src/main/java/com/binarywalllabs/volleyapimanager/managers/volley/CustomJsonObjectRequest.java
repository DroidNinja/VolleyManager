package com.binarywalllabs.volleyapimanager.managers.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Arun on 23-11-2015.
 */
public class CustomJsonObjectRequest extends JsonObjectRequest
{
    Map<String,String> headers;
    public CustomJsonObjectRequest(int method, String url, String requestBody, Map<String,String> headers, Response.Listener listener, Response.ErrorListener errorListener)
    {
        super(method, url, requestBody, listener, errorListener);
        this.headers = headers;
    }

    @Override
    public Map getHeaders() throws AuthFailureError {
       return headers;
    }

}
