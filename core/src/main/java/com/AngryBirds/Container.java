package com.AngryBirds;

import java.util.ArrayList;

public class Container {
    private ArrayList<ArrayList<Float>> box = new ArrayList<>();
    private ArrayList<ArrayList<Float>> helmetpig = new ArrayList<>();
    private ArrayList<ArrayList<Float>> glassbox = new ArrayList<>();
    private ArrayList<ArrayList<Float>> kingpig = new ArrayList<>();
    private ArrayList<ArrayList<Float>> log = new ArrayList<>();
    private ArrayList<ArrayList<Float>> pig = new ArrayList<>();
    private ArrayList<ArrayList<Float>> stonebox = new ArrayList<>();
    private ArrayList<ArrayList<Float>> stonelog = new ArrayList<>();
    int level = 0;
    int birds = 0;

    public ArrayList<ArrayList<Float>> getStonelog() {
        return stonelog;
    }

    public ArrayList<ArrayList<Float>> getStonebox() {
        return stonebox;
    }

    public ArrayList<ArrayList<Float>> getPig() {
        return pig;
    }

    public ArrayList<ArrayList<Float>> getLog() {
        return log;
    }

    public ArrayList<ArrayList<Float>> getKingpig() {
        return kingpig;
    }

    public ArrayList<ArrayList<Float>> getGlassbox() {
        return glassbox;
    }

    public ArrayList<ArrayList<Float>> getHelmetpig() {
        return helmetpig;
    }

    public ArrayList<ArrayList<Float>> getBox() {
        return box;
    }

    public Container() {

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setBirds(int birds) {
        this.birds = birds;
    }

    public int getBirds() {
        return birds;
    }
}
