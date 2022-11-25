package com.reeyanto.androidsqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.reeyanto.androidsqlite.helpers.DatabaseHelper;
import com.reeyanto.androidsqlite.models.Mahasiswa;

public class FormActivity extends AppCompatActivity {

    private EditText etNim, etNama, etJurusan;
    private Button btnSave;
    private String nim, nama, jurusan;
    private double latitude = 0.0, longitude = 0.0;
    private final static int REQUEST_CODE = 200;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initComponents();
        btnSave.setOnClickListener(view -> saveData(false));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();

        // apakah sedang edit?
        if (isEditMode()) {
            Mahasiswa mahasiswa = getIntent().getParcelableExtra("EDIT_USER");
            etNim.setText(mahasiswa.getNim());
            etNama.setText(mahasiswa.getNama());
            etJurusan.setText(mahasiswa.getJurusan());
            
            btnSave.setOnClickListener(view -> saveData(true));
        }
    }

    private boolean isEditMode() {
        return getIntent().hasExtra("EDIT_USER");
    }


    private void getCurrentLocation() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, REQUEST_CODE);
            }
        } else {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude= location.getLongitude();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if ((grantResults.length) > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Akses ditolak!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void saveData(@Nullable boolean isEditMode) {
        if (validation()) {
            DatabaseHelper db = new DatabaseHelper(this);
            Mahasiswa mahasiswa = new Mahasiswa(nim, nama, jurusan, latitude, longitude);

            if (isEditMode) {
                if (db.updateMahasiswa(mahasiswa) > 0) Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
            } else {
                if (db.insertMahasiswa(mahasiswa) > 0) Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
            }

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