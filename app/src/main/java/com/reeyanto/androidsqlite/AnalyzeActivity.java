package com.reeyanto.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AnalyzeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}