/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare;

import DaysCare.Immunization.ImmunizationRecord;
import DaysCare.Organization.Classroom;
import DaysCare.Organization.Group;
import DaysCare.Person.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JANFAN
 */
class FileUtil {
    public static List<String> readFIle(String filePath) {
		List<String> list=new ArrayList<>();
        filePath = System.getProperty("user.dir") + "/6200final/" +filePath;
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

    /**
     * return the structure of
     * hashmap data. And return a String to read
     * @return a string of the data structure
     */
    public static String writeToFile (){
        StringBuilder sb = new StringBuilder();
        Map<Classroom, List<Group>> classMap = SingletonAdmin.getClassMap();
        Map<Student,List<ImmunizationRecord>>studentMap = SingletonAdmin.getStudentMap();

        for(Map.Entry<Classroom, List<Group>> classEntry : classMap.entrySet()){
            Classroom currentClass = classEntry.getKey();
            List<Group> groups = classEntry.getValue();
            System.out.println("Class Num: " + currentClass);
            // for each gropu
            for (Group group : groups) {
                System.out.println("\tGroup Num: " + group.toString());


                List<Student> students = group.getStudentList();

                // for each student in group
                for (Student student : students) {
                    List<ImmunizationRecord> records = studentMap.get(student);

                    // print vaccine records
                    sb.append("Class Num: " + currentClass + '\n');
                    sb.append("Group Num: " + group.toString() + '\n');
                    sb.append("Student Name: ").append(student).append("Student Age: ").append(student.getAge()).append('\n');

                    System.out.println("\tStudent: " + student + " \\ Age: " + student.getAge());
                    System.out.println("\tRecords: ");
                    for(ImmunizationRecord i : records){
                        System.out.println("\t\t"+i.getRecords());
                        sb.append(i.toString() + '\n');
                    }
                }
            }
            System.out.println();
        }

        String filePath = System.getProperty("user.dir") + "/6200final/" + "src/DaysCare/Data/Output.txt";
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(sb.toString());
        }catch (IOException e){
            System.out.println("Fail to write to File: " + e.getMessage());
        }

        return sb.toString();
    }


}
