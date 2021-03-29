package com.example.mynews;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

public class DetailActivity extends Activity {
    private String url;
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        url = getIntent().getStringExtra("url");
        webView = (WebView)findViewById(R.id.wv_detail);
        webView.loadUrl(url);
    }
}
