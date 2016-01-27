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
package com.m2dl.mini_projet.mini_projet_android.photos.model;

import com.m2dl.mini_projet.mini_projet_android.photos.Constants;

import java.util.Date;

public class Image {

    private String id;
    private double coordLat;
    private double coordLong;
    private String author;
    private Date date;
    private String tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(double coordLat) {
        this.coordLat = coordLat;
    }

    public double getCoordLong() {
        return coordLong;
    }

    public void setCoordLong(double coordLong) {
        this.coordLong = coordLong;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String url() {
        return Constants.CLOUDINARY_URL_IMAGE + "/" + id + Constants.IMG_EXTENSION;
    }
}
