# json-data-storage
A tiny json based application data utility for Java.


```java
import omegaui.io.storage.DataStorage;

public class Main {
    public static void main(String[] args) {
        DataStorage dataStorage = DataStorage.getStorage("settings");
        dataStorage.put("theme", "atom light");
        DataStorage dataStorage1 = DataStorage.getStorage(".config", "settings.json");
        System.out.println(dataStorage1.getString("theme"));
    }
}
```