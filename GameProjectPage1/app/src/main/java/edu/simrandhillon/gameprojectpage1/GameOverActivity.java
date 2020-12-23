package edu.simrandhillon.gameprojectpage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity
{

    TextView t_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        t_score = findViewById(R.id.txtScore);
        int uScr = getIntent().getIntExtra("userScore" , -1 );
        t_score.setText(String.valueOf(uScr));
    }

    public void onRetry(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void doHighScore(View view)
    {
        Intent highscore = new Intent(this, HighScoreActivity.class);
        startActivity(highscore);
    }
}