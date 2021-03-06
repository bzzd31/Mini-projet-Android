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
package com.m2dl.mini_projet.mini_projet_android.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.m2dl.mini_projet.mini_projet_android.MainActivity;
import com.m2dl.mini_projet.mini_projet_android.R;
import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;
import com.m2dl.mini_projet.mini_projet_android.photos.ServiceGenerator;
import com.m2dl.mini_projet.mini_projet_android.photos.SimpleImageTag;
import com.m2dl.mini_projet.mini_projet_android.photos.model.Image;
import com.m2dl.mini_projet.mini_projet_android.photos.storage.CloudinaryHelper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PhotoDialogFragment extends DialogFragment {

    private static final String ARG_PHOTO = "photo";
    private static final String ARG_COORD_LAT = "coordLat";
    private static final String ARG_COORD_LONG = "coordLong";
    private static final String ARG_FILE_PATH = "imageFilePath";

    private EditText etPseudo, etTags;

    private ArrayList<String> tags;
    private double coordLat;
    private double coordLong;
    private Bitmap myBitmap;
    private String imageFilePath;
    private static MainActivity mainActivity;

    public static PhotoDialogFragment newInstance(Bitmap myBitmap, double coordLat, double coordLong, String imageFilePath) {
        PhotoDialogFragment dialog = new PhotoDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PHOTO, myBitmap);
        args.putDouble(ARG_COORD_LAT, coordLat);
        args.putDouble(ARG_COORD_LONG, coordLong);
        args.putString(ARG_FILE_PATH, imageFilePath);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_photo_view, container, false);
        if (getArguments() != null) {
            mainActivity = ((MainActivity) getActivity());
            myBitmap = getArguments().getParcelable(ARG_PHOTO);
            ImageView bitmapDialog = (ImageView)v.findViewById(R.id.imageView);
            bitmapDialog.setImageBitmap(myBitmap);

            coordLat = getArguments().getDouble(ARG_COORD_LAT);
            coordLong = getArguments().getDouble(ARG_COORD_LONG);

            imageFilePath = getArguments().getString(ARG_FILE_PATH);
        }

        getDialog().setTitle("Uploader votre photo");

        etPseudo = (EditText) v.findViewById(R.id.editTextPseudo);
        etTags = (EditText) v.findViewById(R.id.editTextTags);

        Button buttonAnnuler = (Button)v.findViewById(R.id.buttonAnnuler);
        buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        Button buttonConfirmer = (Button)v.findViewById(R.id.buttonConfirmer);
        buttonConfirmer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date currentDate = new Date(System.currentTimeMillis());
                Boolean authorConfirmed, tagsConfirmed;
                if (!etPseudo.getText().toString().isEmpty()) {
                    authorConfirmed = true;
                } else {
                    authorConfirmed = false;
                    etPseudo.setError("Ce champs est obligatoire");
                }
                if (!etTags.getText().toString().isEmpty()) {
                    tagsConfirmed = true;
                } else {
                    tagsConfirmed = false;
                    etTags.setError("Veuillez rentrer au moins un tag");
                }

                if (tagsConfirmed && authorConfirmed) {
                    final Photo myPhoto = new Photo(etPseudo.getText().toString(), coordLat, coordLong, currentDate);
                    String[] myTags = etTags.getText().toString().split(",");
                    for (String tag : myTags) {
                        Tag myTag = new Tag(tag.replaceAll("\\s", ""));
                        myPhoto.putTag(myTag);
                    }

                    String tag = etTags.getText().toString().replaceAll("\\s", "");

                    final boolean[] uploaded = {false};

                    final Image p = factory(myPhoto, tag, myBitmap);
                    final Callback<Image> callback = new Callback<Image>() {
                        @Override
                        public void success(Image photo, Response response) {
                            myPhoto.setUrl(photo.url());
                            mainActivity.putInPhotoMarkers(myPhoto);
                            uploaded[0] = true;
                            getDialog().dismiss();
                        }

                        @Override
                        public void failure(RetrofitError error) {

                            System.err.println("Could not post photo");
                        }
                    };

                    final SimpleImageTag simpleImageTag = ServiceGenerator.createService(SimpleImageTag.class);

                    AsyncTask<InputStream, Void, String> task = new AsyncTask<InputStream, Void, String>() {
                        @Override
                        protected String doInBackground(InputStream... params) {
                            String id = CloudinaryHelper.upload(params[0]);
                            p.setId(id);
                            simpleImageTag.postImage(p, callback);
                            return id;
                        }
                    };
                    try {
                        task.execute(new FileInputStream(new File(imageFilePath)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    final ProgressDialog progDialog = ProgressDialog.show(mainActivity, "Upload de l'image en cours...", "Veuillez patienter", true);
                    new Thread() {
                        public void run() {
                            try {
                                while(!uploaded[0]);
                            } catch (Exception e) {
                                Log.e(MainActivity.TAG, e.getMessage());
                            }
                            progDialog.dismiss();
                        }
                    }.start();
                }
            }
        });
        return v;
    }

    public Image factory(Photo mphoto, String tags, Bitmap photo) {
        File file = new File(imageFilePath);
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, os);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Image p;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            p = new Image();
            p.setAuthor(mphoto.getAuthor());
            p.setCoordLat(mphoto.getCoordLat());
            p.setCoordLong(mphoto.getCoordLong());
            p.setTags(tags);
        } catch (IOException e) {
            p = null;
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return p;
    }
}