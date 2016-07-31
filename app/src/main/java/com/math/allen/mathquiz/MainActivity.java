package com.math.allen.mathquiz;

/**
 * Created by Allen on 6/12/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    private TextView input,leadname,leadscore;
    private Button button1;
    private String s = "";
    private UserGet db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        db = new UserGet(this);
        String[] leader = db.getLeader();
        leadname = (TextView) findViewById(R.id.currLead);
        leadscore = (TextView) findViewById(R.id.leadScore);
        leadname.setText("Current Leader: " + leader[0]);
        leadscore.setText("Score: " + leader[1]);
        input = (TextView) findViewById(R.id.editText);
        button1 = (Button) findViewById(R.id.button3);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String temp = input.getText().toString();
                    if(temp.replaceAll(" ","").equals("")) s = "";
                    else {
                        int i = 0;
                        int j = temp.length() - 1;
                        while(temp.charAt(i) == ' ') {
                            i++;
                        }
                        while(temp.charAt(j) == ' ') {
                            j--;
                        }
                        s = temp.substring(i,j+1);
                    }
                    handled = true;
                }
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(input.getWindowToken(), 0);
                return handled;
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = input.getText().toString();
                if(temp.replaceAll(" ","").equals("")) s = "";
                else {
                    int i = 0;
                    int j = temp.length() - 1;
                    while(temp.charAt(i) == ' ') {
                        i++;
                    }
                    while(temp.charAt(j) == ' ') {
                        j--;
                    }
                    s = temp.substring(i,j+1);
                }
                if (s.equals("")) {
                    input.setHint("Please input a username");
                } else {
                    if(!db.isUser(s)) {
                        db.insertUser(s);
                    }
                    Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                    Bundle b = new Bundle();
                    b.putString("user", s);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        });

    }


}

