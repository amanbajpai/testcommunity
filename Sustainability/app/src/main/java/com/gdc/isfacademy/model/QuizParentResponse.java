package com.gdc.isfacademy.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ashishthakur on 27/11/18.
 */

public class QuizParentResponse extends CommonResponse implements Serializable{

    ArrayList<QuestionAnswerBean>qnas;

    public ArrayList<QuestionAnswerBean> getQnas() {
        return qnas;
    }

    public void setQnas(ArrayList<QuestionAnswerBean> qnas) {
        this.qnas = qnas;
    }
}
