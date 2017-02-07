package com.example.nutfreedom.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private int sum;
    private TextView tvResultCal;
    private EditText edtReturn;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);
        Bundle bundle = intent.getBundleExtra("cBundle");
        int x = bundle.getInt("x");
        int y = bundle.getInt("y");
        int z = bundle.getInt("z");

        CoordinateParcelable coordinateParcelable = intent.getParcelableExtra("cParcelable");

        initInstances();
    }

    private void initInstances() {
        tvResultCal = (TextView) findViewById(R.id.tvResultCal);
        tvResultCal.setText("Result = " + sum);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        edtReturn = (EditText) findViewById(R.id.edtReturn);
    }

    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", edtReturn.getText().toString());
            setResult(RESULT_OK, returnIntent);
            finish();

        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
