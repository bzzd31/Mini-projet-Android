package com.m2dl.mini_projet.mini_projet_android.photos.model;

import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;
import com.m2dl.mini_projet.mini_projet_android.photos.Constants;

import java.util.Date;
import java.util.List;

/**
 * Created by kelto on 25/01/16.
 */
public class Photo {

    public String id;
    public double coordLat;
    public double coordLong;
    public String author;
    public Date date;
    public String tags;

    public String getUrl() {
        return Constants.CLOUDINARY_URL_IMAGE + "/" + id + Constants.IMG_EXTENSION;
    }
}
