/**
 * Common FileUtils.
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
        File parent = file.getParentFile();
        if(parent != null) {
            parent.mkdirs();
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(content);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
