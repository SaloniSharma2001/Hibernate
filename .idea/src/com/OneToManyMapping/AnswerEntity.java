package com.OneToOneMapping;
public class AnswerEntity {
    @Id
    @Column(name = "answer_id")
    private int answerId;

    public AnswerEntity(int answerId, String answer) {
        this.answerId = answerId;
        this.answer = answer;
    }

    public AnswerEntity(int answerId, String answer) {
        super();
    }
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private String answer;

    public QuestionEntity getQuestion() {
        return question;
    }

    @ManyToOne // mappedBy is used to specify the field in the QuestionEntity that is the owner of the relationship. In this case, it's the answers field.'
    private QuestionEntity question;
}