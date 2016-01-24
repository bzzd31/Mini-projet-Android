package com.m2dl.mini_projet.mini_projet_android.provider;

import android.graphics.Bitmap;

import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.Date;
import java.util.List;

/**
 * Created by Lucas-PCP on 24/01/2016.
 */
public class PhotoProviderMock implements IPhotoProvider {
    @Override
    public List<Photo> getPhotos() {
        return null;
    }

    @Override
    public String post(Bitmap photo, String author, Date date, double coordLat, double coordLong, List<Tag> tags) {
        return "http://i.imgur.com/2kr3TIL.jpg";
    }
}
