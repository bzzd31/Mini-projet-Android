package com.m2dl.mini_projet.mini_projet_android.provider;

import android.graphics.Bitmap;

import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhotoProviderMock implements IPhotoProvider {

    private List<Photo> photoList;

    public PhotoProviderMock() {
        photoList = new ArrayList<>();

        photoList.add(new Photo(null, "author1", 43.55780, 1.46934, new Date(), "http://i.imgur.com/2kr3TIL.jpg"));
        photoList.add(new Photo(null, "author2", 43.56406, 1.46625, new Date(), "http://i.imgur.com/2kr3TIL.jpg"));
        photoList.add(new Photo(null, "author3", 43.56674, 1.46968, new Date(), "http://i.imgur.com/2kr3TIL.jpg"));
        photoList.add(new Photo(null, "author4", 43.56472, 1.46617, new Date(), "http://i.imgur.com/2kr3TIL.jpg"));
    }

    @Override
    public List<Photo> getPhotos() {
        return photoList;
    }

    @Override
    public String post(Bitmap photo, String author, Date date, double coordLat, double coordLong, List<Tag> tags) {
        return "http://i.imgur.com/2kr3TIL.jpg";
    }
}
