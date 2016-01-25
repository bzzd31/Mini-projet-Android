package com.m2dl.mini_projet.mini_projet_android.utils;

import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TagUtil {

    public static boolean containsOneOf(Set<Tag> tags1, List<Tag> tags2) {
        boolean found = false;
        Iterator it = tags2.iterator();
        while (!found && it.hasNext()) {
            Tag tag = (Tag) it.next();
            if (tags1.contains(tag)) {
                found = true;
            }
        }
        return found;
    }

    public static Set<Tag> extractTags(List<Photo> photos) {
        Set<Tag> tags = new HashSet<>();

        for (Photo photo : photos) {
            tags.addAll(photo.getTags());
        }

        return tags;
    }
}
