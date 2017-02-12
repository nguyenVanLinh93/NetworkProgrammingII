package com.example.nguyenlinh.networkprogrammingii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Experiment with interactively passing JSON data to the loan-calculator service. E.g., try http://apps.coreservlets.com/NetworkingSupport/loan-calculator?loanInputs={amount: 200000, rate: 6.5, months: 180}
 * (omit the carriage return in the middle, of course)
 *
 * @author nguyenlinh
 * @version 1.0
 * @since 2017-2-10
 */
public class Exercises4Activity extends Exercises3Activity {

    private EditText mEditTextAmount, mEditTextRate, mEditTextMounths;
    private TextView mTextViewPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises4);

        mEditTextAmount = (EditText)findViewById(R.id.editTextAmount);
        mEditTextRate = (EditText)findViewById(R.id.editTextRate);
        mEditTextMounths = (EditText)findViewById(R.id.editTextMonths);
        mTextViewPay = (TextView) findViewById(R.id.textViewPayment);
    }

    public void calculate(View clickButton){
        final String amount = mEditTextAmount.getText().toString();
        final String rate = mEditTextRate.getText().toString();
        final String month = mEditTextMounths.getText().toString();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ShowResultsTask().execute("http://apps.coreservlets.com/NetworkingSupport/loan-calculator?loanInputs={amount: "+amount+", rate: "+rate+", months: "+month+"}");
            }
        });
    }

    @Override
    public void showResult(String result) {
        mTextViewPay.setText(result);
    }
}
