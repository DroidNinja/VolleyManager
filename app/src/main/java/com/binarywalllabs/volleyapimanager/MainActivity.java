package com.binarywalllabs.volleyapimanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.binarywalllabs.volleyapimanager.models.ApiModel;
import com.binarywalllabs.volleyapimanager.models.MediaModel;
import com.binarywalllabs.volleyapimanager.models.PostModel;
import com.binarywalllabs.volleyapimanager.managers.volley.WebServiceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPostRequest(View view) {
        ApiModel apiModel = new ApiModel();

        //This is sample post
        apiModel.postModel = new PostModel();
        apiModel.postModel.name = "This is title";
        apiModel.postModel.body = "This is body";
        apiModel.postModel.type = "text";
        apiModel.postModel.regId = "This is registration token";
        WebServiceManager.getInstance().onCreatePost(apiModel);
    }

    public void onGetRequest(View view) {
        ApiModel apiModel = new ApiModel();
        WebServiceManager.getInstance().onGetPosts(apiModel);
    }

    public void onMultiPartRequest(View view) {
        ApiModel apiModel = new ApiModel();
        apiModel.mediaModel = new MediaModel();
        apiModel.mediaModel.mediaName = "photo";
        apiModel.mediaModel.filePath = "/storage/sdcard1/Backgrounds/Backgrounds_24554.png";
        WebServiceManager.getInstance().onUploadPhoto(apiModel);
    }
}
