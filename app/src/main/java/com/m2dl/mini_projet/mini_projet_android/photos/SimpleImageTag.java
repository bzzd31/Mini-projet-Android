package com.m2dl.mini_projet.mini_projet_android.photos;

import com.m2dl.mini_projet.mini_projet_android.photos.model.Image;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by kelto on 24/01/16.
 */
public interface SimpleImageTag {

    @GET("/images")
    void getImages(Callback<List<Image>> callback);

    @POST("/images")
    void postImage(
            @Body Image image,
            Callback<Image> callback
    );

}
