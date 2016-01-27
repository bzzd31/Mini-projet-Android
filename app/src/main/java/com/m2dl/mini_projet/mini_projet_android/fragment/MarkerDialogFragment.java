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
package com.m2dl.mini_projet.mini_projet_android.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.m2dl.mini_projet.mini_projet_android.R;
import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;
import com.m2dl.mini_projet.mini_projet_android.utils.BitmapUtil;

import java.text.DateFormat;
import java.util.ArrayList;

public class MarkerDialogFragment extends DialogFragment {
    public static MarkerDialogFragment newInstance(Photo myPhoto) {
        MarkerDialogFragment dialogMarker = new MarkerDialogFragment();
        Bundle args = new Bundle();
        args.putString("author", myPhoto.getAuthor());
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        args.putString("date", dateFormat.format(myPhoto.getDate()));
        ArrayList<String> myArrayList = new ArrayList<>();
        for (Tag tag: myPhoto.getTags()) {
            myArrayList.add(tag.getNom());
        }
        args.putStringArrayList("tags", myArrayList);
        args.putString("url", myPhoto.getUrl());
        dialogMarker.setArguments(args);
        return dialogMarker;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.marker_view, container, false);
        if (getArguments() != null) {
            BitmapUtil bitmapUtil = new BitmapUtil();
            ImageView bitmapDialog = (ImageView) v.findViewById(R.id.imageView);
            ProgressBar progress = (ProgressBar)v.findViewById(R.id.loadingPanel);
            progress.setVisibility(View.VISIBLE);
            bitmapUtil.loadBitmap(bitmapDialog, progress, getArguments().getString("url"));
            TextView pseudoTV = (TextView)v.findViewById(R.id.textViewPseudo);
            pseudoTV.setText(pseudoTV.getText()+getArguments().getString("author"));
            TextView dateTV = (TextView)v.findViewById(R.id.textViewDate);

            dateTV.setText(dateTV.getText()+dateFormatting(getArguments().getString("date")));
            TextView tagsTV = (TextView)v.findViewById(R.id.textViewTags);
            String tags = "";
            for (String str: getArguments().getStringArrayList("tags")) {
                tags= tags+" "+str;
            }
            tagsTV.setText(tagsTV.getText()+tags);

        }

        getDialog().setTitle("Visualisation du marker");

        Button buttonFermer = (Button)v.findViewById(R.id.buttonFermer);
        buttonFermer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return v;
    }

    private String dateFormatting(String date) {
        String finalDate = "";
        String[] parts = date.split(" ");
        for (int i = 0; i< 5; i++) {
            finalDate = finalDate+parts[i]+" ";
            if (i == 3) {
                finalDate = finalDate + "à ";
            }
        }
        return finalDate;
    }
}
