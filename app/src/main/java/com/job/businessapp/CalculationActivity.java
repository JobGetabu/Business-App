package com.job.businessapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculationActivity extends AppCompatActivity {

    public static final String PRODUCTPRICE = "PRODUCTPRICE";
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button15)
    Button button15;
    @BindView(R.id.button30)
    Button button30;

    private String getProductprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Get Discount");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getProductprice = getIntent().getStringExtra(PRODUCTPRICE);

        textView.setText(getProductprice);
    }

    @OnClick({R.id.textView, R.id.button5, R.id.button15, R.id.button30})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView:
                Toast.makeText(this, ""+textView.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                calculateDiscount(5d);
                break;
            case R.id.button15:
                calculateDiscount(15d);
                break;
            case R.id.button30:
                calculateDiscount(30d);
                break;
        }
    }

    private void calculateDiscount(double perc){

        String temp  = getProductprice.replace("$","");

        double price = Double.parseDouble(temp);

        double dis = (perc / 100) * price;

        textView.setText("$ "+(price - dis));
    }
}
