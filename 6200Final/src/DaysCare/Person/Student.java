/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Person;

import java.util.Objects;

/**
 *
 * @author JANFAN
 */
public class Student implements PersonAPI {
    String name;
    int age;
    double grade;
    public Student(String s){
        
        String []a = s.split(",");
	this.name = a[0];
        a[1]=a[1].replace(" ", "");
	this.age = Integer.parseInt(a[1]);
        a[2]=a[2].replace(" ", "");
	this.grade = Double.parseDouble(a[2]);
    }
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }
    
    
    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(grade, student.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, grade);
    }
    

    
    
}
