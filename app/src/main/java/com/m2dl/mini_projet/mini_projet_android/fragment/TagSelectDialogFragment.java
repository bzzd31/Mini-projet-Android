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

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.m2dl.mini_projet.mini_projet_android.MainActivity;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagSelectDialogFragment extends DialogFragment {

    private static final String ARG_ALL_TAGS = "allTags";
    private static final String ARG_SELECTED_TAGS = "selectedTags";

    private List<Tag> mAllTags;
    private List<Tag> mSelectedTags;
    private List<Tag> mNewSelectedTags;

    public static TagSelectDialogFragment newInstance(List<Tag> allTags, List<Tag> selectedTags) {
        TagSelectDialogFragment fragment = new TagSelectDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ALL_TAGS, (ArrayList) allTags);
        args.putSerializable(ARG_SELECTED_TAGS, (ArrayList) selectedTags);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        mAllTags = (List) getArguments().getSerializable(ARG_ALL_TAGS);
        mSelectedTags = (List) getArguments().getSerializable(ARG_SELECTED_TAGS);

        mNewSelectedTags = new ArrayList<>(mSelectedTags);

        CharSequence[] options = new CharSequence[mAllTags.size()];
        boolean[] optionsChecked = new boolean[mAllTags.size()];

        for (int i = 0; i < mAllTags.size(); i++) {
            Tag tag = mAllTags.get(i);
            options[i] = tag.toString();
            optionsChecked[i] = mSelectedTags.contains(tag);
        }

        // Set the dialog title
        builder.setTitle("Tags à afficher")
                .setMultiChoiceItems(options, optionsChecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked) {
                            mNewSelectedTags.add(mAllTags.get(which));
                        } else {
                            mNewSelectedTags.remove(mAllTags.get(which));
                        }
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity activity = (MainActivity) getActivity();
                        activity.setSelectedTags(mNewSelectedTags);
                        activity.refreshPhoto();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}
