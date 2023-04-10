package com.example.fartosandroid.objects;

import java.io.Serializable;

public class CartaV implements Serializable {
    private String nom;
    private int skin;

    /*Tipus:
     * 1: +/-1
     * 2: +/-2
     * 3: +/-3
     * 4: Teleport
     * 5: Zancadilla
     * 6: Patada
     * 7: Hundimiento
     * 8: Broma
     * */
    private  int tipus;


    public CartaV(String nom, int skin) {
        this.nom = nom;
        this.skin = skin;
    }

    public CartaV(String nom, int skin, int tipus) {
        this.nom = nom;
        this.skin = skin;
        this.tipus = tipus;
    }

    public int getTipus() {
        return tipus;
    }

    public void setTipus(int tipus) {
        this.tipus = tipus;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSkin() {
        return skin;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }


    @Override
    public String toString() {
        return "CartaV{" +
                "nom='" + nom + '\'' +
                ", skin=" + skin +
                ", tipus=" + tipus +
                '}';
    }
}
