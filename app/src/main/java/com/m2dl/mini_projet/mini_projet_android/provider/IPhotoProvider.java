package com.m2dl.mini_projet.mini_projet_android.provider;

import android.graphics.Bitmap;

import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.Date;
import java.util.List;

public interface IPhotoProvider {

    List<Photo> getPhotos(); //donner un URL à chaque photo renvoyé du serveur

    String post(Bitmap photo, String author, Date date, double coordLat, double coordLong, String tags, String imageFilePath); //un post retourne l'URL de la photo
}
