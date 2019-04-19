package com.example.lin9080.fzuscorey;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AllStuAdapter extends RecyclerView.Adapter<AllStuAdapter.ViewHolder> {
    private ArrayList<Student> items;
    private int sub=1;
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

    public void setSub(int sub){
        this.sub=sub;
    }

    public AllStuAdapter(ArrayList<Student> items){
        this.items = items;
    }

    @Override
    public AllStuAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);
        AllStuAdapter.ViewHolder holder = new AllStuAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllStuAdapter.ViewHolder holder, int position) {
        Student student = items.get(position);
        holder.text1.setText(position+1+"");
        holder.text2.setText(student.getStu_name());
        holder.text3.setText(student.getStuid());
        int score=StudentUtil.getSubjectScore(student,sub);
        holder.text4.setText(score+"");
        String grade="";
        if(sub==0) score=score/6;
        if(score<60){
            grade="不及格";
        }else if(score<70){
            grade="及格";
        }else if(score<85){
            grade="良好";
        }else grade="优秀";
        holder.text5.setText(grade);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
