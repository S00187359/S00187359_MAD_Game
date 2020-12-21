package edu.simrandhillon.gameprojectpage1;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class PlayScreenActivity extends AppCompatActivity implements SensorEventListener {

    private final double NORTH_MOVE_FORWARD = 11;     // upper mag limit
    private final double NORTH_MOVE_BACKWARD = 8;      // lower mag limit
    private final double EAST_MOVE_FORWARD = -7;
    private final double EAST_MOVE_BACKWARD = 0;
    private final double WEST_MOVE_FORWARD = 7;
    private final double WEST_MOVE_BACKWARD = 0;
    private final double SOUTH_MOVE_FORWARD = 9;
    private final double SOUTH_MOVE_BACKWARD = 6;

    boolean highLimit = false;      // detect high limit
    boolean highLimit_SOUTH = false;      // detect high limit
    boolean highLimit_WEST = false;      // detect high limit
    boolean highLimit_EAST = false;      // detect high limit
    int counter = 0;                // step counter
    int counterNorth = 0;                // step counter
    int counterSouth = 0;                // step counter
    int counterEast = 0;                // step counter
    int counterWest = 0;                // step counter

    Button b2Red, b2Blue, b2Yellow, b2Green, fb2;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        b2Red = findViewById(R.id.btn2Red);
        b2Blue = findViewById(R.id.btn2Blue);
        b2Yellow = findViewById(R.id.btn2Yellow);
        b2Green = findViewById(R.id.btn2Green);

        b2Blue.getBackground().setAlpha(100);
        b2Red.getBackground().setAlpha(100);
        b2Green.getBackground().setAlpha(100);
        b2Yellow.getBackground().setAlpha(100);

        // int[] arrayB = extras.getIntArray("numbers");
        // 1 2 1 1  (N N W N)


        // we are going to use the sensor service
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }


    /*
     * When the app is brought to the foreground - using app on screen
     */
    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*
     * App running but not on screen - in the background
     */
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);    // turn off listener to save power
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];


        // Can we get a north movement
        // you need to do your own mag calculating
        if ((x > NORTH_MOVE_FORWARD) && (highLimit == false)) {
            highLimit = true;
            b2Blue.getBackground().setAlpha(255);

        }
        if ((x < NORTH_MOVE_BACKWARD) && (highLimit == true)) {
            counterNorth++;

            b2Blue.getBackground().setAlpha(100);
            highLimit = false;

        }

        if ((x < SOUTH_MOVE_FORWARD) && (highLimit_SOUTH == false)) {
            highLimit_SOUTH = true;
            b2Yellow.getBackground().setAlpha(255);

        }
        if ((x > SOUTH_MOVE_BACKWARD) && (highLimit_SOUTH == true)) {
            counterSouth++;
            b2Yellow.getBackground().setAlpha(100);
            highLimit_SOUTH = false;
        }

        if ((y < EAST_MOVE_FORWARD) && (highLimit_EAST == false)) {
            highLimit_EAST = true;
            b2Green.getBackground().setAlpha(255);

        }
        if ((y > EAST_MOVE_BACKWARD) && (highLimit_EAST == true)) {
            counterEast++;

            b2Green.getBackground().setAlpha(100);;
            highLimit_EAST = false;
        }

        if ((y > WEST_MOVE_FORWARD) && (highLimit_WEST == false)) {
            highLimit_WEST = true;
            b2Red.getBackground().setAlpha(255);
        }
        if ((y < WEST_MOVE_BACKWARD) && (highLimit_WEST == true)) {
            counterWest++;
            b2Red.getBackground().setAlpha(100);

            highLimit_WEST = false;
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not used
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private void flashButton(Button button) {
        fb2 = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                fb2.setPressed(true);
                fb2.invalidate();
                fb2.performClick();
                fb2.getBackground().setAlpha(100);
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        fb2.setPressed(false);
                        fb2.getBackground().setAlpha(255);
                        fb2.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);

            } // end runnable
        };
    }

}