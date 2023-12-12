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
    public ImmunizationRecord(Immunization immunization,Student student,String date){
        this.immunization=immunization;
        this.student=student;
        this.date=date;
        this.date=this.date.replace(" ", "");
        checkifExpire();
    }

    private void checkifExpire() {
        LocalDate from=LocalDate.now();
        
        String []a = date.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(a[2]), Integer.parseInt(a[0]), Integer.parseInt(a[1]));
        int result=(int) ChronoUnit.DAYS.between(date, from);
        int duration=immunization.duartion;
        if(result>=duration*365)
            isExpire=false;
        else
            isExpire=true;
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
    
    @Override
    public String toString(){
        return this.immunization.toString();
    }
}
