/* This file is part of UPS-Caring.

    Copyright 2016 Charles Fallourd <charles.fallourd@master-developpement-logiciel.fr>
    Copyright 2016 Lucas Bled <lucas.bled@master-developpement-logiciel.fr>
    Copyright 2016 Théo Vaucher <theo.vaucher@master-developpement-logiciel.fr>

    UPS-Caring is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UPS-Caring is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UPS-Caring.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.m2dl.mini_projet.mini_projet_android.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

    public static void loadBitmap(ImageView iv, ProgressBar pb, String src) {
        new DownloadImageTask(iv, pb).execute(src);
    }
}
