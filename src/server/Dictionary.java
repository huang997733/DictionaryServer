/**
 * author: Ziyang Huang 1067800
 */
package server;

import java.io.*;
import java.util.HashMap;

public class Dictionary {
    private String path;
    private HashMap<String, String> dict = new HashMap<String, String>();

    public Dictionary(String path) {
        this.path = path;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            dict = (HashMap<String, String>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong dictionary format! Running default");
            runDefault();
        } catch (FileNotFoundException e) {
            System.out.println("No such file! Running default");
            runDefault();
        } catch (Exception e) {
            System.out.println("Unknown error");
            System.exit(-1);
        }

    }

    public synchronized String query(String word) {
        return dict.get(word);
    }

    public synchronized boolean add(String word, String meaning) {
        if (dict.containsKey(word)) {
            return false;
        }
        dict.put(word, meaning);
        writeFile(this.path);
        return true;
    }

    public synchronized boolean remove(String word) {
        if (dict.containsKey(word)) {
            dict.remove(word);
            writeFile(this.path);
            return true;
        }
        return false;
    }

    public synchronized boolean update(String word, String meaning) {
        if (dict.containsKey(word)) {
            dict.remove(word);
            dict.put(word, meaning);
            writeFile(this.path);
            return true;
        }
        return false;
    }

    public void writeFile(String path) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(dict);
            oos.close();
        } catch (Exception e) {
            System.out.println("Unknown error occurred when writing file");
        }

    }

    private void runDefault() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            dict = (HashMap<String, String>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("No default dictionary, create one");
            createDict();
        } catch (Exception e) {
            System.out.println("Unknown error");
            System.exit(-1);
        }
    }

    private void createDict() {
        dict = new HashMap<String, String>();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(dict);
            oos.close();
        } catch (Exception e) {
            System.out.println("Unknown error");
            System.exit(-1);
        }
    }

}
