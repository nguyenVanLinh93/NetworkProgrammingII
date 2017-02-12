package com.example.nguyenlinh.networkprogrammingii;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Make an app that takes a URL from the user and prints out the number of lines in the page at that URL.
 * Use HttpURLConnection. (To simplify your code, steal the layout file and some of the initial Java setup from my FTP welcome message app.)
 *
 *@author nguyenlinh
 * @version 1.0
 * @since 2017-2-10
 */
public class Exercises1Activity extends AppCompatActivity {

    protected EditText mEditText;
    protected TextView mTextView;
    protected Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises1);

        mButton = (Button)findViewById(R.id.button);
        mEditText = (EditText)findViewById(R.id.editText);
        mTextView = (TextView)findViewById(R.id.textView2);
    }

    public void showNumberLines(View clickedButton){
        final String urlString = mEditText.getText().toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ShowResultsTask().execute(urlString);
            }
        });
    }

    public void displayResults(String count) {
        int num = Integer.valueOf(count);
        if(num > 0){
            mTextView.setText("Number of Lines in Page : "+count);
        } else {
            if (num == 0){
                mTextView.setText("N0 Lines in Page");
            } else {
                if (num == -1){
                    mTextView.setText("Bad URL");
                } else {
                    mTextView.setText("Error in connection");
                }
            }
        }

    }

    public String showResults(String urlString){
        int count = 0;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection)url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while((line = in.readLine()) != null){
                    count++;
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


    private class ShowResultsTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            return showResults(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            displayResults(s);
        }
    }
}
