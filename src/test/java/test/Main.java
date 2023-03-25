package test;

import omegaui.io.storage.DataStorage;

public class Main {
    public static void main(String[] args) {
        DataStorage dataStorage = DataStorage.getStorage("settings");
        System.out.println(dataStorage.query("values", "x"));
    }
}
