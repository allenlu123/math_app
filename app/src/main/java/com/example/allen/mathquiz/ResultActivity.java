package com.example.allen.mathquiz;

/**
 * Created by Allen on 6/12/2016.
 */
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class ResultActivity extends Activity {
    private Button btn,playagain;
    private String user;
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textResult = (TextView) findViewById(R.id.textResult);
        btn = (Button) findViewById(R.id.btn);
        playagain = (Button) findViewById(R.id.playagain);
        Bundle b = getIntent().getExtras();
        user = b.getString("user");
        int score = b.getInt("score");
        UserGet db = new UserGet(this);
        int highscore = Integer.parseInt(db.getScore(user));
        if(highscore >= score) {
            textResult.setText("Your score is " + score + ". Nice job, " + user + ".");
        }
        else {
            db.updateUser(user,Integer.toString(score));
            textResult.setText("Your score is " + score + ". Nice job, " + user + ", you beat your high score!");
        }
        db.updateLeader(user,score);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,QuestionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
