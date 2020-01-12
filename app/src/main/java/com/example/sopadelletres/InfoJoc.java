package com.example.sopadelletres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class InfoJoc extends AppCompatActivity {
    WebView paginaweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_joc);

        paginaweb = (WebView) findViewById(R.id.wb1);
        paginaweb.loadUrl("file:///android_asset/index.html");
    }
}
