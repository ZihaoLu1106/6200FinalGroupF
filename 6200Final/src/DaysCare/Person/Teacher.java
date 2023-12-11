/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Person;

/**
 *
 * @author JANFAN
 */
public class Teacher implements PersonAPI {

    String name;
    int age;
    double credit;

    public Teacher(String s) {
        
        String[] a = s.split(",");
        this.name = a[0];
        a[1]=a[1].replace(" ", "");
        this.age = Integer.parseInt(a[1]);
        a[2]=a[2].replace(" ", "");
        this.credit = Double.parseDouble(a[2]);
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAge() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setAge(int age) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
    
    @Override
    public String toString(){
        return "Name:"+this.name+" age:"+this.age+" gpa:"+this.credit;
    }
}
