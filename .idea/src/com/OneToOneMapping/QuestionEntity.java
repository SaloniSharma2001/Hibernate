package com.OneToOneMapping;

@Entity
public class QuestionEntity {
    public QuestionEntity(int questionid, String question, AnswerEntity answer) {
        this.questionid = questionid;
        this.question = question;
        this.answer = answer;
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

    public AnswerEntity getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionid;
    @Column(name = "question")
    private String question;

    //This will add a one-to-one relationship with AnswerEntity and QuestionEntity will have foreign key of answer_id
    @OneToOne
    @JoinColumn(name = "a_id", referencedColumnName = "answer_id")
    private AnswerEntity answer;
}