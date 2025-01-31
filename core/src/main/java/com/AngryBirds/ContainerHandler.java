package com.AngryBirds;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public class ContainerHandler implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String MENU_FILE = "container_data.ser";

    public static void save(Main game) {
        try (ObjectOutputStream tosave = new ObjectOutputStream(new FileOutputStream(MENU_FILE))) {
            tosave.writeObject(game.getGameContainer());
        } catch (IOException e) {
            System.err.println("Error!!!");
        }
    }

    public static void load(Main game) {
        File file = new File(MENU_FILE);
        if (!file.exists()) {
            game.setGameContainer(new Container());
            return;
        }
        try (ObjectInputStream toload = new ObjectInputStream(new FileInputStream(file))) {
            Container cust = (Container) toload.readObject();
            game.setGameContainer((Container) cust);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error!!!");
        }
    }
}
