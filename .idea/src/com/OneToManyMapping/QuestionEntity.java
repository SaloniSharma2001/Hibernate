package com.OneToOneMapping;

@Entity
public class QuestionEntity {
    public QuestionEntity(int questionid, String question, List<AnswerEntity> answers) {
        this.questionid = questionid;
        this.question = question;
        this.answers = answers;
    }

    public QuestionEntity() {
        super();
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionid;

    @ManyToOne
    @Column(name = "question")
    private String question;

    //This will add a one-to-many relationship with AnswerEntity
    //We got a new table mapped with question and answer primary key
    //We can make the mapping in question table  by doint the following
    @OneToMany(mappedBy = "question")
    private List<AnswerEntity> answers;
}