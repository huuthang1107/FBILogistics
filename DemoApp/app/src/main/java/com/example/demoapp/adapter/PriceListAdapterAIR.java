package com.example.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentLclBinding;
import com.example.demoapp.model.Air;
import com.example.demoapp.model.Fcl;

import java.util.List;

public class PriceListAdapterAIR extends RecyclerView.Adapter<PriceListAdapterAIR.PriceAIRViewHolder> {
    private Context context;
    private List<Air> listPriceList;

    public PriceListAdapterAIR(Context context, List<Air> listPriceList) {
        this.context = context;
        this.listPriceList = listPriceList;
    }

    @NonNull
    @Override
    public PriceAIRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_price_list_air, parent,false);

        return new PriceAIRViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceAIRViewHolder holder, int position) {
        if(listPriceList != null && listPriceList.size()>0){
            Air air = listPriceList.get(position);

            holder.tvStt.setText(air.getStt());
            holder.tvAol.setText(air.getAol());
            holder.tvAod.setText(air.getAod());
            holder.tvDim.setText(air.getDim());
            holder.tvGross.setText(air.getGross());
            holder.tvType.setText(air.getTypeOfCargo());
            holder.tvFreight.setText(air.getAirFreight());
            holder.tvSurcharge.setText(air.getSurcharge());
            holder.tvLines.setText(air.getAirLines());
            holder.tvSchedule.setText(air.getSchedule());
            holder.tvTransitime.setText(air.getTransitTime());
            holder.tvValid.setText(air.getValid());
            holder.tvNote.setText(air.getNote());

        }
    }

    @Override
    public int getItemCount() {
        if(listPriceList != null){
            return listPriceList.size();
        }
        return 0;
    }

    public class PriceAIRViewHolder extends RecyclerView.ViewHolder{
        TextView tvStt, tvAol, tvAod, tvDim, tvGross, tvType, tvFreight, tvSurcharge,
        tvLines, tvSchedule, tvTransitime, tvValid, tvNote;
        public PriceAIRViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStt = itemView.findViewById(R.id.tv_row_price_asia_air_stt);
            tvAod = itemView.findViewById(R.id.tv_row_price_asia_air_aod);
            tvAol = itemView.findViewById(R.id.tv_row_price_asia_air_aol);
            tvDim = itemView.findViewById(R.id.tv_row_price_asia_air_dim);
            tvGross = itemView.findViewById(R.id.tv_row_price_asia_air_grossweight);
            tvType = itemView.findViewById(R.id.tv_row_price_asia_air_typeofcargo);
            tvFreight = itemView.findViewById(R.id.tv_row_price_asia_air_freight);
            tvSurcharge = itemView.findViewById(R.id.tv_row_price_asia_air_surcharge);
            tvLines = itemView.findViewById(R.id.tv_row_price_asia_air_airlines);
            tvSchedule = itemView.findViewById(R.id.tv_row_price_asia_air_schedule);
            tvTransitime = itemView.findViewById(R.id.tv_row_price_asia_air_transittime);
            tvValid = itemView.findViewById(R.id.tv_row_price_asia_air_valid);
            tvNote = itemView.findViewById(R.id.tv_row_price_asia_air_note);

        }
    }
}
