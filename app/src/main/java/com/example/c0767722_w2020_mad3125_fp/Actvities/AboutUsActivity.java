package com.example.c0767722_w2020_mad3125_fp.Actvities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.c0767722_w2020_mad3125_fp.R;

public class AboutUsActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setTitle("About Us");
        actionBar.show();
        webView=findViewById(R.id.web);
        WebViewClient mWebViewClient = new WebViewClient();
        webView.setWebViewClient(mWebViewClient);
        webView.loadUrl("https://lambtoncollege.ca");
    }
    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) this;
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("About Us");
        }

    }
}
