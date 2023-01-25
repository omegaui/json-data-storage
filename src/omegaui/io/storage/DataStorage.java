/**
 * DataStorage utility helps in quickly operating on json files.
 * It can be used to save project settings and other stuff.
 * @author: omegaui
 * Copyright (C) 2021 Omega UI

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package omegaui.io.storage;

import org.json.JSONObject;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class DataStorage {
    /*
        Holds the json data.
     */
    private JSONObject json;
    /*
        Stores the file reference.
     */
    private final File storeFile;
    /*
        Stores already created data storage objects to prevent recreation of the same instance,
        Helps in providing data consistency across various components of the Project.
     */
    private static final LinkedList<DataStorage> storages = new LinkedList<>();
    /*
        Manages instance creation to prevent duplication of same data storages.
        Auto creates the entire path.
        example call: DataStorage.getStorage(".config", "settings.json");
                      DataStorage.getStorage("settings");
     */
    public static DataStorage getStorage(String... paths){
        String name = FileUtils.join(paths);
        if(paths.length == 1){
            name += ".json";
        }
        name = new File(name).getAbsolutePath();
        for(DataStorage dataStorage : storages){
            if(name.equals(dataStorage.getQualifiedName()))
                return dataStorage;
        }
        return new DataStorage(paths);
    }
    /*
        The constructor that accepts only the name of the json file
        Implicitly adds the .json suffix for single argument call.
        Example: new DataStorage("settings"); // Auto Adds the .json suffix.
        Example: new DataStorage(".config", "settings.json"); // the .json suffix needs to be explicitly included.
     */
    public DataStorage(String... paths){
        if(paths.length == 1){
            paths[0] = paths[0] + ".json";
        }
        this.storeFile = new File(FileUtils.join(paths));
        storages.add(this);
        load();
    }
    /*
        Loads the json data from file.
     */
    private void load(){
        String content = FileUtils.read(storeFile);
        if(content != null) {
            json = new JSONObject(content);
        }
        else {
            json = new JSONObject();
        }
    }
    /*
        Reloads the json data from file.
     */
    public void reload(){
        load();
    }

    private void save() {
        FileUtils.write(storeFile, json.toString(4));
    }
    public void put(String key, Object value){
        if(value instanceof Collection<?> collection)
            json.put(key, collection);
        else if(value instanceof Map<?,?> map)
            json.put(key, map);
        else
            json.put(key, value);
        save();
    }

    public void put(String key, int value){
        json.put(key, value);
        save();
    }
    public void put(String key, long value){
        json.put(key, value);
        save();
    }
    public void put(String key, double value){
        json.put(key, value);
        save();
    }
    public void put(String key, float value){
        json.put(key, value);
        save();
    }
    public void put(String key, boolean value){
        json.put(key, value);
        save();
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
    /*
        Returns the absolute path of the data store file.
     */
    public String getQualifiedName() {
        return storeFile.getAbsolutePath();
    }
}