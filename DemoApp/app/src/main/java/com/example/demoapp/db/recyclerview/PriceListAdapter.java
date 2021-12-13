package com.example.demoapp.db.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.db.PriceListModel;

import java.util.List;

public class PriceListAdapter extends RecyclerView.Adapter<PriceListAdapter.ViewHolder> {

    private Context context;
    private List<PriceListModel> listPriceList;

    public PriceListAdapter(Context context, List<PriceListModel> listPriceList) {
        this.context = context;
        this.listPriceList = listPriceList;
    }

    @NonNull
    @Override
    public PriceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_pricelist_asia, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceListAdapter.ViewHolder holder, int position) {
        if (listPriceList != null && listPriceList.size() > 0) {
            PriceListModel priceListModel = listPriceList.get(position);

            holder.stt.setText(priceListModel.getStt());
            holder.pol.setText(priceListModel.getPol());
            holder.pod.setText(priceListModel.getPod());
            holder.of20.setText(priceListModel.getOf20());
            holder.of40.setText(priceListModel.getOf40());
            holder.su20.setText(priceListModel.getSu20());
            holder.su40.setText(priceListModel.getSu40());
            holder.line.setText(priceListModel.getLines());
            holder.notes1.setText(priceListModel.getNotes1());
            holder.valid.setText(priceListModel.getValid());
            holder.notes2.setText(priceListModel.getNotes2());
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

        TextView stt, pol, pod, of20, of40, su20, su40, line, notes1, valid, notes2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stt = itemView.findViewById(R.id.tv_row_price_asia_stt);
            pol = itemView.findViewById(R.id.tv_row_price_asia_pol);
            pod = itemView.findViewById(R.id.tv_row_price_asia_pod);
            of20 = itemView.findViewById(R.id.tv_row_price_asia_of20);
            of40 = itemView.findViewById(R.id.tv_row_price_asia_of40);
            su20 = itemView.findViewById(R.id.tv_row_price_asia_su20);
            su40 = itemView.findViewById(R.id.tv_row_price_asia_su40);
            line = itemView.findViewById(R.id.tv_row_price_asia_line);
            notes1 = itemView.findViewById(R.id.tv_row_price_asia_notes1);
            valid = itemView.findViewById(R.id.tv_row_price_asia_valid);
            notes2 = itemView.findViewById(R.id.tv_row_price_asia_notes2);


        }
    }

}