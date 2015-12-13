# Volley API Manager

This repo shows the basic structure to consume rest api using volley library. This shows handling of all basic requests i.e. POST, GET, MULTIPART POST
I have included examples of all types of requests. You can use observer notifications or can use third party library such as event bus to raise notification for calling web services.
(Here I have called directly).

[ApiModel](https://github.com/DroidNinja/VolleyManager/blob/master/app/src/main/java/com/binarywalllabs/volleyapimanager/models/ApiModel.java) - This model contains all variables which contains values for the request parameters.

[WebServiceManager](https://github.com/DroidNinja/VolleyManager/blob/master/app/src/main/java/com/binarywalllabs/volleyapimanager/managers/volley/WebServiceManager.java) - This is responsible for handling all the REST api calls.

[RequestGenerator](https://github.com/DroidNinja/VolleyManager/blob/master/app/src/main/java/com/binarywalllabs/volleyapimanager/managers/volley/RequestGenerator.java) - This class is responsible for making request model that specifies what type of request it is, which web service we should call and what parameters we should pass.

### Dependencies Used:
   * compile 'com.mcxiaoke.volley:library:1.0.19' : **Gradle dependency for volley library**
    * compile 'com.google.code.gson:gson:2.4' : **GSON used for serialization and deserialization of objects**

   **Below dependencies are used for supporting multipart request as all apache stuff has been deprecated in Android 6.0.** [See this](http://developer.android.com/intl/zh-cn/about/versions/marshmallow/android-6.0-changes.html#behavior-apache-http-client)
> *compile 'org.apache.httpcomponents:httpcore:4.2.4'*   
> *compile 'org.apache.httpcomponents:httpmime:4.3.1'*
    
    
