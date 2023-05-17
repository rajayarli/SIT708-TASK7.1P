package com.example.task7p;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ItemViewHolder> {
    private List<Item> itemList;
    private Context context;
    private RowClickListener listener;

    public interface RowClickListener{
        void onItemClick(int id);
    }
    public AdapterItem(List<Item> itemList, Context context, RowClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterItem.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        return new ItemViewHolder(itemView,listener);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItem.ItemViewHolder holder, int position) {
        String itemDisplayName = "Item " + (position + 1) + ": " + itemList.get(position).getLost() + " - " + itemList.get(position).getDescription();
        holder.item_1.setText(itemDisplayName);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item_1;
        public RowClickListener onRowClickListener;
        public ItemViewHolder(@NonNull View itemView,RowClickListener listener) {
            super(itemView);
            this.onRowClickListener = listener;
            itemView.setOnClickListener(this);
            item_1 = itemView.findViewById(R.id.item_1);
        }
        @Override
        public void onClick(View view) {
            onRowClickListener.onItemClick(itemList.get(getAdapterPosition()).getId());
        }
    }

}