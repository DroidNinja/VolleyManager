package com.binarywalllabs.volleyapimanager;

import android.app.Application;

import com.binarywalllabs.volleyapimanager.managers.volley.WebServiceManager;

/**
 * Created by Arun on 22-11-2015.
 */
public class AppDelegate extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        initializeManagers();
    }

    private void initializeManagers() {
        WebServiceManager.getInstance().initializeAPIManager(this);
    }
}
