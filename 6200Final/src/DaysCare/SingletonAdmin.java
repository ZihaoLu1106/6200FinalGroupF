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
    static List<Teacher> teacherList;//used to store all teachers
    static List<Immunization> immunizationList;//use to store all immunizations
    private static final SingletonAdmin instance = new SingletonAdmin();
    
    static int groupNum;
    static int classNum;

    private SingletonAdmin() {
        levelMap=new HashMap<>();
        classMap=new HashMap<>();
        studentMap=new HashMap<>();
        groupSizeMap=new HashMap<>();
        classSizeMap=new HashMap<>();
        
        studentList = new ArrayList<>();
        teacherList = new ArrayList<>();
        
        groupNum=0;
        classNum=0;

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
        //5.initailize hashmaps
        initializeMap();
        //6.distribution student and teacher to each group and class
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
            //System.out.println(instance.studentList.get(i));
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
    
    private static void initializeMap(){
        for (Map.Entry<Level, Integer> classSizeset
                                          : classSizeMap.entrySet()) {
            Group group=createGroup(groupSizeMap.get(classSizeset.getKey()));
            Classroom classroom=createClassroom(classSizeset.getValue());
            List<Classroom>tempClassroomList=new ArrayList<>();
            tempClassroomList.add(classroom);
            //
            levelMap.put(classSizeset.getKey(), tempClassroomList);
            classroom.addGroup(group);
            
            
        }
    }
    private static Group createGroup(int capacity){
        Teacher teacher=teacherList.get(groupNum);
        Group group=new Group(groupNum,capacity,teacher);
        groupNum++;
        return group;
    }
    private static Classroom createClassroom(int capacity){
        Classroom classroom =new Classroom(capacity,classNum);
        classNum++;
        return classroom;
    }
    
    private static void distribute() {
        //distribute student to group with teacher
        for(Student s:studentList){
            System.out.println(s);
            //get the student's level
            Level level=Level.getType(s.getAge());// we get level
            //get the student's group size
            int groupSize=groupSizeMap.get(level);
            //get the student's class size
            int classSize=classSizeMap.get(level);
            //get the student's classroom list
            List<Classroom>classroomList=levelMap.get(level);
            //get the student's group list
            List<Group>groupList=classMap.get(classroomList.get(0));
            //get the student's teacher list
            List<Teacher>teacherList=instance.teacherList;
            
            
            //find space
            //check if a class is full by group
            //search every last group in each classroom to check if has space left
            Classroom targetClassroom=null;
            Group targetGroup=null;
            boolean isFindClass=false;
            boolean isFindGroup=false;
            for(int i=0;i<classroomList.size();i++){
                Classroom tempClass=classroomList.get(i);
                //check if the classroom is full
                if(tempClass.getGourpList().size()>=tempClass.getCapacity()){//reach max capacity of group
                    //check the last group if it has space
                    Group tempGroup=tempClass.getGourpList().get(tempClass.getGourpList().size()-1);
                    if(tempGroup.getStudentList().size()>=tempGroup.getCapacity()){//if the last group is also full
                        continue;//next classroom
                    }
                    else{//the last group still has space
                        targetClassroom=tempClass;
                        targetGroup=tempGroup;
                        isFindClass=true;
                        isFindGroup=true;
                    }
                }else{//if the classroom is not full
                    //check the last group first
                    Group tempGroup=tempClass.getGourpList().get(tempClass.getGourpList().size()-1);//last group
                    if(tempGroup.getStudentList().size()>=tempGroup.getCapacity()){//if the last group is full
                        //create new group
                        Group newGroup=createGroup(groupSizeMap.get(level));
                        tempClass.addGroup(newGroup);
                       // classMap.get(tempClass).add(newGroup);
                        targetClassroom=tempClass;
                        targetGroup=tempGroup;
                        isFindClass=true;
                        isFindGroup=true;
                    }
                    else{//the last group still has space
                        targetClassroom=tempClass;
                        targetGroup=tempGroup;
                        isFindClass=true;
                        isFindGroup=true;
                    }
                }
            }
            
            if(isFindClass==false){//if all classroom is full, we need to create a new classroom
                Classroom newClassroom=createClassroom(classSizeMap.get(level));//create new classroom
                levelMap.get(level).add(newClassroom);
                Group newGroup=createGroup(groupSizeMap.get(level));//create new group
                newClassroom.addGroup(newGroup);
                //classMap.put(newClassroom,newClassroom.getGourpList());
                
                targetClassroom=newClassroom;
                targetGroup=newGroup;
            }
            
            //now we get the classroom and group we need
            //put the student into the group;
            
            if(targetGroup.addStudent(s)){
                System.out.println("Success student "+s+" group:"+targetGroup.getGourpNum()+" class:"+targetClassroom.getClassNum());
            }
    }
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
