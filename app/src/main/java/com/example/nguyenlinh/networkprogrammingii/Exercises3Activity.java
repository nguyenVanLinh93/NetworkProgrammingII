package com.example.nguyenlinh.networkprogrammingii;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * My loan calculator app is running on http://apps.coreservlets.com/NetworkingSupport/loan-calculator.
 * If you fail to supply input, it uses default values, so that URL alone is enough to get a JSON result.
 * (Type the URL into a Web browser to see.) Make an app that connects to that URL, turns the result into a JSON object, and displays the monthly payment.
 *
 * @author nguyenlinh
 * @version 1.0
 * @since 2017-2-10
 */
public class Exercises3Activity extends AppCompatActivity {

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises3);

        mTextView = (TextView)findViewById(R.id.textViewjson1);
    }

    public String loadDataFromInternet(String stringURL){
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(stringURL);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void doSomeThing(String s){
        try {
            JSONObject root = new JSONObject(s);
            showResult(root.getString("monthlyPayment"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected class ShowResultsTask extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
            return loadDataFromInternet(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            doSomeThing(s);
        }
    }

    public void showResult(String result){
        mTextView.setText(result);
    }

    public void getDefaulValues(View clickedbutton){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ShowResultsTask().execute("http://apps.coreservlets.com/NetworkingSupport/loan-calculator");
            }
        });
    }
}
