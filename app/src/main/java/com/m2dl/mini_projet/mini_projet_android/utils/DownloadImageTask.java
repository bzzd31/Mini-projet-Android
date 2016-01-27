/* This file is part of UPS-Caring.

    Copyright 2016 Charles Falloud <charles.fallourd@master-developpement-logiciel.fr>
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
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView bmImage;
    private ProgressBar pb;

    public DownloadImageTask(ImageView bmImage, ProgressBar pb) {
        this.bmImage = bmImage;
        this.pb = pb;
    }

    protected void onPreExecute() {
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", "image download error");
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        //set image of your imageview
        pb.setVisibility(View.GONE);
        BitmapUtil bitmapUtil = new BitmapUtil();
        bmImage.setImageBitmap(bitmapUtil.resize(result));
        //close
        //mDialog.dismiss();
    }
}