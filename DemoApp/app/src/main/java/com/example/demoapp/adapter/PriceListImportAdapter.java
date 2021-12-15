package com.example.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.databinding.RowPricelistImportBinding;
import com.example.demoapp.model.Import;

import java.util.List;


public class PriceListImportAdapter extends RecyclerView.Adapter<PriceListImportAdapter.ViewHolder> {

    private Context context;
    private List<Import> listPriceList;


    public PriceListImportAdapter(Context context, List<Import> listPriceList) {
        this.context = context;
        this.listPriceList = listPriceList;
    }

    @NonNull
    @Override
    public PriceListImportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_pricelist_import, parent, false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull PriceListImportAdapter.ViewHolder holder, int position) {
        if (listPriceList != null && listPriceList.size() > 0) {
            Import priceListModel = listPriceList.get(position);

            holder.binding.tvRowPriceImportStt.setText(priceListModel.getStt());
            holder.binding.tvRowPriceImportPol.setText(priceListModel.getPol());
            holder.binding.tvRowPriceImportPod.setText(priceListModel.getPod());
            holder.binding.tvRowPriceImportOf20.setText(priceListModel.getOf20());
            holder.binding.tvRowPriceImportOf40.setText(priceListModel.getOf40());
            holder.binding.tvRowPriceImportSurcharge.setText(priceListModel.getSurcharge());
            holder.binding.tvRowPriceImportTotal.setText(priceListModel.getTotalFreight());
            holder.binding.tvRowPriceImportCarrier.setText(priceListModel.getCarrier());
            holder.binding.tvRowPriceImportSchedule.setText(priceListModel.getSchedule());
            holder.binding.tvRowPriceImportTransit.setText(priceListModel.getTransitTime());
            holder.binding.tvRowPriceImportFree.setText(priceListModel.getFreeTime());
            holder.binding.tvRowPriceImportValid.setText(priceListModel.getValid());
            holder.binding.tvRowPriceImportNote.setText(priceListModel.getNote());

        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        if (listPriceList != null) {
            return listPriceList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowPricelistImportBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
