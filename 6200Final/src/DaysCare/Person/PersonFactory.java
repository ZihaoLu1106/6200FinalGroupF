/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaysCare.Person;

/**
 *
 * @author JANFAN
 */
public enum PersonFactory {
    STUDENT{
    
        @Override
        public PersonAPI getPerson(String s) {
            return new Student(s);
        }
    },
    TEACHER{
    
        @Override
        public PersonAPI getPerson(String s) {
            return new Teacher(s);
        }
    };

    private PersonFactory() {
    }
    public abstract PersonAPI getPerson(String s);

    

}
