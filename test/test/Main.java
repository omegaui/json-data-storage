package test;

import omegaui.io.storage.DataStorage;

public class Main {
    public static void main(String[] args) {
        DataStorage dataStorage = new DataStorage("settings");
        dataStorage.put("theme", "atom light");
        System.out.println(dataStorage.getString("theme"));
    }
}
