package com.m2dl.mini_projet.mini_projet_android.data.pointInteret;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public enum PointInteretType {

    CARTON ("Carton", BitmapDescriptorFactory.HUE_ORANGE),
    PAPIER ("Papier", BitmapDescriptorFactory.HUE_CYAN),
    PILES ("Piles", BitmapDescriptorFactory.HUE_VIOLET),
    TEXTILES ("Textiles", BitmapDescriptorFactory.HUE_ROSE),
    VERRE ("Verre", BitmapDescriptorFactory.HUE_GREEN);

    private String name;
    private float color;

    PointInteretType(String name, float color) {
        this.name = name;
        this.color = color;
    }

    public float getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name;
    }
}
