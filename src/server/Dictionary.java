package server;

import java.io.*;
import java.util.HashMap;

public class Dictionary {
    private String path;
    private HashMap<String, String> dict = new HashMap<String, String>();

    public Dictionary() {
        dict.put("apple", "aaa");
    }

    public Dictionary(String path) {
        this.path = path;
        dict = loadFile(path);

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

        return false;
    }

    public synchronized boolean update(String word, String meaning) {

        return false;
    }

    public HashMap<String, String> loadFile(String path) {

        HashMap<String, String> dict = new HashMap<String, String>();
        BufferedReader br = null;
        try {
            File file = new File(path);

            br = new BufferedReader(new FileReader(file));

            String line = null;

            while ((line = br.readLine()) != null) {


                String[] parts = line.split(":");


                String name = parts[0].trim();
                String number = parts[1].trim();

                if (!name.equals("") && !number.equals(""))
                    dict.put(name, number);
            }
        }
        catch (Exception e) {
            System.out.println("EXCEPTION!");
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
        return dict;
    }

    public void writeFile(String path) {

        BufferedWriter bf = null;
        try {

            bf = new BufferedWriter(new FileWriter(path));

            for (HashMap.Entry<String, String> entry : dict.entrySet()) {

                bf.write(entry.getKey() + ":" + entry.getValue());

                bf.newLine();
            }

            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {

                bf.close();
            }
            catch (Exception e) {
            }
        }
    }

}
