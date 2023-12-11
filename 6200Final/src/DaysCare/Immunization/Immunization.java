/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Immunization;

/**
 *
 * @author JANFAN
 */
public class Immunization {
    String immunizationName;
    int duartion;

    Immunization(String s) {
        String []a = s.split(",");
	this.immunizationName = a[0];
	this.duartion = Integer.parseInt(a[1]);
    }

    public String getImmunizationName() {
        return immunizationName;
    }

    public void setImmunizationName(String immunizationName) {
        this.immunizationName = immunizationName;
    }

    public int getDuartion() {
        return duartion;
    }

    public void setDuartion(int duartion) {
        this.duartion = duartion;
    }
    @Override
    public String toString(){
        return "Immunization Name:"+this.immunizationName+" Duration:"+this.duartion;
    }
    
    
}
