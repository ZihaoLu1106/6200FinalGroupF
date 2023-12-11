/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JANFAN
 */
public class Classroom {
    Level level;
    int classNum;
    int capacity;
    List<Group>gourpList;
    public Classroom(){
        gourpList=new ArrayList<>();
    }
    public Classroom(int capacity,int classNum,List<Group>gourpList){
        this.capacity=capacity;
        this.classNum=classNum;
        this.gourpList=gourpList;
    }

    public int getClassNum() {
        return classNum;
    }
    public boolean addGroup(Group g){
        if(gourpList.size()>=capacity){
            return false;
        }else{
            gourpList.add(g);
            return true;
        }
    }

    public List<Group> getGourpList() {
        return gourpList;
    }

    public int getCapacity() {
        return capacity;
    }
    
    
    
}
