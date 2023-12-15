/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare;

import DaysCare.Immunization.ImmunizationRecord;
import DaysCare.Immunization.RegistrationRecord;
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
public class FileUtil {
    public static List<String> readFIle(String filePath) {
		List<String> list=new ArrayList<>();
        filePath = System.getProperty("user.dir")+"/" +filePath;
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
    public static String writeToFile() {
        StringBuilder sb = new StringBuilder();
        Map<Classroom, List<Group>> classMap = SingletonAdmin.getClassMap();
        Map<Student, List<ImmunizationRecord>> studentMap = SingletonAdmin.getStudentMap();
        Map<Student, RegistrationRecord> registrationMap = SingletonAdmin.getRegistrationMap();

        for (Map.Entry<Classroom, List<Group>> classEntry : classMap.entrySet()) {
            Classroom currentClass = classEntry.getKey();
            List<Group> groups = classEntry.getValue();

            for (Group group : groups) {
                List<Student> students = group.getStudentList();

                for (Student student : students) {
                    List<ImmunizationRecord> records = studentMap.get(student);
                    RegistrationRecord registrationRecord = registrationMap.get(student);

                    sb.append("Class Num: ").append(currentClass).append('\n');
                    sb.append("Group Num: ").append(group.toString()).append('\n');
                    sb.append("Student Name: ").append(student.getName()).append(" Student Age: ").append(student.getAge()).append('\n');

                    for (ImmunizationRecord record : records) {
                        sb.append(record.getRecords()).append('\n');
                    }

                    if (registrationRecord != null) {
                        sb.append("Last Registration Date: ").append(registrationRecord.getLastRegistrationDate()).append('\n');
                        sb.append("Next Registration Date: ").append(registrationRecord.getNextRegistrationDate()).append('\n');
                        sb.append("Registration Status: ").append(registrationRecord.getStatus()).append('\n');
                    } else {
                        sb.append("Registration information not available.\n");
                    }
                    sb.append('\n'); // Adding a new line for separation between students
                }
            }
        }

        String filePath = System.getProperty("user.dir") + "/src/DaysCare/Data/Output.txt";
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Fail to write to File: " + e.getMessage());
        }

        return sb.toString();
    }





}
