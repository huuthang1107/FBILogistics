package com.example.demoapp.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.databinding.RowDomExportBinding;
import com.example.demoapp.model.DomExport;

import java.util.List;

public class ExportDomAdapter extends RecyclerView.Adapter<ExportDomAdapter.ExportViewHolder> {
    private  Context context;
    private List<DomExport> listExport;

    @NonNull
    @Override
    public ExportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExportViewHolder(RowDomExportBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExportViewHolder holder, int position) {
        holder.bind(listExport.get(position));
    }

    public ExportDomAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDomExport(List<DomExport> list) {
        this.listExport = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (listExport == null) {
            return 0;
        }
        return listExport.size();
    }

    public static class ExportViewHolder extends RecyclerView.ViewHolder {
        private RowDomExportBinding binding;

        public ExportViewHolder(@NonNull RowDomExportBinding root) {
            super(root.getRoot());
            binding = root;
        }

        public void bind(DomExport export) {
            String name = export.getName();
            String weight = export.getWeight();
            String quantity = export.getQuantity();
            String temp = export.getTemp();
            String address = export.getAddress();
            String portExport = export.getPortExport();
            String length = export.getLength();
            String height = export.getHeight();
            String width = export.getWidth();

            binding.tvDomExportProductName.setText(name);
            binding.tvDomExportWeight.setText(weight);
            binding.tvDomExportQuantity.setText(quantity);
            binding.tvDomExportTemp.setText(temp);
            binding.tvDomExportAddress.setText(address);
            binding.tvDomExportSeaport.setText(portExport);
            binding.tvDomExportLength.setText(length);
            binding.tvRowDomExportHeight.setText(height);
            binding.tvRowDomExportWidth.setText(width);
        }
    }
}
