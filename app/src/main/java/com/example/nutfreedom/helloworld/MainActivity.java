package com.example.nutfreedom.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgOperator;
    private Button btnCal;
    private EditText edtCal1;
    private EditText edtCal2;
    private TextView tvResultCal;
    private CustomViewGroup viewGroup1;
    private CustomViewGroup viewGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.activity_main);
        initInstances();
    }

    private void initInstances() {
        viewGroup1 = (CustomViewGroup) findViewById(R.id.viewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.viewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");

        rgOperator = (RadioGroup) findViewById(R.id.rgOperator);

        edtCal1 = (EditText) findViewById(R.id.edtCal1);
        edtCal2 = (EditText) findViewById(R.id.edtCal2);

        tvResultCal = (TextView) findViewById(R.id.tvResultCal);

        btnCal = (Button) findViewById(R.id.btnCal);
        btnCal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCal) {
            int val1 = getValEdtCal(edtCal1.getText().toString());
            int val2 = getValEdtCal(edtCal2.getText().toString());
            int result = 0;
            switch (rgOperator.getCheckedRadioButtonId()) {
                case R.id.rbPlus:
                    result = val1 + val2;
                    break;
                case R.id.rbMinus:
                    result = val1 - val2;
                    break;
                case R.id.rbMultiply:
                    result = val1 * val2;
                    break;
                case R.id.rbDivide:
                    if (val2 == 0) {
                        Toast.makeText(MainActivity.this, "ไม่สามารถใส่ค่าตัวหารที่เป็น 0 ได้", Toast.LENGTH_SHORT).show();
                    } else {
                        result = val1 / val2;
                    }
                    break;
            }
            tvResultCal.setText("= " + result);

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("result", result);

            Coordinate c1 = new Coordinate();
            c1.x = 10;
            c1.y = 15;
            c1.z = 20;

            Bundle bundle = new Bundle();
            bundle.putInt("x", c1.x);
            bundle.putInt("y", c1.y);
            bundle.putInt("z", c1.z);
            intent.putExtra("cBundle", bundle);

            CoordinateParcelable c2 = new CoordinateParcelable();
            c2.x = 10;
            c2.y = 15;
            c2.z = 20;
            intent.putExtra("cParcelable", c2);

            startActivityForResult(intent, 12345);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12345) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int getValEdtCal(String val) {
        int result = 0;
        try {
            result = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("tvResultCal", tvResultCal.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //tvResultCal.setText(savedInstanceState.getString("tvResultCal"));
    }
}
