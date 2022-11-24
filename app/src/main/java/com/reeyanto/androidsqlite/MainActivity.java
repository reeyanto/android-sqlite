package com.reeyanto.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reeyanto.androidsqlite.adapters.MahasiswaAdapter;
import com.reeyanto.androidsqlite.helpers.DatabaseHelper;
import com.reeyanto.androidsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMahasiswa;
    private FloatingActionButton fabAdd;
    private ArrayList<Mahasiswa> mahasiswaList;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        fabAdd.setOnClickListener(view -> openFormActivity());

        getDataFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromDatabase();
    }

    private void getDataFromDatabase() {
        DatabaseHelper db = new DatabaseHelper(this);
        mahasiswaList = new ArrayList<>();
        mahasiswaList.addAll(db.getAllMahasiswa());

        db.close();
        setRecyclerView();
    }

    private void setRecyclerView() {
        rvMahasiswa.setHasFixedSize(true);
        rvMahasiswa.setAdapter(new MahasiswaAdapter(this, mahasiswaList));
        rvMahasiswa.setLayoutManager(new LinearLayoutManager(this));
    }

    private void openFormActivity() {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

    private void initComponents() {
        rvMahasiswa = findViewById(R.id.rv_mahasiswa);
        fabAdd = findViewById(R.id.fab_add);
    }
}