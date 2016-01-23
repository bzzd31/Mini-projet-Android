package com.m2dl.mini_projet.mini_projet_android.data.tag;

public class PointInteret extends Tag {

    private int color;

    public PointInteret(String name, int color) {
        super(name);
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
