package com.example.fartosandroid.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements Serializable {

    private String nom;
    public List<CartaV> ma = new ArrayList<>();
    private boolean patada = false;
    private boolean zancadilla = false;
    private int sprite;
    private int icon;
    private int nCasilla = -1;
    private boolean p;

    public Jugador() {
    }

    public Jugador(String nom, List<CartaV> ma, boolean patada, boolean zancadilla, int sprite) {
        this.nom = nom;
        this.ma = ma;
        this.patada = patada;
        this.zancadilla = zancadilla;
        this.sprite = sprite;
    }

    public Jugador(String nom, int sprite, int icon) {
        this.nom = nom;
        this.sprite = sprite;
        this.icon = icon;
    }

    public Jugador(String nom) {
        this.nom = nom;
    }



    public int getnCasilla() {
        return nCasilla;
    }

    public void setnCasilla(int nCasilla) {
        this.nCasilla = nCasilla;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<CartaV> getMa() {
        return ma;
    }

    public void setMa(List<CartaV> ma) {
        this.ma = ma;
    }

    public boolean isPatada() {
        return patada;
    }

    public void setPatada(boolean patada) {
        this.patada = patada;
    }

    public boolean isZancadilla() {
        return zancadilla;
    }

    public void setZancadilla(boolean zancadilla) {
        this.zancadilla = zancadilla;
    }

    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

}
