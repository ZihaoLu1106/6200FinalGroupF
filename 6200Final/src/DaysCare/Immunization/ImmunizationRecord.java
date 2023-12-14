/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Immunization;

import DaysCare.Person.Student;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author JANFAN
 */
public class ImmunizationRecord {
    Immunization immunization;
    Student student;
    String date;
    boolean isExpire;
    String expireDate;
    public ImmunizationRecord(Immunization immunization,Student student,String date){
        this.immunization=immunization;
        this.student=student;
        this.date=date;
        this.date=this.date.replace(" ", "");
        this.expireDate="";
        checkifExpire();
    }

    private void checkifExpire() {
        LocalDate from=LocalDate.now();
        
        String []a = date.split("-");
        LocalDate lastdate = LocalDate.of(Integer.parseInt(a[2]), Integer.parseInt(a[0]), Integer.parseInt(a[1]));
        //System.out.println("lastdate: "+lastdate);
        int result=(int) ChronoUnit.DAYS.between(lastdate, from);
        int duration=immunization.duartion;
        if(result>=duration*365)
            isExpire=false;
        else
            isExpire=true;
        
        LocalDate nextdate=lastdate.plusDays(duration*365);
        //System.out.println("nextdate: "+nextdate);
        this.expireDate=String.valueOf(nextdate.getMonthValue())+"-"+String.valueOf(nextdate.getDayOfMonth())+"-"+String.valueOf(nextdate.getYear());
    }
    public void update(){
        LocalDate today=LocalDate.now();
        int day=today.getDayOfMonth();
        int month=today.getMonthValue();
        int year=today.getYear();
        this.date=String.valueOf(month)+"-"+String.valueOf(day)+"-"+String.valueOf(year);
        checkifExpire();
    }






    public String getDate() {
        return date;
    }

    public boolean isIsExpire() {
        return isExpire;
    }

    public String getExpireDate() {
        return expireDate;
    }
    
    @Override
    public String toString(){
        return this.immunization.toString();
    }
    public String toFileString() {
        // The file should have the student's name and the dates for each immunization
        return student.getName() + ", " + date; // You can add more details as needed
    }
}
