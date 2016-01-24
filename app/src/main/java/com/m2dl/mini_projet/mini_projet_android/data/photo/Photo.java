package com.m2dl.mini_projet.mini_projet_android.data.photo;

import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Photo {

    private double coordLat;
    private double coordLong;
    private String author;
    private Date date;
    private List<Tag> tags;
    private String url;

    public Photo(String author, double coordLat, double coordLong, Date date) {
        this(author, coordLat, coordLong, date, null);
    }

    public Photo(String author, double coordLat, double coordLong, Date date, String url) {
        this.author = author;
        this.coordLat = coordLat;
        this.coordLong = coordLong;
        this.date = date;
        this.url = url;
        this.tags = new ArrayList<>();
    }

    public void putTag(Tag tag) {
        this.tags.add(tag);
    }

    public double getCoordLat() {
        return coordLat;
    }

    public double getCoordLong() {
        return coordLong;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "coordLat=" + coordLat +
                ", coordLong=" + coordLong +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", tags=" + tags +
                ", url='" + url + '\'' +
                '}';
    }
}
