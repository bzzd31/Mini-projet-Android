package com.m2dl.mini_projet.mini_projet_android.data.photo;

import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.ArrayList;
import java.util.List;

public class Photo {

    private double coordLat;
    private double coordLong;
    private String author;
    private List<Tag> tags;
    private String url;

    public Photo(String author, double coordLat, double coordLong) {
        this(author, coordLat, coordLong, null);
    }

    public Photo(String author, double coordLat, double coordLong, String url) {
        this.author = author;
        this.coordLat = coordLat;
        this.coordLong = coordLong;
        this.url = url;
        this.tags = new ArrayList<>();
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
