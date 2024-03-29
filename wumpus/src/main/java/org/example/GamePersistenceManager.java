package org.example;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Álláskezelő.
 */
public class GamePersistenceManager {

    /**
     * Mentés.
     *
     * @param gameBoard pálya
     *
     * @param fileName név
     *
     * @return vége
     */
    public boolean saveGameState(GameBoard gameBoard, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(GameBoard.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File file = new File(fileName + "_gameState.xml");
            marshaller.marshal(gameBoard, file);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Mentés.
     *
     * @param gameStateName név
     *
     * @return vége
     */
    public GameBoard loadGameState(String gameStateName) {
        try {
            JAXBContext context = JAXBContext.newInstance(GameBoard.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();


            File file = new File(gameStateName + "_gameState.xml");
            GameBoard gameBoard = (GameBoard) unmarshaller.unmarshal(file);


            gameBoard.postLoadInitialization();

            return gameBoard;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
