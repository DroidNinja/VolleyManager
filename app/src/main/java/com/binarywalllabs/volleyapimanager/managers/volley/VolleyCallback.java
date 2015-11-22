package com.binarywalllabs.volleyapimanager.managers.volley;

import org.json.JSONObject;

/**
 * Created by Arun on 22-11-2015.
 */
public interface VolleyCallback {
    void onSuccess(String response);
    void onError(String error);
}
