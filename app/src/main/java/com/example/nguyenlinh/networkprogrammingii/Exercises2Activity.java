package com.example.nguyenlinh.networkprogrammingii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Make an app that takes a URL from the user and prints out the number of characters in the page at that URL.
 * Use HttpClient. (Hint: very easy if you use my HttpUtils).
 *
 * @author nguyenlinh
 * @version 1.0
 * @since 2017-2-10
 */
public class Exercises2Activity extends Exercises1Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises2);

        mButton = (Button)findViewById(R.id.button);
        mEditText = (EditText)findViewById(R.id.editText);
        mTextView = (TextView)findViewById(R.id.textView2);
    }

    @Override
    public void displayResults(String count) {
        int num = Integer.valueOf(count);
        if(num > 0){
            mTextView.setText("Number of Characters in Page : "+count);
        } else {
            if (num == 0){
                mTextView.setText("N0 Characters in Page");
            } else {
                if (num == -1){
                    mTextView.setText("Bad URL");
                } else {
                    mTextView.setText("Error in connection");
                }
            }
        }
    }

    @Override
    public String showResults(String urlString) {
        int count = 0;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection)url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                String[] word = line.split("[:/.{} ]+");
                count += word.length;
            }
        } catch (MalformedURLException e) {
            count = -1;
            e.printStackTrace();
        } catch (IOException e) {
            count = -2;
            e.printStackTrace();
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }

        return String.valueOf(count);
    }
}
