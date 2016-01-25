package com.m2dl.mini_projet.mini_projet_android.photos.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by kelto on 25/01/16.
 */
public class CloudinaryHelper {
    public static Cloudinary cloudinary = new Cloudinary("cloudinary://956147132177299:GQYtmkrLTROvBd-vTzsAX_Kbf1o@drmzt362b");

    public static String upload(InputStream inputStream) {
        try {


            Map<String,String> result = cloudinary.uploader().upload(inputStream, ObjectUtils.emptyMap());
            return result.get("public_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
