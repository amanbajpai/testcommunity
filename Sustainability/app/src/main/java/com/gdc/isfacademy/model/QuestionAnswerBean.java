package com.gdc.isfacademy.model;

import java.io.Serializable;

/**
 * Created by ashishthakur on 27/11/18.
 */

public class QuestionAnswerBean implements Serializable {
    String question;
    String answer;
    Options options;
    boolean isQuestionChecked=false;
    private int userSelectedAnswer = -1;
    boolean answered;
    boolean correct;

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setUserSelectedAnswer(int userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }

    public boolean isQuestionChecked() {
        return isQuestionChecked;
    }

    public void setQuestionChecked(boolean questionChecked) {
        isQuestionChecked = questionChecked;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
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

    public class Options {
        String a;
        String b;
        String c;
        String d;


        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }
    }


}