package com.job.businessapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class ProductWebActivity extends AppCompatActivity {

    //requesting data about the product

    public static final String PRODUCTURL = "PRODUCTURL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_web);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.productweb);

        String prourl = getIntent().getStringExtra(PRODUCTURL);

        if (prourl == null){
            finish();
        }

        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl(prourl);
    }
}
