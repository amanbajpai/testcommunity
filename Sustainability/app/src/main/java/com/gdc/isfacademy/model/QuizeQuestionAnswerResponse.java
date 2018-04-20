package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 16/4/18.
 */

public class QuizeQuestionAnswerResponse {

    String question;
    String answer;


    public QuizeQuestionAnswerResponse(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
