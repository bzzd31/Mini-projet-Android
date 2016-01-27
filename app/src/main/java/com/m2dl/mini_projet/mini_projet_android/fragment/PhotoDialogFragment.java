package com.m2dl.mini_projet.mini_projet_android.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
        args.putParcelable("photo", myBitmap);
        args.putDouble("coordLat", coordLat);
        args.putDouble("coordLong", coordLong);
        args.putString("imageFilePath", imageFilePath);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_photo_view, container, false);
        if (getArguments() != null) {
            mainActivity = ((MainActivity) getActivity());
            myBitmap = getArguments().getParcelable("photo");
            ImageView bitmapDialog = (ImageView)v.findViewById(R.id.imageView);
            bitmapDialog.setImageBitmap(myBitmap);

            coordLat = getArguments().getDouble("coordLat");
            coordLong = getArguments().getDouble("coordLong");

            imageFilePath = getArguments().getString("imageFilePath");
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
                Boolean authorConfirmed = false, tagsConfirmed = false;
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
                    final Photo myPhoto = new Photo(myBitmap, etPseudo.getText().toString(), coordLat, coordLong, currentDate);
                    String[] myTags = etTags.getText().toString().split(",");
                    for (String tag : myTags) {
                        Tag myTag = new Tag(tag.replaceAll("\\s", ""));
                        myPhoto.putTag(myTag);
                    }

                    String tag = etTags.getText().toString().replaceAll("\\s", "");

                    final boolean[] uploaded = {false};

                    final Image p = factory(myPhoto, tag, myPhoto.getMyBitmap());
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

                            System.err.println("oups");
                        }
                    };

                    final SimpleImageTag simpleImageTag = ServiceGenerator.createService(SimpleImageTag.class);

                    AsyncTask<InputStream, Void, String> task = new AsyncTask<InputStream, Void, String>() {
                        @Override
                        protected String doInBackground(InputStream... params) {
                            String id = CloudinaryHelper.upload(params[0]);
                            p.setAuthor(id);
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