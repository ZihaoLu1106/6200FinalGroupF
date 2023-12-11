/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Organization;

import DaysCare.Person.Student;
import DaysCare.Person.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JANFAN
 */
public class Group {
    private Level level;
    private int groupNum;
    private int capacity;
    private Teacher teacher;
    private List<Student>studentList;
    
    public Group(int groupNum,int capacity,Teacher teacher,List<Student>studentList){
        this.groupNum=groupNum;
        this.capacity=capacity;
        this.teacher=teacher;
        this.studentList=studentList;
    }
    public Group(int groupNum,int capacity,Teacher teacher){
        this.groupNum=groupNum;
        this.capacity=capacity;
        this.teacher=teacher;
        studentList=new ArrayList<>();
    }

    public Teacher getTeacher() {
        return teacher;
    }
    
    public boolean addStudent(Student s){
        if(studentList.size()>=capacity){
            return false;
        }else{
            studentList.add(s);
            return true;
        }
    }
    public List<Student> getStudentList() {
        return studentList;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getGourpNum() {
        return groupNum;
    }

    public int getCapacity() {
        return capacity;
    }
    @Override
    public String toString(){
        return String.valueOf(groupNum);
    }
    
    
    
    
    
    
    
    
    
}
