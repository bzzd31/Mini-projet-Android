package com.m2dl.mini_projet.mini_projet_android.provider;

import android.graphics.Bitmap;

import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;
import com.m2dl.mini_projet.mini_projet_android.photos.ServiceGenerator;
import com.m2dl.mini_projet.mini_projet_android.photos.SimpleImageTag;
import com.m2dl.mini_projet.mini_projet_android.photos.model.PhotoList;
import com.m2dl.mini_projet.mini_projet_android.photos.storage.CloudinaryHelper;
import com.m2dl.mini_projet.mini_projet_android.provider.IPhotoProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kelto on 25/01/16.
 */
public class PhotoProvider implements IPhotoProvider {

    @Override
    public List<Photo> getPhotos() {
        PhotoList photoList = ServiceGenerator.createService(SimpleImageTag.class).getPhotos();
        List<Photo> photos = new ArrayList<>();
        for(com.m2dl.mini_projet.mini_projet_android.photos.model.Photo p : photoList.photos) {
            Photo photo = new Photo(null, p.author, p.coordLat, p.coordLong, p.date, p.getUrl());
            photo.setTag(p.tags);
            String[] myTags = p.tags.split(",");
            for (String tag: myTags) {
                Tag myTag = new Tag(tag.replaceAll("\\s", ""));
                photo.putTag(myTag);
            }
        }

        return photos;
    }


    @Override
    public String post(Bitmap photo, String author, Date date, double coordLat, double coordLong, String tags) {
        //photo should be a File or an Inputstream. Can transform, but not necessary
        String id = CloudinaryHelper.upload(null);
        com.m2dl.mini_projet.mini_projet_android.photos.model.Photo p = new com.m2dl.mini_projet.mini_projet_android.photos.model.Photo();
        p.id = id;
        p.date = new Date();
        p.coordLat = coordLat;
        p.coordLong = coordLong;
        p.tags = tags;
        ServiceGenerator.createService(SimpleImageTag.class).postPhoto(p);
        return p.getUrl();
    }
}
