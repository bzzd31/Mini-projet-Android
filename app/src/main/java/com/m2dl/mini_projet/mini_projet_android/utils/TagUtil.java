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
