package com.example.demoapp.db.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.model.PriceListAIR;

import java.util.List;

public class PriceListAIRAdapter extends  RecyclerView.Adapter<PriceListAIRAdapter.PriceAirViewHolder> {

    private Context context;
    private List<PriceListAIR> listAIRS;

    public PriceListAIRAdapter(Context context, List<PriceListAIR> listAIRS) {
        this.context = context;
        this.listAIRS = listAIRS;
    }

    @NonNull
    @Override
    public PriceAirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_price_list_air,parent,false);
        return new PriceAirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceAirViewHolder holder, int position) {
        if(listAIRS != null && listAIRS.size() > 0){
            PriceListAIR priceListAIR = listAIRS.get(position);

            holder.tvstt.setText(priceListAIR.getStt());
            holder.tvaol.setText(priceListAIR.getAol());
            holder.tvaod.setText(priceListAIR.getAod());
            holder.tvdim.setText(priceListAIR.getDim());
            holder.tvgross.setText(priceListAIR.getGross());
            holder.tvtype.setText(priceListAIR.getTypeOfCargo());
            holder.tvairfreight.setText(priceListAIR.getOceanFreight());
            holder.tvsurcharge.setText(priceListAIR.getLocalCharge());
            holder.tvairlines.setText(priceListAIR.getCarrier());
            holder.tvschedule.setText(priceListAIR.getSchedule());
            holder.tvtransittime.setText(priceListAIR.getTransitTime());
            holder.tvvalid.setText(priceListAIR.getValid());
            holder.tvnote.setText(priceListAIR.getNote());
        }
    }

    @Override
    public int getItemCount() {
        if(listAIRS != null){
            return listAIRS.size();
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
