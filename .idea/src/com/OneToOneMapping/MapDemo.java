package com.OneToOneMapping;

public class MapDemo {

    puublic static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");  // Load Hibernate configuration file
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();  // Open a new session

        //Creating question object
        Question Q1 = new Question();
        q1.setQuestionId(25);
        q1.setQuestion("What is java?");

        //Creating answer object
        Answer A1 = new Answer();
        A1.setAnswerId(343);
        A1.setAnswer("Java is a programming language");

        q1.setAnswer(A1);  // Setting the answer to the question object

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();  // Begin transaction

        //Object save
        session.save(q1 );
        transaction.commit();  // Commit the transaction
        //fetching the object
        Question question = (Question) session.get(Question.class, 25);
        System.out.println("Question: " + question.getQuestion());
        System.out.println("Answer: " + question.getAnswer().getAnswer());
        session.close();  // Close the session
        factory.close();  // Close the session factory when the application is done with it
    }
}