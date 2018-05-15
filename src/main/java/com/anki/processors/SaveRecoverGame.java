package com.anki.processors;

import com.anki.model.Game;
import lombok.AllArgsConstructor;

import java.io.*;

/**
 * @author routarddev
 */
@AllArgsConstructor
public class SaveRecoverGame {

    private String filename;

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
    }

    /**
     * Deserialization process for the Anki Game status
     * @throws IOException
     * @throws ClassNotFoundException
     * @return object Game
     */
    public Game readObject() throws IOException, ClassNotFoundException {
        // Reading the object from the serialized file
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);

        // Method for deserialization of object
        Game object = (Game) in.readObject();

        in.close();
        file.close();

        return object;
    }

}
