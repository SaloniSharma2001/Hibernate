package com.tut;

@Entity
//@Entity(name = "STUDENT") in here we are specifying the table name in the database
@Table(name = "students_deatails")
public class Student {
//Marks Id as primary key
    @Id
    private int id;
    private String name;
    private String city;

    public Student(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Student(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return this.id + " : " + this.name + " : " + this.city;
    }
}
