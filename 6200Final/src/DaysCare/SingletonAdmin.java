/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare;

import DaysCare.Immunization.Immunization;
import DaysCare.Immunization.ImmunizationFactory;
import DaysCare.Immunization.ImmunizationRecord;
import DaysCare.Organization.Classroom;
import DaysCare.Organization.Group;
import DaysCare.Organization.Level;
import DaysCare.Person.PersonFactory;
import DaysCare.Person.Student;
import DaysCare.Person.Teacher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author JANFAN
 */
public class SingletonAdmin {
    static Map<Level,List<Classroom>>levelMap;//each level has a list f classroom
    static Map<Classroom,List<Group>>classMap;//each classroom has a list of group
    static Map<Student,List<ImmunizationRecord>>studentMap;//each student has a list of thier list of immunizastion record
    static Map<Level,Integer>groupSizeMap;//it will store the size of each group in different level
    static Map<Level,Integer>classSizeMap;//it will store the size of each class in different level
    
    static List<Student> studentList;//used to store all student
    List<Teacher> teacherList;//used to store all teachers
    static List<Immunization> immunizationList;//use to store all immunizations
    private static final SingletonAdmin instance = new SingletonAdmin();

    private SingletonAdmin() {
        levelMap=new HashMap<>();
        classMap=new HashMap<>();
        studentMap=new HashMap<>();
        groupSizeMap=new HashMap<>();
        classSizeMap=new HashMap<>();
        
        studentList = new ArrayList<>();
        teacherList = new ArrayList<>();

    }

    public static SingletonAdmin getInstance() {
        //1.read immunization file
        readImmunization();
        //2.read student file and immunization file
        readStudentInformation();
        //3.read teacher file ratio rule file 
        readTeacher();
        //4.read ratio file and store the level and capatity to hash map
        readRatio();
        //5.distribution student and teacher to each group and class
        distribute();
        

        return instance;
    }

    private static void readImmunization() {
        //read immunizationrole file, which contains immunization name and duration
        List<String> list = FileUtil.readFIle("src/DaysCare/Data/ImmunizationRole.txt");
        //use Lambda to turn all string to immunization object
        instance.immunizationList=list.stream()
                .map(ImmunizationFactory.IMMUNIZATION::getImmunization)
                .collect(Collectors.toList());

    }
    private static void readStudentInformation() {
        List<String> students = FileUtil.readFIle("src/DaysCare/Data/StudentData.txt");
        List<String>records=FileUtil.readFIle("src/DaysCare/Data/ImmunizationRecord.txt");
        //read student file and create all student object
        for(String s:students){
            instance.studentList.add((Student) PersonFactory.STUDENT.getPerson(s));  
        }
        //for each student,it will make a list of immunization list
        //then put the student and immunization to hash map studentMap
        for(int i=0;i<records.size();i++){
            String []a = records.get(i).split(",");
            List<ImmunizationRecord>recordList=new ArrayList<>();//use to store immunization record
            System.out.println(instance.studentList.get(i));
            //each line contins some date for last time immunization
            //and each date corresponds to one immunization
            for(int j=0;j<instance.immunizationList.size();j++){
                
                ImmunizationRecord ir=new ImmunizationRecord(instance.immunizationList.get(j),instance.studentList.get(i),a[j+1]);
                recordList.add(ir);
            }
            //put the student and his/her immunization record list to hashmap;
            studentMap.put(instance.studentList.get(i), recordList);
        }
    }
    private static void readTeacher() {
        List<String> teachers = FileUtil.readFIle("src/DaysCare/Data/TeacherData.txt");
        //read teacher file and create all teacher object
        for(String s:teachers){
            instance.teacherList.add((Teacher) PersonFactory.TEACHER.getPerson(s));  
        }
    }
    private static void readRatio() {
        //read group size data
        List<String> groupSizeData = FileUtil.readFIle("src/DaysCare/Data/RatioRoleForStudentToTeacher.txt");
        for(String s:groupSizeData){
            String []a = s.split(",");
            Level level=Level.getType(Integer.parseInt(a[0]));
            int capacity=Integer.parseInt(a[2]);
            
            instance.groupSizeMap.put(level, capacity);
        }
        
        //read class size data
        List<String> classSizeData = FileUtil.readFIle("src/DaysCare/Data/RatioRoleForGroupToClassRoom.txt");
        for(String s:classSizeData){
            String []a = s.split(",");
            Level level=Level.getType(Integer.parseInt(a[0]));
            int capacity=Integer.parseInt(a[2]);
            
            instance.classSizeMap.put(level, capacity);
        }
        
    }
    private static void distribute() {

    }
    
    
    
    
    
    

    public static Map<Level, List<Classroom>> getLevelMap() {
        return levelMap;
    }

    public static Map<Classroom, List<Group>> getClassMap() {
        return classMap;
    }

    public static Map<Student, List<ImmunizationRecord>> getStudentMap() {
        return studentMap;
    }

    public static Map<Level, Integer> getGroupSizeMap() {
        return groupSizeMap;
    }

    public static Map<Level, Integer> getClassSizeMap() {
        return classSizeMap;
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public static List<Immunization> getImmunizationList() {
        return immunizationList;
    }
    

}
