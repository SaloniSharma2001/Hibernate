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

    public static void main(String[] args) {
        // Do nothing
    }
}