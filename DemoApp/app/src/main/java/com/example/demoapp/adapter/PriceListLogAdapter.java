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
import com.example.demoapp.model.Log;

import java.util.List;

public class PriceListLogAdapter extends RecyclerView.Adapter<PriceListLogAdapter.LogViewHolder> {
    private Context context;
    private List<Log> mPriceListLog;

    public PriceListLogAdapter(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_price_list_log, parent,false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        if(mPriceListLog!= null && mPriceListLog.size()>0){
            Log log = mPriceListLog.get(position);

            holder.tvStt.setText(log.getStt());
            holder.tvTenHang.setText(log.getTenhang());
            holder.tvHScode.setText(log.getHscode());
            holder.tvCongdung.setText(log.getCongdung());
            holder.tvHinhanh.setText(log.getHinhanh());
            holder.tvCangdi.setText(log.getCangdi());
            holder.tvCangden.setText(log.getCangden());
            holder.tvLoaihang.setText(log.getLoaihang());
            holder.tvSoluongcuthe.setText(log.getSoluongcuthe());
            holder.tvYeucaudacbiet.setText(log.getYeucaudacbiet());
            holder.tvValid.setText(log.getValid());

        }
        return;
    }

    @Override
    public int getItemCount() {
        if(mPriceListLog != null && mPriceListLog.size()>0){
            return mPriceListLog.size();
        }
        return 0;
    }
    public void setDataLog(List<Log> mListDetailLog) {
        this.mPriceListLog = mListDetailLog;
        notifyDataSetChanged();
    }

    public class LogViewHolder extends RecyclerView.ViewHolder{
        private TextView tvStt, tvTenHang, tvHScode, tvCongdung, tvHinhanh, tvCangdi, tvCangden,
        tvLoaihang, tvSoluongcuthe, tvYeucaudacbiet, tvValid;

        public LogViewHolder(@NonNull View itemView) {

            super(itemView);
            tvStt = itemView.findViewById(R.id.tv_row_price_log_stt);
            tvTenHang = itemView.findViewById(R.id.tv_row_price_log_tenhang);
            tvHScode = itemView.findViewById(R.id.tv_row_price_log_hscode);
            tvCongdung = itemView.findViewById(R.id.tv_row_price_log_congdung);
            tvHinhanh = itemView.findViewById(R.id.tv_row_price_log_hinhanh);
            tvCangdi = itemView.findViewById(R.id.tv_row_price_log_cangdi);
            tvCangden = itemView.findViewById(R.id.tv_row_price_log_cangden);
            tvLoaihang = itemView.findViewById(R.id.tv_row_price_log_loaihang);
            tvSoluongcuthe = itemView.findViewById(R.id.tv_row_price_log_soluongcuthe);
            tvYeucaudacbiet = itemView.findViewById(R.id.tv_row_price_log_yeucaudacbiet);
            tvValid = itemView.findViewById(R.id.tv_row_price_log_valid);


        }
    }
}
