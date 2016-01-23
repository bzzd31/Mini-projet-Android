package com.m2dl.mini_projet.mini_projet_android;

import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.ArrayList;

/**
 * Created by Lucas-PCP on 23/01/2016.
 */
public class InterfacePhoto {

    private double coordLat;
    private double coordLong;
    private String author;
    private ArrayList<Tag> tags;

    public InterfacePhoto(String author, double coordLat, double coordLong) {
        this.author = author;
        this.coordLat = coordLat;
        this.coordLong = coordLong;
        this.tags = new ArrayList<Tag>();
    }

    public void putTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "["+coordLat +
                ", " + coordLong +
                ", " + author +
                ", " + tags +
                ']';
    }
}
