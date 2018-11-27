package com.gdc.isfacademy.model;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 27/11/18.
 */

public class QuizParentResponse extends CommonResponse {

    ArrayList<QuestionAnswerBean>qnas;

    public ArrayList<QuestionAnswerBean> getQnas() {
        return qnas;
    }

    public void setQnas(ArrayList<QuestionAnswerBean> qnas) {
        this.qnas = qnas;
    }
}
