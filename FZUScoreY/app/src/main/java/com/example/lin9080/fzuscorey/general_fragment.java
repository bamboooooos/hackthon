package com.example.lin9080.fzuscorey;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.lin9080.fzuscorey.R;
import com.example.lin9080.fzuscorey.StuActivity;
import com.example.lin9080.fzuscorey.Student;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class general_fragment extends Fragment {
    private ArrayList<recycler_item> items = new ArrayList<>();
    Student mstudent;
    ArrayList<Student> students;
    int term=1;
    private String id;
    private RadioGroup radioGroup1;
    private StudentAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_fragment,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.score_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        StuActivity stuActivity = (StuActivity) getActivity();
         id= stuActivity.getAcco();
        students= (ArrayList<Student>) LitePal.findAll(Student.class);
        radioGroup1 = (RadioGroup) view.findViewById(R.id.stu_f1_l1_rg);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup1.getCheckedRadioButtonId()){
                    case R.id.stu_f1_l1_rb1:
                        term = 1;
                        getItemNeed(term);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.stu_f1_l1_rb2:
                        term = 2;
                        getItemNeed(term);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.stu_f1_l1_rb3:
                        term = 3;
                        getItemNeed(term);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.stu_f1_l1_rb4:
                        term = 4;
                        getItemNeed(term);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        getItemNeed(term);
        adapter = new StudentAdapter(items);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void getItemNeed(int term){//5个参数,科目，分数，科目平均分，科目排名，科目百分比排名
        for(Student student:students){
            if(student.getStuid().equals(id)){
                if(student.getTerm()==term) {
                    mstudent = student;
                }
            }
        }
        items.clear();
        SortUtil sortUtil=new SortUtil(students,term);
        recycler_item item=new recycler_item(mstudent.getName_1(),mstudent.getScore_1()+"",sortUtil.getAverage(1)+"",sortUtil.getRank(mstudent.getStuid(),1)+"",sortUtil.getPrank(mstudent.getStuid(),1)+"");
        items.add(item);
        item=new recycler_item(mstudent.getName_2(),mstudent.getScore_2()+"",sortUtil.getAverage(2)+"",sortUtil.getRank(mstudent.getStuid(),2)+"",sortUtil.getPrank(mstudent.getStuid(),2)+"");
        items.add(item);
        item=new recycler_item(mstudent.getName_3(),mstudent.getScore_3()+"",sortUtil.getAverage(3)+"",sortUtil.getRank(mstudent.getStuid(),3)+"",sortUtil.getPrank(mstudent.getStuid(),3)+"");
        items.add(item);
        item=new recycler_item(mstudent.getName_4(),mstudent.getScore_4()+"",sortUtil.getAverage(4)+"",sortUtil.getRank(mstudent.getStuid(),4)+"",sortUtil.getPrank(mstudent.getStuid(),4)+"");
        items.add(item);
        item=new recycler_item(mstudent.getName_5(),mstudent.getScore_5()+"",sortUtil.getAverage(5)+"",sortUtil.getRank(mstudent.getStuid(),5)+"",sortUtil.getPrank(mstudent.getStuid(),5)+"");
        items.add(item);
        item=new recycler_item(mstudent.getName_6(),mstudent.getScore_6()+"",sortUtil.getAverage(6)+"",sortUtil.getRank(mstudent.getStuid(),6)+"",sortUtil.getPrank(mstudent.getStuid(),6)+"");
        items.add(item);
    }
}