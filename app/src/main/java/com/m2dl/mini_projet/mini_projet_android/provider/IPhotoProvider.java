package com.m2dl.mini_projet.mini_projet_android.provider;

import android.graphics.Bitmap;

import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.List;

public interface IPhotoProvider {

    List<Photo> getPhotos();

    Photo post(Bitmap photo, String author, double coordLat, double coordLong, List<Tag> tags);
}
