package com.AngryBirds;

import java.io.*;
import java.util.ArrayList;

public class SoundHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String MENU_FILE = "sound_data.ser";

    public static void save(Main game) {
        try (ObjectOutputStream tosave = new ObjectOutputStream(new FileOutputStream(MENU_FILE))) {
            tosave.writeObject(game.isSoundopen());
        } catch (IOException e) {
            System.err.println("Error!!!");
        }
    }

    public static void load(Main game) {
        File file = new File(MENU_FILE);
        if (!file.exists()) {
            game.setSoundopen(true);
            return;
        }
        try (ObjectInputStream toload = new ObjectInputStream(new FileInputStream(file))) {
            boolean cust = (boolean) toload.readObject();
            game.setSoundopen((boolean) cust);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error!!!");
        }
    }
}
