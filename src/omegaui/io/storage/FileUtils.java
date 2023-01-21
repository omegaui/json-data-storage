package omegaui.io.storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileUtils {
    public static String join(String... paths){
        StringBuilder result = new StringBuilder();
        for(String path : paths){
            result.append(path).append(File.separator);
        }
        return result.substring(0, result.length() - 1);
    }

    public static String read(File file){
        if(!file.exists()){
            return null;
        }
        String content = "";
        try (Scanner reader = new Scanner(file)) {
            while(reader.hasNextLine()){
                content += reader.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
    public static void write(File file, String content){
        file.getParentFile().mkdirs();
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(content);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
