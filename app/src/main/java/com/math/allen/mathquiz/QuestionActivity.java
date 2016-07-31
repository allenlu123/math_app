package com.math.allen.mathquiz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class QuestionActivity extends Activity {
    private Question currQ = new Question();
    private int score = 0;
    private TextView txtQuestion,times,scored,name,highscored;
    private Button button1,button2,button3;
    private CounterClass timer;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        user = bundle.getString("user");
        name = (TextView) findViewById((R.id.userName));
        name.setText("User : " + user);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        scored = (TextView) findViewById(R.id.currScore);
        UserGet db = new UserGet(this);
        String highscore = db.getScore(user);
        highscored = (TextView) findViewById(R.id.score);
        highscored.setText("Your Record : " + highscore);
        times = (TextView) findViewById(R.id.timers);
        setQuestionView();
        timer.start();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswerStr(button1.getText().toString());
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswerStr(button2.getText().toString());
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswerStr(button3.getText().toString());
            }
        });
    }
    public void getAnswerStr(String ans) {
        if(currQ.getAnswer().equals(ans)) {
            timer.cancel();
            score++;
            scored.setText("Score : " + score);
        }
        else {
            timer.cancel();
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            b.putString("user", user);
            intent.putExtras(b);
            startActivity(intent);
            finish();
            return;
        }
        setQuestionView();
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            times.setText("Time is up");
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            b.putString("user", user);
            intent.putExtras(b);
            startActivity(intent);
            finish();
            return;
        }
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%01d.%03d",
                    millis/1000,
                    millis % 1000);
            System.out.println(hms);
            times.setText(hms);
        }
    }

    private void setQHelp(int ans, Random r) {
        currQ.setAnswer(Integer.toString(ans));
        int but = r.nextInt(6);
        int bool;
        int range;
        int fake;
        switch(but) {
            case 0:
                currQ.setOptA(Integer.toString(ans));
                bool = r.nextInt(2);
                range = r.nextInt(10) + 1;
                if(bool > 0) {
                    fake = ans + range;
                    currQ.setOptB(Integer.toString(fake));
                }
                else {
                    fake = ans - range;
                    currQ.setOptB(Integer.toString(fake));
                }
                if(fake < range) {
                    currQ.setOptC(Integer.toString(ans + 10));
                }
                else {
                    currQ.setOptC(Integer.toString(ans - 10));
                }
                break;
            case 1:
                currQ.setOptA(Integer.toString(ans));
                bool = r.nextInt(2);
                range = r.nextInt(10) + 1;
                if(bool > 0) {
                    fake = ans + range;
                    currQ.setOptC(Integer.toString(fake));
                }
                else {
                    fake = ans - range;
                    currQ.setOptC(Integer.toString(fake));
                }
                if(fake < range) {
                    currQ.setOptB(Integer.toString(ans + 10));
                }
                else {
                    currQ.setOptB(Integer.toString(ans - 10));
                }
                break;
            case 2:
                currQ.setOptB(Integer.toString(ans));
                bool = r.nextInt(2);
                range = r.nextInt(10) + 1;
                if(bool > 0) {
                    fake = ans + range;
                    currQ.setOptA(Integer.toString(fake));
                }
                else {
                    fake = ans - range;
                    currQ.setOptA(Integer.toString(fake));
                }
                if(fake < range) {
                    currQ.setOptC(Integer.toString(ans + 10));
                }
                else {
                    currQ.setOptC(Integer.toString(ans - 10));
                }
                break;
            case 3:
                currQ.setOptB(Integer.toString(ans));
                bool = r.nextInt(2);
                range = r.nextInt(10) + 1;
                if(bool > 0) {
                    fake = ans + range;
                    currQ.setOptC(Integer.toString(fake));
                }
                else {
                    fake = ans - range;
                    currQ.setOptC(Integer.toString(fake));
                }
                if(fake < range) {
                    currQ.setOptA(Integer.toString(ans + 10));
                }
                else {
                    currQ.setOptA(Integer.toString(ans - 10));
                }
                break;
            case 4:
                currQ.setOptC(Integer.toString(ans));
                bool = r.nextInt(2);
                range = r.nextInt(10) + 1;
                if(bool > 0) {
                    fake = ans + range;
                    currQ.setOptA(Integer.toString(fake));
                }
                else {
                    fake = ans - range;
                    currQ.setOptA(Integer.toString(fake));
                }
                if(fake < range) {
                    currQ.setOptB(Integer.toString(ans + 10));
                }
                else {
                    currQ.setOptB(Integer.toString(ans - 10));
                }
                break;
            case 5:
                currQ.setOptC(Integer.toString(ans));
                bool = r.nextInt(2);
                range = r.nextInt(10) + 1;
                if(bool > 0) {
                    fake = ans + range;
                    currQ.setOptB(Integer.toString(fake));
                }
                else {
                    fake = ans - range;
                    currQ.setOptB(Integer.toString(fake));
                }
                if(fake < range) {
                    currQ.setOptA(Integer.toString(ans + 10));
                }
                else {
                    currQ.setOptA(Integer.toString(ans - 10));
                }
                break;
            default:
                break;
        }
    }

    private void setQuestionView() {
        Random r = new Random();
        int num1;
        int num2;
        if(score < 5) {
            num1 = r.nextInt(10);
            num2 = r.nextInt(10);
        }
        else {
            num1 = r.nextInt(15);
            num2 = r.nextInt(15);
        }
        int opt;
        if(num2 > 0 && num1 % num2 == 0) opt = r.nextInt(4);
        else opt = r.nextInt(3);
        String op = "";
        int ans;
        switch(opt) {
            case 0:
                op = "+";
                ans = num1 + num2;
                setQHelp(ans,r);
                break;
            case 1:
                op = "-";
                ans = num1 - num2;
                setQHelp(ans,r);
                break;
            case 2:
                op = "x";
                ans = num1 * num2;
                setQHelp(ans,r);
                break;
            case 3:
                op = "\u00F7";
                ans = num1/num2;
                setQHelp(ans,r);
                break;
            default:
                op = "Error";
                break;
        }
        currQ.setQuestion(Integer.toString(num1) + " " + op + " " + Integer.toString(num2) + " = ?");
        txtQuestion.setText(currQ.getQuestion());
        button1.setText(currQ.getOptA());
        button2.setText(currQ.getOptB());
        button3.setText(currQ.getOptC());
        times.setText("00:00:05");
        int time;
        if(score < 10) {
            time = 5000;
        }
        else if(score < 20) {
            time = 4000;
        }
        else if(score < 30) {
            time = 3000;
        }
        else if(score < 40) {
            time = 2500;
        }
        else if(score < 50) {
            time = 2000;
        }
        else {
            time = 1500;
        }
        timer = new CounterClass(time, 1);
        timer.start();
    }
}
