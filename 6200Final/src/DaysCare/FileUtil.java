/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JANFAN
 */
class FileUtil {
    public static List<String> readFIle(String filePath) {
		List<String> list=new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
        	
            String line;
            // Read lines from the file until the end of the file is reached
            while ((line = bufferedReader.readLine()) != null) {
            	list.add(line);
            }
            
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return list;
	}

    public static void writeToFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
