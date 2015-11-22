package com.binarywalllabs.volleyapimanager.managers.volley;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
                break;
        }
    }

    private String createWebserviceUri(RequestModel requestModel) {
        String url = requestModel.BASE_URL + requestModel.WEBSERVICE_NAME;
        return url;
    }

    //method post to server
    public void postData(String url, final RequestModel requestModel) {

//        final ProgressDialog pDialog = new ProgressDialog(context);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                return requestModel.postParameters;
//            }
//
//
//        };
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


    //get data method
    public void getData(String url, final VolleyCallback callback) {

//        final ProgressDialog pDialog = new ProgressDialog(context); // прогрес бар
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        JsonObjectRequest getData = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                pDialog.hide(); //скрываем прогресс бар
//                callback.onSuccess(response); // ответ помещаем в интерфейс
//            }
//
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                pDialog.hide(); //скрываем прогресс бар
//                callback.onError(error.getMessage()); // ответ помещаем в интерфейс
//            }
//        });
//
//        getData.setRetryPolicy(new DefaultRetryPolicy(5000, //ставим 5 секунт тайм аут
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        queue.add(getData); // добавляем наш postData в RequestQueue
    }
}
