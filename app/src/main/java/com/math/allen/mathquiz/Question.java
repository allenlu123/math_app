package com.math.allen.mathquiz;

import android.app.Activity;

/**
 * Created by Allen on 6/12/2016.
 */
public class Question extends Activity {
    private String question;
    private String answer;
    private String OptA;
    private String OptB;
    private String OptC;

    public Question() {
        question = "";
        answer = "";
        OptA = "";
        OptB = "";
        OptC = "";
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOptA() {
        return OptA;
    }

    public String getOptB() {
        return OptB;
    }

    public String getOptC() {
        return OptC;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptA(String optA) {
        OptA = optA;
    }

    public void setOptB(String optB) {
        OptB = optB;
    }

    public void setOptC(String optC) {
        OptC = optC;
    }
}
