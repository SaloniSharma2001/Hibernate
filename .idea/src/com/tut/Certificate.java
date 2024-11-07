package com.tut;

@Embeddable
public class certificate {
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public certificate(String course, String duration) {
        this.course = course;
        this.duration = duration;
    }

    public certificate() {
        super();
    }

    private String course;
    private String duration;
}