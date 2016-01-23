package com.m2dl.mini_projet.mini_projet_android.data.tag;

public class Tag implements Comparable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return nom != null ? nom.equals(tag.nom) : tag.nom == null;

    }

    @Override
    public int hashCode() {
        return nom != null ? nom.hashCode() : 0;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return -1;

        Tag tag = (Tag) o;

        return nom.compareTo(tag.nom);
    }
}
