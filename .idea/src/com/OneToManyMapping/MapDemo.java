package com.OneToOneMapping;

public class MapDemo {

    puublic static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");  // Load Hibernate configuration file
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();  // Open a new session

        //Creating question object
        Question q1 = new Question();
        q1.setQuestionId(25);
        q1.setQuestion("What is java?");

        //Creating answer object
        Answer a1 = new Answer();
        a1.setAnswerId(343);
        a1.setAnswer("Java is a programming language");
        a1.setQuestion(q1);

        Answer a2 = new Answer();
        a2.setAnswerId(343);
        a2.setAnswer("With the help of java we can create softwares");
        a2.setQuestion(q1);

        List<Answer> answers = new ArrayList<Answer>();
        answers.add(a1);
        answer.add(a2);

        q1.setAnswers(answers);

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();  // Begin transaction

        //Object save
        session.save(q1 );
        session.save(a1 );
        session.save(a2 );

        transaction.commit();  // Commit the transaction
        //fetching the object
        Question question = (Question) session.get(Question.class, 25);
        System.out.println("Question ID: " + question.getQuestionId());
        System.out.println("Question: " + question.getQuestion());
        //Depending upon the fetch = FetchType.EAGER or FetchType.LAZY data will be loaded
        //By default lazy loading happens
        System.out.println("Answers Size: " + question.getAnswers().size());
        for(Answer a: question.getAnswer()){
            System.out.println("Answer: " + a.getAnswers());
        }
        session.close();  // Close the session
        factory.close();  // Close the session factory when the application is done with it
    }
}