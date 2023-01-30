
<div align="center">
    <h3>🚀 The Shared Preferences API for Java 🚀</h3>
</div>

# ![](https://img.icons8.com/3d-fluency/48/null/3d-fluency-database.png) json-data-storage
A tiny json based application data utility for Java.

# ![](https://img.icons8.com/3d-fluency/48/null/tape-measure-sewing.png) Features
- No Redundancy
- Easily Handles multiple references of same Storage
- Real-Time Write i.e Automatically Saves file on changes
- Auto constructs the entire path

# ![](https://img.icons8.com/3d-fluency/48/null/nui2.png) Usage
All we need is a DataStorage object.

### Basic Call

````java
DataStorage storage = DataStorage.getStorage("settings"); // auto adds ".json suffix", the call is equivalent to "settings.json"
storage.put("theme", "dark");
// When put(String, Object) is called 
// The above line will finish off writing settings.json in the current working directory
````

### Advanced Call

````java
DataStorage storage = DataStorage.getStorage(".config", "settings.json");
storage.put("theme", "dark");
// When put(String, Object) is called 
// The above line will finish off writing settings.json in the .config working directory
````

#### Note: DataStorage.getStorage() **auto-constructs** the path if it doesn't already exist

# ![](https://img.icons8.com/3d-fluency/48/null/smartphone-tablet.png) Handling Multiple References

Now, if there are multiple cases requesting the reference to the same DataStorage object, so instead of 
creating multiple instances pointing to the same storage location, 
**the same instance is referenced at every call**.

Example:
```java
public class Preferences{
    public void saveTheme(){
        DataStorage storage = DataStorage.getStorage(".config", "settings.json");
        storage.put("theme", "dark");
    }
    
    public void saveUserInfo(){
        DataStorage storage = DataStorage.getStorage(".config", "settings.json");
        storage.put("username", "iron-man");
        storage.put("password", "!@#$%^&*^");
    }
}
```

Although, the **storage** objects request the same json file, so instead of creating multiple references 
of the class, a single instance is shared to both.

<div align="center">
    <img src="/images/json-data-storage-reference-handle-illustration.png"/>
</div>

its Like
```java
storage1 = storage2 = the_shared_object
```


