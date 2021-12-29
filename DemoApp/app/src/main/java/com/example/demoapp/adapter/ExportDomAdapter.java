package com.example.demoapp.adapter;


import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExportDomAdapter extends RecyclerView.Adapter<ExportDomAdapter.ExportViewHolder>  {
    @NonNull
    @Override
    public ExportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ExportViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ExportViewHolder extends RecyclerView.ViewHolder{

        public ExportViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
