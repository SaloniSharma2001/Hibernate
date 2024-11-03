package com.tut;

public class FetchDemo {
     public static void main(String[] args) {
        //Example of get or load method
         //This is not a recommended way to get a session factory in a real-world application
         //Instead it is adivsable to make connection provider as we used to do in JDBC, by making a singleton class
         Configuration configuration = new Configuration().configure("hibernate.cfg.xml");  // Load Hibernate configuration file
         SessionFactory factory = configuration.buildSessionFactory();
         Session session = factory.openSession();  // Open a new session
         // Fetch data from the database hence we don't need transaction management, only in the case of Insert, Update, Delete operations
         //Use of get() or load() methods for fetching data
         int id = 106;
         Student student = (Student) session.get(Student.class, id);
         // Student student = (Student) session.load(Student.class, id);
         System.out.println("Student: " + student);

         Address address = (Address) session.get(Address.class, id);
         System.out.println("address: " + address.getStreet() + ", " + address.getCity());
         session.close();  // Close the session when done with it
         factory.close();  // Close the session factory when the application is done with it
    }
}
//We need to give it the entity type (Object of which class we want to fetch) and the id (identifier/primarykey) of the object we want to fetch