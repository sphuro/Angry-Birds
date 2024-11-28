package com.AngryBirds;

import java.io.*;
import java.util.ArrayList;

public class MusicHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String MENU_FILE = "music_data.ser";

    public static void save(Main game) {
        try (ObjectOutputStream tosave = new ObjectOutputStream(new FileOutputStream(MENU_FILE))) {
            tosave.writeObject(game.isMusicopen());
        } catch (IOException e) {
            System.err.println("Error!!!");
        }
    }

    public static void load(Main game) {
        File file = new File(MENU_FILE);
        if (!file.exists()) {
            game.setMusicopen(true);
            return;
        }
        try (ObjectInputStream toload = new ObjectInputStream(new FileInputStream(file))) {
            boolean cust = (boolean) toload.readObject();
            game.setMusicopen((boolean) cust);
            System.out.println(cust);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error!!!");
        }
    }
}
