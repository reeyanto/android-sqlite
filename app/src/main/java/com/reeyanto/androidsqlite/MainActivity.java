package com.reeyanto.androidsqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reeyanto.androidsqlite.adapters.MahasiswaAdapter;
import com.reeyanto.androidsqlite.helpers.DatabaseHelper;
import com.reeyanto.androidsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMahasiswa;
    private FloatingActionButton fabAdd;
    private ArrayList<Mahasiswa> mahasiswaList;
    private EditText etSearch;
    private ImageButton btnSearch;
    private static final String TAG = "MainActivity";

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
        btnSearch.setOnClickListener(view -> getDataFromDatabase(etSearch.getText().toString().trim()));

        getDataFromDatabase(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromDatabase(null);
    }

    private void getDataFromDatabase(@Nullable String keyword) {
        DatabaseHelper db = new DatabaseHelper(this);
        mahasiswaList = new ArrayList<>();

        if (keyword == null) mahasiswaList.addAll(db.getAllMahasiswa(null));
        else mahasiswaList.addAll(db.getAllMahasiswa(keyword));

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
        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        rvMahasiswa = findViewById(R.id.rv_mahasiswa);
        fabAdd = findViewById(R.id.fab_add);
    }
}