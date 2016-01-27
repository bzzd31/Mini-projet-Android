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
    private String tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "coordLat=" + coordLat +
                ", coordLong=" + coordLong +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", tags=" + tags +
                ", url='" + url + '\'' +
                '}';
    }
}
