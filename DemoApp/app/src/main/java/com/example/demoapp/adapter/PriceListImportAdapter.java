package com.example.demoapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.model.Import;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.detail.FragmentImportDetail;

import java.util.List;


public class PriceListImportAdapter extends RecyclerView.Adapter<PriceListImportAdapter.ViewHolder> {

    private Context context;
    private List<Import> listPriceList;

    public PriceListImportAdapter(Context context) {
        this.context = context;
    }

    public void setImports(List<Import> list){
        this.listPriceList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PriceListImportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_pricelist_import, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceListImportAdapter.ViewHolder holder, int position) {
        Import imp = listPriceList.get(position);
        if (listPriceList != null && listPriceList.size() > 0) {

            holder.stt.setText(imp.getStt());
            holder.pol.setText(imp.getPol());
            holder.pod.setText(imp.getPod());
            holder.of20.setText(imp.getOf20());
            holder.of40.setText(imp.getOf40());
            holder.surcharge.setText(imp.getSurcharge());
            holder.total.setText(imp.getTotalFreight());
            holder.carrier.setText(imp.getCarrier());
            holder.schedule.setText(imp.getSchedule());
            holder.transit.setText(imp.getTransitTime());
            holder.free.setText(imp.getFreeTime());
            holder.valid.setText(imp.getValid());
            holder.note.setText(imp.getNote());

        } else {
            return;
        }
        holder.importCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetail(imp);
            }
        });
    }

    public void goToDetail(Import imp){
        FragmentActivity activity = (FragmentActivity) context;
        FragmentManager fm = activity.getSupportFragmentManager();
        DialogFragment dialogFragment = FragmentImportDetail.getInstance();

        Bundle bundle = new Bundle();

        bundle.putSerializable(Constants.IMPORT_OBJECT, imp);
        dialogFragment.setArguments(bundle);
        dialogFragment.show( fm,"DetailImport");
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
        ConstraintLayout importCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            importCardView = itemView.findViewById(R.id.row_cv_import);
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
