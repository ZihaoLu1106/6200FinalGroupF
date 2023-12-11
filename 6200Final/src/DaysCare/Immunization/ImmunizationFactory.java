/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Immunization;

/**
 *
 * @author JANFAN
 */
public enum ImmunizationFactory {
    IMMUNIZATION;
    private ImmunizationFactory(){}
    public  Immunization getImmunization(String s){
        return new Immunization( s);
    }
    
}
