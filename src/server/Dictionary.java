package server;

import java.util.HashMap;

public class Dictionary {
    private HashMap<String, String> dict = new HashMap<String, String>();

    public Dictionary() {
        dict.put("apple", "aaa");
    }

    public Dictionary(String path) {

    }

    public synchronized String query(String word) {
        return dict.get(word);
    }

    public synchronized boolean add(String word, String meaning) {

        return false;
    }

    public synchronized boolean remove(String word) {

        return false;
    }

    public synchronized boolean update(String word, String meaning) {

        return false;
    }
}
