package com.reeyanto.androidsqlite.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reeyanto.androidsqlite.FormActivity;
import com.reeyanto.androidsqlite.R;
import com.reeyanto.androidsqlite.helpers.DatabaseHelper;
import com.reeyanto.androidsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Mahasiswa> mahasiswaList;
    private Mahasiswa mahasiswa;

    public MahasiswaAdapter(Context context, ArrayList<Mahasiswa> mahasiswaList) {
        this.context = context;
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mahasiswa_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.ViewHolder holder, int position) {
        mahasiswa = mahasiswaList.get(position);
        holder.tvNama.setText(mahasiswa.getNama());
        holder.tvNim.setText(String.format("%s\t\t\t: %s", "NIM", mahasiswa.getNim()));
        holder.tvJurusan.setText(String.format("%s\t\t: %s", "Jurusan", mahasiswa.getJurusan()));
        holder.tvLatLong.setText(String.format("%s\t\t: %s, %s", "LatLong", mahasiswa.getLatitude().toString(), mahasiswa.getLongtitude().toString()));

        holder.btnDelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Konfirmasi")
                    .setMessage("Yakin ingin menghapus data ini?")
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        DatabaseHelper db = new DatabaseHelper(context);
                        if(db.deleteMahasiswa(mahasiswaList.get(position)) > 0) {
                            Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                            mahasiswaList.clear();
                            mahasiswaList.addAll(db.getAllMahasiswa(null));
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Tidak", ((dialogInterface, i) -> {
                        // do nothing
                    }))
                    .create().show();
        });

        holder.btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(context, FormActivity.class);
            intent.putExtra("EDIT_USER", mahasiswaList.get(position));

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Aplikasi maps atau browser tidak tersedia", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnMaps.setOnClickListener(view -> {
            String latLong = String.format("%s,%s,%s", mahasiswa.getLatitude(), mahasiswa.getLongtitude(), "20z");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/@" + latLong));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNim, tvNama, tvJurusan, tvLatLong;
        private Button btnEdit, btnDelete, btnMaps;

        public ViewHolder(@NonNull View view) {
            super(view);

            tvNim = view.findViewById(R.id.tv_nim);
            tvNama = view.findViewById(R.id.tv_nama);
            tvJurusan = view.findViewById(R.id.tv_jurusan);
            tvLatLong = view.findViewById(R.id.tv_lat_long);

            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);
            btnMaps = view.findViewById(R.id.btnMaps);
        }
    }
}
