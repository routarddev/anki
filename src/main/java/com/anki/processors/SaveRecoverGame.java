package com.anki.processors;

import com.anki.utils.Constants;

import java.io.*;

/**
 * @author routarddev
 */
public class SaveRecoverGame {

    private static final String filename = Constants.FILE_PATH + Constants.GAME_STATUS_FILE;

    /**
     * Serialization process for the Anki Game status
     * @param object Game
     * @throws IOException
     */
    public void writeObject(Object object) throws IOException {
        //Saving of object in a file
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);

        // Method for serialization of object
        out.writeObject(object);

        out.close();
        file.close();

        System.out.println("Object has been serialized");
    }

    /**
     * Deserialization process for the Anki Game status
     * @param object Game
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readObject(Object object) throws IOException, ClassNotFoundException {
        // Reading the object from the serialized file
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);

        // Method for deserialization of object
        object = in.readObject();

        in.close();
        file.close();

        System.out.println("Object has been deserialized ");
        System.out.println("object = " + object.toString());
    }

}
