package com.m2dl.mini_projet.mini_projet_android.photos.model;

import com.m2dl.mini_projet.mini_projet_android.photos.Constants;

import java.util.Date;

/**
 * Created by kelto on 25/01/16.
 */
public class Image {

    private String id;
    private double coordLat;
    private double coordLong;
    private String author;
    private Date date;
    private String tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(double coordLat) {
        this.coordLat = coordLat;
    }

    public double getCoordLong() {
        return coordLong;
    }

    public void setCoordLong(double coordLong) {
        this.coordLong = coordLong;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String url() {
        return Constants.CLOUDINARY_URL_IMAGE + "/" + id + Constants.IMG_EXTENSION;
    }
}
