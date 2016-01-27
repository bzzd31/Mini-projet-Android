package com.m2dl.mini_projet.mini_projet_android.photos.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.m2dl.mini_projet.mini_projet_android.photos.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by kelto on 25/01/16.
 */
public class CloudinaryHelper {
    public static Cloudinary cloudinary = new Cloudinary(Constants.CLOUDINARY_URL);

    public static String upload(InputStream inputStream) {
        try {


            Map<String,String> result = cloudinary.uploader().upload(inputStream, ObjectUtils.asMap("sslmode", "require"));
            return result.get("public_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
