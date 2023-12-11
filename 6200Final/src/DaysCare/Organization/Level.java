/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Organization;

import java.util.List;

/**
 *
 * @author JANFAN
 */
public enum Level {
    LEVEL6to12,
    LEVEL13to24,
    LEVEL25to35,
    LEVEL36to47,
    LEVEL48to59,
    LEVEL60ANDUP;
    
    private Level(){}
    public static Level getType(int age){
        if(age<13)
            return LEVEL6to12;
        else if(age>=13&&age<25)
            return LEVEL13to24;
        else if(age>=25&&age<36)
            return LEVEL25to35;
        else if(age>=36&&age<48)
            return LEVEL36to47;
        else if(age>=48&&age<60)
            return LEVEL48to59;
        else
            return LEVEL60ANDUP;
    }
    

    
    

}
