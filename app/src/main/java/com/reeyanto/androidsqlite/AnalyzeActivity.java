package com.reeyanto.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.reeyanto.androidsqlite.adapters.AnalyzeAdapter;
import com.reeyanto.androidsqlite.helpers.DatabaseHelper;
import com.reeyanto.androidsqlite.models.Analyze;

import java.util.ArrayList;

public class AnalyzeActivity extends AppCompatActivity {

    private RecyclerView rvAnalyze;
    private ArrayList<Analyze> analyzeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        rvAnalyze = findViewById(R.id.rv_analyze);
        getAnalyzeData();
    }

    private void getAnalyzeData() {
        analyzeArrayList = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        analyzeArrayList.addAll(databaseHelper.getAnalyze());

        setRecyclerViewAnalyze();
    }

    private void setRecyclerViewAnalyze() {
        rvAnalyze.setHasFixedSize(true);
        rvAnalyze.setAdapter(new AnalyzeAdapter(this, analyzeArrayList));
        rvAnalyze.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}