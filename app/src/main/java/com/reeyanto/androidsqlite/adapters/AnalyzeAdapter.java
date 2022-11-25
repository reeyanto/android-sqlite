package com.reeyanto.androidsqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reeyanto.androidsqlite.R;
import com.reeyanto.androidsqlite.models.Analyze;

import java.util.ArrayList;

public class AnalyzeAdapter extends RecyclerView.Adapter<AnalyzeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Analyze> analyzeArrayList;
    private Analyze analyze;

    public AnalyzeAdapter(Context context, ArrayList<Analyze> analyzeArrayList) {
        this.context = context;
        this.analyzeArrayList = analyzeArrayList;
    }

    @NonNull
    @Override
    public AnalyzeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.analyze_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnalyzeAdapter.ViewHolder holder, int position) {
        analyze = analyzeArrayList.get(position);

        holder.tvAnalyzeJurusan.setText(analyze.getJurusan());
        holder.tvAnalyzeJumlah.setText(analyze.getJumlah());
    }

    @Override
    public int getItemCount() {
        return analyzeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAnalyzeJurusan, tvAnalyzeJumlah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAnalyzeJurusan = itemView.findViewById(R.id.tv_analyze_jurusan);
            tvAnalyzeJumlah  = itemView.findViewById(R.id.tv_analyze_jumlah);
        }
    }
}
