package com.tut;

import org.hibernate.SessionFactory;

publuc class App{
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //Session factory is a thread-safe singleton
//        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");  // Load Hibernate configuration file
//        SessionFactory factory = configuration.buildSessionFactory();
        //We need not to declare the path of Hibernate configuration file in the constructor, if we have it in the classpath
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        System.out.println("Factory created");
        System.out.println("Factory closed?" factory.isClosed());

        //Create a new Student object and set its properties.
       Student st = new Student();
       //Then set the values in Student st object and save it to the database
        st.setId(25);
        st.setName("John Doe");
        st.setCity("Delhi");
        System.out.println("Student details: " + st);

        //Creating object of address class
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("New York");
        address.setIsOpen(true);
        address.setDateCreated(new Date());
        address.setZipcode(1234.345);

        //Reading Image
        //FileInputStream fileInputStream = new FileInputStream("path/to/your/image.jpg");
        //address.setImage(fileInputStream.readAllBytes());
        //OR TO MAKE IT LESS COMPLICATED, you can read the image as a byte array and store it in the database.
        //byte[] data = new byte[fileInputStream.available()];
        //fileInputStream.read(data);
        //address.setImage(data);
        st.setAddress(address); //Setting the address to the student object.

        //Flushing the changes to the database
        //session.flush();
        //Committing the transaction
        //session.commit();

        //After all the operations, we close the session
        //session.close();

        //We can also use the SessionFactory to get a new session
        //Session newSession = factory.openSession();

        //The following code is using the SessionFactory's getCurrentSession() method
        //It gives currently running session
        //Session session = factory.getCurrentSession();
        //Gives us a new session each time
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(st);
        session.save(address);
        session.getTransaction().commit();
        session.close();
        System.out.println("Done. Student saved successfully");

        System.out.println("Student saved successfully");
        System.out.println("Factory closed?" + factory.isClosed());
    }
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create a new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            // return configuration.buildSessionFactory();
            return org.hibernate.cfg.Configuration.configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}