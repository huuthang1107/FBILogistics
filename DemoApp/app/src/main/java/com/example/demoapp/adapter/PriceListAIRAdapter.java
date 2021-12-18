package com.example.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.model.Air;
import com.example.demoapp.model.DetailsAIR;

import java.util.List;

public class PriceListAIRAdapter extends RecyclerView.Adapter<PriceListAIRAdapter.PriceAirViewHolder> {
    private Context context;
    private List<DetailsAIR>listAIRS;

    public PriceListAIRAdapter(Context context, List<DetailsAIR> listAIRS) {
        this.context = context;
        this.listAIRS = listAIRS;
    }

    @NonNull
    @Override
    public PriceAirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_price_list_air, parent, false);

        return new PriceAirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceAirViewHolder holder, int position) {
        if(listAIRS != null && listAIRS.size()>0){
            DetailsAIR air = listAIRS.get(position);

            holder.tvstt.setText(air.getStt());
            holder.tvaol.setText(air.getAol());
            holder.tvaod.setText(air.getAod());
            holder.tvdim.setText(air.getDim());
            holder.tvgross.setText(air.getGrossweight());
            holder.tvtype.setText(air.getTypeofcargo());
            holder.tvairfreight.setText(air.getAirfreight());
            holder.tvsurcharge.setText(air.getSurcharge());
            holder.tvairlines.setText(air.getAirlines());
            holder.tvschedule.setText(air.getSchedule());
            holder.tvtransittime.setText(air.getTransittime());
            holder.tvvalid.setText(air.getValid());
            holder.tvnote.setText(air.getNote());

        }
        return;
    }

    @Override
    public int getItemCount() {
        if(listAIRS != null){
            return  listAIRS.size();
        }
        return 0;
    }


    public  class  PriceAirViewHolder extends RecyclerView.ViewHolder {
        TextView tvstt, tvaol, tvaod, tvdim, tvgross, tvtype, tvairfreight, tvsurcharge, tvairlines
                , tvschedule, tvtransittime, tvvalid, tvnote;

        public PriceAirViewHolder(@NonNull View itemView) {
            super(itemView);
            tvstt = itemView.findViewById(R.id.tv_row_price_asia_air_stt);
            tvaol = itemView.findViewById(R.id.tv_row_price_asia_air_aol);
            tvaod = itemView.findViewById(R.id.tv_row_price_asia_air_aod);
            tvdim = itemView.findViewById(R.id.tv_row_price_asia_air_dim);
            tvgross = itemView.findViewById(R.id.tv_row_price_asia_air_grossweight);
            tvtype= itemView.findViewById(R.id.tv_row_price_asia_air_typeofcargo);
            tvairfreight = itemView.findViewById(R.id.tv_row_price_asia_air_freight);
            tvsurcharge = itemView.findViewById(R.id.tv_row_price_asia_air_surcharge);
            tvairlines = itemView.findViewById(R.id.tv_row_price_asia_air_airlines);
            tvschedule = itemView.findViewById(R.id.tv_row_price_asia_air_schedule);
            tvtransittime = itemView.findViewById(R.id.tv_row_price_asia_air_transittime);
            tvvalid = itemView.findViewById(R.id.tv_row_price_asia_air_valid);
            tvnote = itemView.findViewById(R.id.tv_row_price_asia_air_note);
        }

    }
}
