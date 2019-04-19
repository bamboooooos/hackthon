package com.example.lin9080.fzuscorey;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.litepal.LitePal;

import java.util.ArrayList;

public class analysis_fragment1 extends Fragment {
    private int term=1;
    private int sub=0;
    private ArrayList<Student> students=new ArrayList<>();
    private ArrayList<Student> sortedStudents=new ArrayList<>();
    final AllStuAdapter adapter=new AllStuAdapter(sortedStudents);
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_analysis1,container,false);
        MonActivity monActivity=(MonActivity)getActivity();
        students.clear();
        students.addAll((ArrayList<Student>) LitePal.findAll(Student.class));
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.analysis1RecyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getDataNeed(term,sub);
        initRadioButton(view);
        initRadioGroup(view);
        return view;
    }

    private void getDataNeed(int term,int sub){
        adapter.setSub(sub);
        SortUtil sortUtil=new SortUtil(students,term);
        sortUtil.SortStudent(sub);
        sortedStudents.clear();
        sortedStudents.addAll(sortUtil.getSortedStudents());
        adapter.notifyDataSetChanged();
    }

    private void initRadioGroup(final View view){
        radioGroup1 = (RadioGroup)view.findViewById(R.id.mon_f2_l1_rg);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup1.getCheckedRadioButtonId()){
                    case R.id.mon_f2_l1_rb1:
                        term = 1;
                        getDataNeed(term,sub);
                        initRadioButton(view);
                        break;
                    case R.id.mon_f2_l1_rb2:
                        term = 2;
                        getDataNeed(term,sub);
                        initRadioButton(view);
                        break;
                    case R.id.mon_f2_l1_rb3:
                        term = 3;
                        getDataNeed(term,sub);
                        initRadioButton(view);
                        break;
                    case R.id.mon_f2_l1_rb4:
                        term = 4;
                        getDataNeed(term,sub);
                        initRadioButton(view);
                        break;
                }
            }
        });
        radioGroup2 = (RadioGroup)view.findViewById(R.id.mon_f2_l2_rg);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup2 .getCheckedRadioButtonId()){
                    case R.id.mon_f2_l2_rb1:
                        sub = 0;
                        getDataNeed(term,sub);
                        break;
                    case R.id.mon_f2_l2_rb2:
                        sub = 1;
                        getDataNeed(term,sub);
                        break;
                    case R.id.mon_f2_l2_rb3:
                        sub = 2;
                        getDataNeed(term,sub);
                        break;
                    case R.id.mon_f2_l2_rb4:
                        sub = 3;
                        getDataNeed(term,sub);
                        break;
                    case R.id.mon_f2_l2_rb5:
                        sub = 4;
                        getDataNeed(term,sub);
                        break;
                    case R.id.mon_f2_l2_rb6:
                        sub = 5;
                        getDataNeed(term,sub);
                        break;
                    case R.id.mon_f2_l2_rb7:
                        sub = 6;
                        getDataNeed(term,sub);
                        break;
                }
            }
        });
    }
    private void initRadioButton(View view){
        Student studentExample=sortedStudents.get(0);
        ((RadioButton)(view.findViewById(R.id.mon_f2_l2_rb1))).setText("总分");
        ((RadioButton)(view.findViewById(R.id.mon_f2_l2_rb2))).setText(studentExample.getName_1());
        ((RadioButton)(view.findViewById(R.id.mon_f2_l2_rb3))).setText(studentExample.getName_2());
        ((RadioButton)(view.findViewById(R.id.mon_f2_l2_rb4))).setText(studentExample.getName_3());
        ((RadioButton)(view.findViewById(R.id.mon_f2_l2_rb5))).setText(studentExample.getName_4());
        ((RadioButton)(view.findViewById(R.id.mon_f2_l2_rb6))).setText(studentExample.getName_5());
        ((RadioButton)(view.findViewById(R.id.mon_f2_l2_rb7))).setText(studentExample.getName_6());
    }
}
