package com.example.nguyenlinh.networkprogrammingii;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Make an app that takes a loan amount, two interest rates and two time periods in months.
 * Compute the total payments for the loan under each of those two situations, and display the lower of the two values.
 *
 * @author nguyenlinh
 * @version 1.0
 * @since 2017-2-10
 */
public class Exercises5Activity extends Exercises4Activity {

    private EditText mEditTextAmount, mEditTextRate1, mEditTextMounths1, mEditTextRate2, mEditTextMounths2;
    private TextView mTextViewPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises5);

        mEditTextAmount = (EditText)findViewById(R.id.editTextAmount);
        mEditTextRate1 = (EditText)findViewById(R.id.editTextRate);
        mEditTextMounths1 = (EditText)findViewById(R.id.editTextMonths);
        mEditTextRate2 = (EditText)findViewById(R.id.editTextRate2);
        mEditTextMounths2 = (EditText)findViewById(R.id.editTextMonth2);
        mTextViewPay = (TextView) findViewById(R.id.textViewPayment);
    }

    @Override
    public void calculate(View clickButton) {
        final String amount = mEditTextAmount.getText().toString();
        final String rate1 = mEditTextRate1.getText().toString();
        final String month1 = mEditTextMounths1.getText().toString();
        final String rate2 = mEditTextRate2.getText().toString();
        final String month2 = mEditTextMounths2.getText().toString();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ShowResultsTask2().execute("http://apps.coreservlets.com/NetworkingSupport/loan-calculator?loanInputs={amount: "+amount+", rate: "+rate1+", months: "+month1+"}", "http://apps.coreservlets.com/NetworkingSupport/loan-calculator?loanInputs={amount: "+amount+", rate: "+rate2+", months: "+month2+"}");
            }
        });
    }

    protected class ShowResultsTask2 extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
            String s1 = loadDataFromInternet(params[0]); // sử lý các giá trị thứ nhất ta nhập vào rồi trả về 1 chuỗi s1
            String s2 = loadDataFromInternet(params[1]); // sử lý các gis trị thứ 2 rồi trả về 1 chuỗi s2

            return s1+"tach"+s2; // ghép 2 chuỗi s1 và s2 để truyền vào hàm onPostExcute va phân cách nhau bằng chữ tach
        }

        @Override
        protected void onPostExecute(String s) {
            doSomeThing(s);
        }
    }

    @Override
    public void doSomeThing(String s) {
        String[] stringArray = s.split("tach"); // tách chuỗi ban đầu thành 2 chuỗi s1 s2 để sử lý
        try {
            JSONObject root1 = new JSONObject(stringArray[0]);
            String leaf1 = root1.getString("totalPayments"); // lấy ra giá trị số 1

            JSONObject root2 = new JSONObject(stringArray[1]);
            String leaf2 = root1.getString("totalPayments"); // lấy ra giá trị số 2

            if (Double.parseDouble(leaf1) < Double.parseDouble(leaf2)){ // ép kiểu double sau đó so sánh cái nào bé hơn
                showResult("totalPayments : "+leaf1);
            } else {
                showResult("totalPayments : "+leaf2);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showResult(String result) {
        mTextViewPay.setText(result);
    }
}
