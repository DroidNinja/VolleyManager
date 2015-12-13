package com.binarywalllabs.volleyapimanager.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arun on 22-11-2015.
 */
public class PostModel {
    public String name;
    public String body;
    public String type;
    @SerializedName("reg_id")
    public String regId;
}
