package com.example.mayjunejuly.myapplication;

/**
 * Created by MAYJUNEJULY on 1/26/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    private List<Product> listProductModel;
    private Context context;

    public AdapterProduct(List<Product> listProductModel, Context context) {
        this.listProductModel = listProductModel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.container_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = listProductModel.get(position);
        try{
            //loading the image
            Glide.with(context)
                    .load(product.getImage())
                    .into(holder.imageView);

            holder.textViewName.setText(product.getName());
            holder.textViewDescription.setText(product.getDescription());
            holder.textViewStock.setText(String.valueOf(product.getStock()));
            holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listProductModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName, textViewDescription, textViewStock, textViewPrice;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewStock = itemView.findViewById(R.id.textViewStock);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}