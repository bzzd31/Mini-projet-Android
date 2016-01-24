package com.m2dl.mini_projet.mini_projet_android.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtil {

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }

    public static Bitmap resize(Bitmap bMap) {
        if ((float) bMap.getHeight() / (float) bMap.getWidth() == .5625 ||
                (float) bMap.getWidth() / (float) bMap.getHeight() == .5625) { //16/9
            if (bMap.getHeight() < bMap.getWidth()) {
                //bMap = Bitmap.createScaledBitmap(bMap, 1024, 576, false);
                bMap = Bitmap.createScaledBitmap(bMap, 1280, 720, false); //720p
            } else {
                //bMap = Bitmap.createScaledBitmap(bMap, 576, 1024, false);
                bMap = Bitmap.createScaledBitmap(bMap, 720, 1280, false); //720p
            }
        } else if ((float) bMap.getHeight() / (float) bMap.getWidth() == .625 ||
                (float) bMap.getWidth() / (float) bMap.getHeight() == .625) { //16/10
            if (bMap.getHeight() < bMap.getWidth()) {
                //bMap = Bitmap.createScaledBitmap(bMap, 960, 600, false);
                bMap = Bitmap.createScaledBitmap(bMap, 1152, 720, false); //720p
            } else {
                //bMap = Bitmap.createScaledBitmap(bMap, 600, 960, false);
                bMap = Bitmap.createScaledBitmap(bMap, 720, 1152, false); //720p
            }
        } else if ((float) bMap.getHeight() / (float) bMap.getWidth() == .75 ||
                (float) bMap.getWidth() / (float) bMap.getHeight() == .75) { //4/3
            if (bMap.getHeight() < bMap.getWidth()) {
                bMap = Bitmap.createScaledBitmap(bMap, 960, 720, false);
            } else {
                bMap = Bitmap.createScaledBitmap(bMap, 720, 960, false);
            }
        }
        return bMap;
    }
}
