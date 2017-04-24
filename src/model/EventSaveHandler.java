package model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Robert on 4/15/2017.
 */
public class EventSaveHandler {
    private FileOutputStream fOut;
    private ObjectOutputStream eOut;
    private FileInputStream fIn;
    private ObjectInputStream eIn;
    private String fileName;

    /**
     *
     * @param fName the name of the file you want to use
     */
    public EventSaveHandler(String fName) {
        fileName = fName;
    }

    /**
     *
     * @param fName the name of the file you want to use
     */
    public void changeFile(String fName) {
        fileName = fName;
    }

    /**
     *
     * @return  the current file's name
     */
    public String currentFileName() {
        return fileName;
    }

    /**
     *
     * @param list the ArrayList of events to write to the current file
     */
    public void writeFromList(ArrayList<Event> list) {
        try {
            fOut = new FileOutputStream(fileName);
            eOut = new ObjectOutputStream(fOut);
            /* trying something new here
            for(int i = 0; i < list.size(); i++)
            {
                Event temp = list.get(i);
                eOut.writeObject(temp);
            }
            */
            eOut.writeObject(list);
            fOut.close();
            eOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param list the ArrayList of events to add to by reading from the current file, does not overwrite existing members
     */
    public void readToList(ArrayList<Event> list) {
        try {
            fIn = new FileInputStream(fileName);
            eIn = new ObjectInputStream(fIn);

            /*weird code here, trying something else
            boolean moreToRead = true;
            while(moreToRead)
            {
                try {
                    Event event = (Event) eIn.readObject();
                    list.add(event);
                } catch(EOFException ef)
                {
                    moreToRead = false;
                }
            }
            */
            Object temp = eIn.readObject();
            ArrayList<Event> tempList = (ArrayList<Event>) temp;

            for(int i = 0; i < tempList.size(); i++)
            {
                list.add(tempList.get(i));
            }

            fIn.close();
            eIn.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Probably nothing to read");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
