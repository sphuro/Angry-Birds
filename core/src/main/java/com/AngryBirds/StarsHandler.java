package com.AngryBirds;

import java.io.*;
import java.util.ArrayList;

public class StarsHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String MENU_FILE = "star_data.ser";

    public static void save(Main game) {
        try (ObjectOutputStream tosave = new ObjectOutputStream(new FileOutputStream(MENU_FILE))) {
            tosave.writeObject(game.getStars());
        } catch (IOException e) {
            System.err.println("Error!!!");
        }
    }

    public static void load(Main game) {
        File file = new File(MENU_FILE);
        if (!file.exists()) {
            ArrayList<Integer> tos = new ArrayList<>();
            for (int i=0;i<3;i++) tos.add(0);
            game.setStars(tos);
            return;
        }
        try (ObjectInputStream toload = new ObjectInputStream(new FileInputStream(file))) {
            ArrayList<Integer> cust = (ArrayList<Integer>) toload.readObject();
            game.setStars((ArrayList<Integer>) cust);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error!!!");
        }
    }
}
