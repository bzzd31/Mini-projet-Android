package com.m2dl.mini_projet.mini_projet_android.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


import java.io.InputStream;

/**
 * Created by Lucas-PCP on 24/01/2016.
 */
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