package edu.simrandhillon.gameprojectpage1;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    Button bRed, bBlue, bYellow, bGreen, fb;
    int sequenceCount = 4, n = 0;
    private Object mutex = new Object();
    int[] gameSequence = new int[120];
    int arrayIndex = 0;



    CountDownTimer ct = new CountDownTimer(6000,  1500) {

        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);

            oneButton();

            //here you can have your logic to set text to edittext
        }

        public void onFinish() {
            //mTextField.setText("done!");
            // we now have the game sequence

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));



            // start next activity


            // put the sequence into the next activity
            // stack overglow https://stackoverflow.com/questions/3848148/sending-arrays-with-intent-putextra
            Intent i = new Intent(MainActivity.this, PlayScreenActivity.class);
            // i.putExtra("numbers", array);
            startActivity(i);

            // start the next activity
            //int[] arrayB = extras.getIntArray("numbers");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bRed = findViewById(R.id.btnRed);
        bBlue = findViewById(R.id.btnBlue);
        bYellow = findViewById(R.id.btnYellow);
        bGreen = findViewById(R.id.btnGreen);
    }

    public void doPlay(View view)
    {
        if (arrayIndex == 0)
        {
            ct.start();
        }
        else if (arrayIndex >= 3 && arrayIndex <= 8 )
        {
            ct = new CountDownTimer(9000, 1500)
            {
                @Override
                public void onTick(long millisUntilFinished)
                {
                    oneButton();
                }

                @Override
                public void onFinish()
                {
                    for (int i = 0; i< arrayIndex; i++)
                        Log.d("game sequence", String.valueOf(gameSequence[i]));


                    // start next activity


                    // put the sequence into the next activity
                    // stack overglow https://stackoverflow.com/questions/3848148/sending-arrays-with-intent-putextra
                    Intent i = new Intent(MainActivity.this, PlayScreenActivity.class);
                    // i.putExtra("numbers", array);
                    startActivity(i);

                    // start the next activity
                    //int[] arrayB = extras.getIntArray("numbers");
                }
            };

            ct.start();
        }
        else if (arrayIndex >= 9)
        {
            ct = new CountDownTimer(12000, 1500)
            {
                @Override
                public void onTick(long millisUntilFinished)
                {
                    oneButton();
                }

                @Override
                public void onFinish()
                {
                    for (int i = 0; i< arrayIndex; i++)
                        Log.d("game sequence", String.valueOf(gameSequence[i]));


                    // start next activity


                    // put the sequence into the next activity
                    // stack overglow https://stackoverflow.com/questions/3848148/sending-arrays-with-intent-putextra
                    Intent i = new Intent(MainActivity.this, PlayScreenActivity.class);
                    // i.putExtra("numbers", array);
                    startActivity(i);

                    // start the next activity
                    //int[] arrayB = extras.getIntArray("numbers");
                }
            };

            ct.start();
        }




    }


    private void oneButton() {

        Toast.makeText(this, "Index: = " + arrayIndex, Toast.LENGTH_SHORT).show();
        n = getRandom(sequenceCount);

        switch (n) {
            case 1:
                flashButton(bBlue);
                gameSequence[arrayIndex++] = BLUE;
                break;
            case 2:
                flashButton(bRed);
                gameSequence[arrayIndex++] = RED;
                break;
            case 3:
                flashButton(bYellow);
                gameSequence[arrayIndex++] = YELLOW;
                break;
            case 4:
                flashButton(bGreen);
                gameSequence[arrayIndex++] = GREEN;
                break;
            default:
                break;
        }   // end switch



    }

    //
    // return a number between 1 and maxValue
    private int getRandom(int maxValue) {
        return ((int) ((Math.random() * maxValue) + 1));
    }

    private void flashButton(Button button) {
        fb = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run()
            {

                fb.setPressed(true);
                fb.invalidate();
                fb.performClick();
                fb.getBackground().setAlpha(100);
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        fb.setPressed(false);
                        fb.getBackground().setAlpha(255);
                        fb.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);

            } // end runnable
        };
        handler.postDelayed(r, 600);
    }

}