package com.m2dl.mini_projet.mini_projet_android.photos;

import com.m2dl.mini_projet.mini_projet_android.photos.model.Photo;
import com.m2dl.mini_projet.mini_projet_android.photos.model.PhotoList;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by kelto on 24/01/16.
 */
public interface SimpleImageTag {
    @GET("/images")
    PhotoList getPhotos();

    @POST("/images")
    void postPhoto(
            @Body Photo photo
    );

    @GET("/images")
    void getAsyncPhotos(Callback<List<Photo>> callback);

    @POST("/images")
    void postAsyncPhoto(
            @Body Photo photo,
            Callback<Photo> callback
    );

}
