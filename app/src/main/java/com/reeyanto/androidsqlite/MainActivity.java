package com.reeyanto.androidsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reeyanto.androidsqlite.adapters.MahasiswaAdapter;
import com.reeyanto.androidsqlite.helpers.DatabaseHelper;
import com.reeyanto.androidsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMahasiswa;
    private FloatingActionButton fabAdd;
    private ArrayList<Mahasiswa> mahasiswaList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.option_menu_analyze) {
            startActivity(new Intent(this, AnalyzeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

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