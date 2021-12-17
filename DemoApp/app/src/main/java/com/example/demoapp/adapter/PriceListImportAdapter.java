package com.example.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
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

            holder.stt.setText(priceListModel.getStt());
            holder.pol.setText(priceListModel.getPol());
            holder.pod.setText(priceListModel.getPod());
            holder.of20.setText(priceListModel.getOf20());
            holder.of40.setText(priceListModel.getOf40());
            holder.surcharge.setText(priceListModel.getSurcharge());
            holder.total.setText(priceListModel.getTotalFreight());
            holder.carrier.setText(priceListModel.getCarrier());
            holder.schedule.setText(priceListModel.getSchedule());
            holder.transit.setText(priceListModel.getTransitTime());
            holder.free.setText(priceListModel.getFreeTime());
            holder.valid.setText(priceListModel.getValid());
            holder.note.setText(priceListModel.getNote());

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

        TextView stt, pol, pod, of20, of40, surcharge, total, carrier, schedule, transit, free, valid, note;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stt = itemView.findViewById(R.id.tv_row_price_import_stt);
            pol = itemView.findViewById(R.id.tv_row_price_import_pol);
            pod = itemView.findViewById(R.id.tv_row_price_import_pod);
            of20 = itemView.findViewById(R.id.tv_row_price_import_of20);
            of40 = itemView.findViewById(R.id.tv_row_price_import_of40);
            surcharge = itemView.findViewById(R.id.tv_row_price_import_surcharge);
            total = itemView.findViewById(R.id.tv_row_price_import_total);
            carrier = itemView.findViewById(R.id.tv_row_price_import_carrier);
            schedule = itemView.findViewById(R.id.tv_row_price_import_schedule);
            transit = itemView.findViewById(R.id.tv_row_price_import_transit);
            free = itemView.findViewById(R.id.tv_row_price_import_free);
            valid = itemView.findViewById(R.id.tv_row_price_import_valid);
            note = itemView.findViewById(R.id.tv_row_price_import_note);

        }
    }

}
