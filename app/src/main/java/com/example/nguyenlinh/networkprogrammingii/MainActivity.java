package com.example.nguyenlinh.networkprogrammingii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main Screen
 * Network Programming II
 *
 * @author nguyenlinh
 * @version 1.0
 * @since 2017-2-10
 */
public class MainActivity extends AppCompatActivity {

    private Button mButton01, mButton02, mButton03, mButton04, mButton05;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton01 = (Button)findViewById(R.id.button01);
    }

    /*
    fuction go to Activity other
     */
    public void goToExercises(View clickedButton){
        if(clickedButton.getId()==R.id.button01){
            Intent intent = new Intent(MainActivity.this,Exercises1Activity.class);
            MainActivity.this.startActivity(intent);
        }

        if(clickedButton.getId()==R.id.button02){
            Intent intent = new Intent(MainActivity.this,Exercises2Activity.class);
            MainActivity.this.startActivity(intent);
        }

        if(clickedButton.getId()==R.id.button03){
            Intent intent = new Intent(MainActivity.this,Exercises3Activity.class);
            MainActivity.this.startActivity(intent);
        }

        if(clickedButton.getId()==R.id.button04){
            Intent intent = new Intent(MainActivity.this,Exercises4Activity.class);
            MainActivity.this.startActivity(intent);
        }

        if(clickedButton.getId()==R.id.button05){
            Intent intent = new Intent(MainActivity.this,Exercises5Activity.class);
            MainActivity.this.startActivity(intent);
        }
    }
}
