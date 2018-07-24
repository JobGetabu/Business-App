package com.job.businessapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ProgressBar;

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

        ProgressBar progressBar = findViewById(R.id.progressBar);

        WebViewController webViewController = new WebViewController(progressBar);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(webViewController);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(prourl);

    }
}

