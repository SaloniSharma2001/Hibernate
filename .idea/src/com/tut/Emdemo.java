package com.tut;

//We are using Certificate class whose table is not determined in the database. If we want to use it, then we either need to define the table structure in the database OR make the class embeddable using annotation.
public class Emdemo {

    Configuration configuration = new Configuration().configure("hibernate.cfg.xml");  // Load Hibernate configuration file
    SessionFactory factory = configuration.buildSessionFactory();
    Session session = factory.openSession();  // Open a new session
    Student student = new Student();
        student.setId(25);
        student.setName("John Doe");
        student.setCity("Delhi");
    Certification certi = new Certification();
    certi.setCertificationType("Java");
    certi.setDuration("2 Months");
    student.setCertificate(certi);

    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();  // Begin transaction

    //Object save
    session.save(student);
    transaction.commit();  // Commit the transaction
    session.close();  // Close the session
    factory.close();  // Close the session factory when the application is done with it
}