/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
}
