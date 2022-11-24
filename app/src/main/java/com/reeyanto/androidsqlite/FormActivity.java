package com.reeyanto.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.reeyanto.androidsqlite.helpers.DatabaseHelper;
import com.reeyanto.androidsqlite.models.Mahasiswa;

public class FormActivity extends AppCompatActivity {

    private EditText etNim, etNama, etJurusan;
    private Button btnSave;
    private String nim, nama, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initComponents();
        btnSave.setOnClickListener(view -> saveData());
    }

    private void saveData() {
        if (validation()) {
            DatabaseHelper db = new DatabaseHelper(this);
            Mahasiswa mahasiswa = new Mahasiswa(nim, nama, jurusan, 0.0, 0.0, null);

            if (db.insertMahasiswa(mahasiswa) > 0) Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();

            db.close();
            finish();
        }
    }

    private boolean validation() {
        boolean isValid = true;

        nim = etNim.getText().toString().trim();
        nama = etNama.getText().toString().trim();
        jurusan = etJurusan.getText().toString().trim();

        if (nim.isEmpty()) {
            etNim.setError("Nim tidak boleh kosong");
            isValid = false;
        }

        if (nama.isEmpty()) {
            etNama.setError("Nama tidak boleh kosong");
            isValid = false;
        }

        if (jurusan.isEmpty()) {
            etJurusan.setError("Jurusan tidak boleh kosong");
            isValid = false;
        }

        return isValid;
    }

    private void initComponents() {
        etNim = findViewById(R.id.et_nim);
        etNama = findViewById(R.id.et_nama);
        etJurusan = findViewById(R.id.et_jurusan);

        btnSave = findViewById(R.id.btnSave);
    }
}