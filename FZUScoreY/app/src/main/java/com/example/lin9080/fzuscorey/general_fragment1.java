package com.example.lin9080.fzuscorey;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

import org.litepal.LitePal;

import java.math.BigDecimal;
import java.util.ArrayList;

public class general_fragment1 extends Fragment {
    private ArrayList<Student> students=new ArrayList<>();
    private ArrayList<ChartBean> chartBeans=new ArrayList<>();
    private ArrayList<ArrayList<ChartBean>> chartBeansList=new ArrayList<>();
    private ArrayList<Student> mstudents=new ArrayList<>();
    private View view;
    private LineChart chart;
    private int term=1;
    private int sub=0;
    private int[] colors=new int[2];
    private int[] circleColors=new int[2];
    private ArrayList<String> labels=new ArrayList<>();
    private BarChart barChart;
    private BarChart barChart2;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_general1, container, false);
        students.clear();
        students.addAll((ArrayList<Student>) LitePal.findAll(Student.class));
        MonActivity monActivity = (MonActivity) getActivity();
        barChart =(BarChart)view.findViewById(R.id.genera1BarChart);
        barChart2=(BarChart)view.findViewById(R.id.genera1BarChart2);
        term=monActivity.getTerm();
        initTerm(term,sub);
        initRadioButton(view);
        initRadioGroup(view);
        return view;
    }
    private void initTerm(int term,int sub){
        mstudents.clear();
        for(int i=0;i<students.size();i++){
            if(students.get(i).getTerm()==term){
                mstudents.add(students.get(i));
            }
        }
        getDataNeed(sub);
        BarChartUtil.initBarChart(barChart,chartBeans);
        BarChartUtil.showBarChart(barChart,chartBeans,"成绩/占比",Color.BLUE);
        getClassGrade(term,sub);
        BarChartUtil.initBarChart(barChart2,chartBeans);
        BarChartUtil.showBarChart(barChart2,chartBeans,"人数占比",Color.CYAN);
    }
    private void getDataNeed(int sub){
        chartBeans.clear();
        int result=0;
        int high=0;
        int low=100;
        int excellent=0;
        int fail=0;
        int[] specilScore={85,60};
        for(int m=0;m<mstudents.size();m++){
            result+=StudentUtil.getSubjectScore(mstudents.get(m),sub);
            if(StudentUtil.getSubjectScore(mstudents.get(m),sub)>high) {
                high=StudentUtil.getSubjectScore(mstudents.get(m),sub);
            }
            if(sub==0){
                low=600;
            }
            if(StudentUtil.getSubjectScore(mstudents.get(m),sub)<low){
                low=StudentUtil.getSubjectScore(mstudents.get(m),sub);
            }
            if(sub==0){
                specilScore[0]=510;
                specilScore[1]=360;
            }
            if(StudentUtil.getSubjectScore(mstudents.get(m),sub)>specilScore[0]){
                excellent++;
            }
            if(StudentUtil.getSubjectScore(mstudents.get(m),sub)<specilScore[1]){
                fail++;
            }
        }
        BigDecimal bd=new BigDecimal(((double)excellent/(double)(mstudents.size())*100)).setScale(0, BigDecimal.ROUND_HALF_UP);
        chartBeans.add(new ChartBean(Integer.parseInt(bd.toString()),"优秀率"));
        BigDecimal bd1=new BigDecimal(((double)fail/(double)(mstudents.size())*100)).setScale(0, BigDecimal.ROUND_HALF_UP);
        chartBeans.add(new ChartBean(Integer.parseInt(bd1.toString()),"不及格率"));
        chartBeans.add(new ChartBean(result/(mstudents.size()),"平均分"));
        chartBeans.add(new ChartBean(high,"最高分"));
        chartBeans.add(new ChartBean(low,"最低分"));
    }

    private void getClassGrade(int term,int sub){
        SortUtil sortUtil=new SortUtil(students,term);
        chartBeans.clear();
        int[] grades=sortUtil.getGrade(sub);
        chartBeans.add(new ChartBean(grades[0],"不及格"));
        chartBeans.add(new ChartBean(grades[1],"及格"));
        chartBeans.add(new ChartBean(grades[2],"良好"));
        chartBeans.add(new ChartBean(grades[3],"优秀"));
    }

    private void initRadioGroup(final View view){
        radioGroup1 = (RadioGroup)view.findViewById(R.id.mon_f1_l1_rg);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup1.getCheckedRadioButtonId()){
                    case R.id.mon_f1_l1_rb1:
                        term = 1;
                        initTerm(term,sub);
                        initRadioButton(view);
                        break;
                    case R.id.mon_f1_l1_rb2:
                        term = 2;
                        initTerm(term,sub);
                        initRadioButton(view);
                        break;
                    case R.id.mon_f1_l1_rb3:
                        term = 3;
                        initTerm(term,sub);
                        initRadioButton(view);
                        break;
                    case R.id.mon_f1_l1_rb4:
                        term = 4;
                        initTerm(term,sub);
                        initRadioButton(view);
                        break;
                }
            }
        });
        radioGroup2 = (RadioGroup)view.findViewById(R.id.mon_f1_l2_rg);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup2 .getCheckedRadioButtonId()){
                    case R.id.mon_f1_l2_rb1:
                        sub = 0;
                        initTerm(term,sub);
                        break;
                    case R.id.mon_f1_l2_rb2:
                        sub = 1;
                        initTerm(term,sub);
                        break;
                    case R.id.mon_f1_l2_rb3:
                        sub = 2;
                        initTerm(term,sub);
                        break;
                    case R.id.mon_f1_l2_rb4:
                        sub = 3;
                        initTerm(term,sub);
                        break;
                    case R.id.mon_f1_l2_rb5:
                        sub = 4;
                        initTerm(term,sub);
                        break;
                    case R.id.mon_f1_l2_rb6:
                        sub = 5;
                        initTerm(term,sub);
                        break;
                    case R.id.mon_f1_l2_rb7:
                        sub = 6;
                        initTerm(term,sub);
                        break;
                }
            }
        });
    }

    private void initRadioButton(View view){
        Student studentExample=mstudents.get(0);
        ((RadioButton)(view.findViewById(R.id.mon_f1_l2_rb1))).setText("总分");
        ((RadioButton)(view.findViewById(R.id.mon_f1_l2_rb2))).setText(studentExample.getName_1());
        ((RadioButton)(view.findViewById(R.id.mon_f1_l2_rb3))).setText(studentExample.getName_2());
        ((RadioButton)(view.findViewById(R.id.mon_f1_l2_rb4))).setText(studentExample.getName_3());
        ((RadioButton)(view.findViewById(R.id.mon_f1_l2_rb5))).setText(studentExample.getName_4());
        ((RadioButton)(view.findViewById(R.id.mon_f1_l2_rb6))).setText(studentExample.getName_5());
        ((RadioButton)(view.findViewById(R.id.mon_f1_l2_rb7))).setText(studentExample.getName_6());
    }
}
