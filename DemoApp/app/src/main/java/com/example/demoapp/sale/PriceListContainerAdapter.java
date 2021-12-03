package com.example.demoapp.sale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.model.PriceListContainerSale;

import java.util.ArrayList;
import com.example.demoapp.R;
import com.example.demoapp.my_interface.IClickItemContainerPriceList;

public class PriceListContainerAdapter extends RecyclerView.Adapter<PriceListContainerAdapter.PriceListContainerHolde> {

    private ArrayList<PriceListContainerSale> containerSaleArrayList;
    private IClickItemContainerPriceList iClickItemContainerPriceList;

    public PriceListContainerAdapter(ArrayList<PriceListContainerSale> containerSaleArrayList, IClickItemContainerPriceList listener) {
        this.containerSaleArrayList = containerSaleArrayList;
        this.iClickItemContainerPriceList = listener;
    }

    @NonNull
    @Override
    public PriceListContainerHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_pricelistcontainer,parent,false);

        PriceListContainerHolde viewHolder = new PriceListContainerHolde(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PriceListContainerHolde holder, int position) {
        PriceListContainerSale containerSale = containerSaleArrayList.get(position);

        holder.tvType.setText(containerSale.getType());
        holder.tvPOl.setText("POL: "+ containerSale.getPol());
        holder.tvPOD.setText("POD: "+ containerSale.getPod());
        holder.tvOF20.setText("O/F 20: "+containerSale.getOf20());
        holder.tvOF40.setText("O/F 40: "+containerSale.getOf40());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemContainerPriceList.onClickItemPriceContainer();
            }
        });

    }

    @Override
    public int getItemCount() {
        return containerSaleArrayList.size();
    }


    public class PriceListContainerHolde extends RecyclerView.ViewHolder{
       private TextView tvType;
       private TextView tvPOl;
       private TextView tvPOD;
       private TextView tvOF20;
       private TextView tvOF40;
       private LinearLayout linearLayout;
        public PriceListContainerHolde(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.layout_item);
            tvType = itemView.findViewById(R.id.item_pricelistcontainer_tvType);
            tvPOl = itemView.findViewById(R.id.item_pricelistcontainer_tvPOL);
            tvPOD = itemView.findViewById(R.id.item_pricelistcontainer_tvPOD);
            tvOF20 = itemView.findViewById(R.id.item_pricelistcontainer_tvOF20);
            tvOF40 = itemView.findViewById(R.id.item_pricelistcontainer_tvOF40);

        }
    }
}
