package omegaui.io.storage;

import org.json.JSONObject;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public class DataStorage {
    private boolean loaded = false;
    private JSONObject json;
    private final File storeFile;
    public DataStorage(String name){
        this.storeFile = new File(FileUtils.join(".config", "storage", name + ".json"));
    }
    private void load(){
        if(loaded)
            return;
        loaded = true;
        String content = FileUtils.read(storeFile);
        if(content != null) {
            json = new JSONObject(content);
        }
        else {
            json = new JSONObject();
        }
    }
    public void reload(){
        loaded = false;
    }
    public void put(String key, Object value){
        load();
        if(value instanceof Collection<?> collection)
            json.put(key, collection);
        else if(value instanceof Map<?,?> map)
            json.put(key, map);
        else
            json.put(key, value);
        FileUtils.write(storeFile, json.toString(4));
    }
    public void put(String key, int value){
        load();
        json.put(key, value);
        FileUtils.write(storeFile, json.toString(4));
    }
    public void put(String key, long value){
        load();
        json.put(key, value);
        FileUtils.write(storeFile, json.toString(4));
    }
    public void put(String key, double value){
        load();
        json.put(key, value);
        FileUtils.write(storeFile, json.toString(4));
    }
    public void put(String key, float value){
        load();
        json.put(key, value);
        FileUtils.write(storeFile, json.toString(4));
    }
    public void put(String key, boolean value){
        load();
        json.put(key, value);
        FileUtils.write(storeFile, json.toString(4));
    }
    public Object get(String key){
        return json.get(key);
    }
    public Object getString(String key){
        return json.getString(key);
    }
    public Object getInt(String key){
        return json.getInt(key);
    }
    public Object getLong(String key){
        return json.getLong(key);
    }
    public Object getFloat(String key){
        return json.getFloat(key);
    }
    public Object getDouble(String key){
        return json.getDouble(key);
    }

}