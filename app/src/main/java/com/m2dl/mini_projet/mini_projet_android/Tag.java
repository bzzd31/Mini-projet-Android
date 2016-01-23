package com.m2dl.mini_projet.mini_projet_android;

/**
 * Created by Lucas-PCP on 23/01/2016.
 */
public class Tag {
    private String nom;

    public Tag(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
