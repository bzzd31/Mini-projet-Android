package com.m2dl.mini_projet.mini_projet_android;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class PhotoDialogFragment extends DialogFragment {

    public static PhotoDialogFragment newInstance(Bitmap myBitmap, double coordX, double coordY) {
        PhotoDialogFragment dialog = new PhotoDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", myBitmap);
        args.putDouble("coordX", coordX);
        args.putDouble("coordY", coordY);
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

            double coordX = getArguments().getDouble("coordX");
            double coordY = getArguments().getDouble("coordY");
            TextView coord = (TextView)v.findViewById(R.id.textViewCoord);
            coord.setText("Coordonnées de la prise de photo: " + coordX + " ; " + coordY);
        }

        ((MainActivity)getActivity()).getCoordX();

        getDialog().setTitle("Uploader votre photo");

        Button buttonAnnuler = (Button)v.findViewById(R.id.buttonAnnuler);
        return v;
    }
}