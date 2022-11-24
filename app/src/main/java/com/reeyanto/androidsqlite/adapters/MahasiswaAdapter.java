package com.reeyanto.androidsqlite.adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.reeyanto.androidsqlite.R;
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
        holder.tvNim.setText(mahasiswa.getNim());
        holder.tvNama.setText(mahasiswa.getNama());
        holder.tvJurusan.setText(mahasiswa.getJurusan());
        holder.tvLatLong.setText(String.format("%s, %s", mahasiswa.getLatitude().toString(), mahasiswa.getLongtitude().toString()));
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNim, tvNama, tvJurusan, tvLatLong, tvNetworkConnection;
        private CardView cardView;

        public ViewHolder(@NonNull View view) {
            super(view);

            tvNim = view.findViewById(R.id.tv_nim);
            tvNama = view.findViewById(R.id.tv_nama);
            tvJurusan = view.findViewById(R.id.tv_jurusan);
            tvLatLong = view.findViewById(R.id.tv_lat_long);
            tvNetworkConnection = view.findViewById(R.id.tv_network_connection);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
