package com.m2dl.mini_projet.mini_projet_android.utils;

import android.util.Log;

import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TagUtil {

    public static boolean containsOneOf(Set<Tag> tags1, List<Tag> tags2) {
       /*boolean found = false;
        Iterator it = tags2.iterator();
        while (!found && it.hasNext()) {
            Tag tag = (Tag) it.next();
            if (tags1.contains(tag)) {
                found = true;
            }
        }*/
       for (Tag tag: tags2) {
            if (tagInTags(tags1, tag)) {
                return true;
            }
        }
        return false;
    }

    public static boolean tagInTags(Set<Tag> tags, Tag tag) {
        for (Tag itTag: tags) {
            String tag1 = itTag.getNom();
            String tag2 = tag.getNom();
            Log.i("COMPARE_TO", tag1+ " >>> " + tag2 + " === " + tag1.compareTo(tag2));
            if (itTag.getNom().compareTo(tag.getNom()) == 0) {
                return true;
            }
        }
        Log.i("COMPARE_TO", "\n=========================================================\n");
        return false;
    }

    public static Set<Tag> extractTags(List<Photo> photos) {
        Set<Tag> tags = new HashSet<>();

        for (Photo photo : photos) {
            tags.addAll(photo.getTags());
        }

        return tags;
    }
}
