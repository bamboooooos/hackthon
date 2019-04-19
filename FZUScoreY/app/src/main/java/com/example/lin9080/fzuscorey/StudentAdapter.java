package com.example.lin9080.fzuscorey;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private ArrayList<recycler_item> items;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView text1,text2,text3,text4,text5;
        public ViewHolder(View view){
            super(view);
            text1 = (TextView) view.findViewById(R.id.text1);
            text2 = (TextView) view.findViewById(R.id.text2);
            text3 = (TextView) view.findViewById(R.id.text3);
            text4 = (TextView) view.findViewById(R.id.text4);
            text5 = (TextView) view.findViewById(R.id.text5);
        }
    }

    public StudentAdapter(ArrayList<recycler_item> items){
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recycler_item recyclerItem = items.get(position);
        holder.text1.setText(recyclerItem.getSubject());
        holder.text2.setText(recyclerItem.getScore());
        holder.text3.setText(recyclerItem.getAverage());
        holder.text4.setText(recyclerItem.getPrank());
        holder.text5.setText(recyclerItem.getRank());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}