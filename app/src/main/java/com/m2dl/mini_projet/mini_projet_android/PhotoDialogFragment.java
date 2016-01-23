package com.m2dl.mini_projet.mini_projet_android;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.ArrayList;


public class PhotoDialogFragment extends DialogFragment {

    private EditText etPseudo, etTags;

    private String pseudo;
    private ArrayList<String> tags;
    private double coordLat;
    private double coordLong;

    public static PhotoDialogFragment newInstance(Bitmap myBitmap, double coordLat, double coordLong) {
        PhotoDialogFragment dialog = new PhotoDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", myBitmap);
        args.putDouble("coordLat", coordLat);
        args.putDouble("coordLong", coordLong);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_photo_view, container, false);
        if (getArguments() != null) {
            Bitmap myBitmap = getArguments().getParcelable("photo");
            ImageView bitmapDialog = (ImageView)v.findViewById(R.id.imageView);
            bitmapDialog.setImageBitmap(myBitmap);

            coordLat = getArguments().getDouble("coordLat");
            coordLong = getArguments().getDouble("coordLong");
            TextView coord = (TextView)v.findViewById(R.id.textViewCoord);
            coord.setText("Latitude: " + coordLat + "  Longitude: " + coordLong);
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
                Boolean authorConfirmed = false, tagsConfirmed = false;
                if (!etPseudo.getText().toString().isEmpty()) {
                    authorConfirmed = true;
                } else {
                    authorConfirmed = false;
                    etPseudo.setError("Ce champs est obligatoire");
                }
                if (!etTags.getText().toString().isEmpty()) {
                    tagsConfirmed = true;
                }
                else {
                    tagsConfirmed = false;
                    etTags.setError("Veuillez rentrer au moins un tag");
                }

                if(tagsConfirmed && authorConfirmed) {
                    InterfacePhoto myPhoto = new InterfacePhoto(etPseudo.getText().toString(), coordLat, coordLong);
                    String[] myTags = etTags.getText().toString().split(",");
                    for (String tag: myTags) {
                        Tag myTag = new Tag(tag.replaceAll("\\s", ""));
                        myPhoto.putTag(myTag);
                    }
                    getDialog().dismiss();
                    Log.i("MYPHOTO", myPhoto.toString());
                }
            }
        });
        return v;
    }
}