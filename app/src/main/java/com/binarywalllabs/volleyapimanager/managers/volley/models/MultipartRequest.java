package com.binarywalllabs.volleyapimanager.managers.volley.models;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arun on 13-12-2015.
 */
public class MultipartRequest extends Request<String> {
    String PROTOCOL_CHARSET = "utf-8";
    private HttpEntity mHttpEntity;
    private Response.Listener mListener;
    private HashMap<String,String> headers;

    public MultipartRequest(String url, HashMap<String,String> postParameters , HashMap<String,String> filesParameters, HashMap<String,String> headers,
                            Response.Listener<String> listener,
                            Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.headers = headers;
        mListener = listener;
        mHttpEntity = buildMultipartEntity(postParameters, filesParameters);
    }

    private HttpEntity buildMultipartEntity(HashMap<String, String> postParameters, HashMap<String, String> filesParameters) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if(postParameters!=null) {
            for (Map.Entry<String, String> entry : postParameters.entrySet()) {
                builder.addTextBody(entry.getKey(), entry.getValue());
            }
        }

        if(filesParameters!=null) {
            for (Map.Entry<String, String> entry : filesParameters.entrySet()) {
                try {
                    File file = new File(entry.getValue());
                    FileBody fileBody = new FileBody(file , ContentType.create("image/*"), file.getName());
                    builder.addPart(entry.getKey(), fileBody);
                }
               catch (Exception e)
               {
                   Log.e(MultipartRequest.class.getSimpleName(), e.getMessage());
               }
            }
        }
        return builder.build();
    }

    @Override
    public String getBodyContentType() {
        return mHttpEntity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            mHttpEntity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(new String(response.data, "UTF-8"),
                    getCacheEntry());
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }
}
