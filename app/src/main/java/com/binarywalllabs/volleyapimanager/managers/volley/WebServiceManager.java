package com.binarywalllabs.volleyapimanager.managers.volley;

import android.content.Context;
import android.util.Log;

import com.binarywalllabs.volleyapimanager.models.ApiModel;
import com.binarywalllabs.volleyapimanager.managers.volley.models.RequestModel;

/**
 * Created by Arun on 22-11-2015.
 */
public class WebServiceManager {

        private static WebServiceManager ourInstance;
    private APIManager apiManager;

    public static WebServiceManager getInstance() {
            if(ourInstance==null)
                ourInstance = new WebServiceManager();
            return ourInstance;
        }

        private WebServiceManager() {
        }

        public void initializeAPIManager(Context context)
        {
            apiManager = new APIManager(context);
        }

        public void onCreatePost(ApiModel apiModel)
        {
            RequestModel requestModel = RequestGenerator.getRequestModelForLogin(apiModel);
            apiManager.callWebservice(requestModel, new VolleyCallback() {
                @Override
                public void onSuccess(String response) {
                    Log.v(WebServiceManager.class.getSimpleName(), response);
                }

                @Override
                public void onError(String error) {
                    Log.v(WebServiceManager.class.getSimpleName(), error);
                }
            });
        }

    public void onGetPosts(ApiModel apiModel)
    {
        RequestModel requestModel = RequestGenerator.getRequestModelForGetPosts(apiModel);
        apiManager.callWebservice(requestModel, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.v(WebServiceManager.class.getSimpleName(), response);
            }

            @Override
            public void onError(String error) {
                Log.v(WebServiceManager.class.getSimpleName(), error);
            }
        });
    }

    public void onUploadPhoto(ApiModel apiModel)
    {
        RequestModel requestModel = RequestGenerator.getRequestModelForUploadPhoto(apiModel);
        apiManager.callWebservice(requestModel, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.v(WebServiceManager.class.getSimpleName(), response);
            }

            @Override
            public void onError(String error) {
                Log.v(WebServiceManager.class.getSimpleName(), error);
            }
        });
    }
}
