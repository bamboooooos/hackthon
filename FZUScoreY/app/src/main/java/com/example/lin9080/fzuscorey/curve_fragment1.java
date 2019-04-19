package com.example.lin9080.fzuscorey;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.github.mikephil.charting.charts.RadarChart;

import org.litepal.LitePal;

import java.util.ArrayList;

public class curve_fragment1 extends Fragment {
    private int term=1;
    private ArrayList<Student> students=new ArrayList<>();
    private RadarChart radarChart;
    private RadioGroup radioGroup1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curve1,container,false);
        radarChart=(RadarChart)view.findViewById(R.id.curve1RadarChart);
        students.clear();
        students.addAll((ArrayList<Student>) LitePal.findAll(Student.class));
        getDataNeed(term);
        radioGroup1 = (RadioGroup)view.findViewById(R.id.mon_f3_l1_rg);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup1.getCheckedRadioButtonId()){
                    case R.id.mon_f3_l1_rb1:
                        term = 1;
                        getDataNeed(term);
                        break;
                    case R.id.mon_f3_l1_rb2:
                        term = 2;
                        getDataNeed(term);
                        break;
                    case R.id.mon_f3_l1_rb3:
                        term = 3;
                        getDataNeed(term);
                        break;
                    case R.id.mon_f3_l1_rb4:
                        term = 4;
                        getDataNeed(term);
                        break;
                }
            }
        });
        return view;
    }

    private void getDataNeed(int term){
        SortUtil sortUtil=new SortUtil(students,term);
        Student student=sortUtil.getSortedStudents().get(0);
        ArrayList<ChartBean> chartBeans=new ArrayList<>();
        chartBeans.add(new ChartBean(sortUtil.getAverage(1),student.getName_1()));
        chartBeans.add(new ChartBean(sortUtil.getAverage(2),student.getName_2()));
        chartBeans.add(new ChartBean(sortUtil.getAverage(3),student.getName_3()));
        chartBeans.add(new ChartBean(sortUtil.getAverage(4),student.getName_4()));
        chartBeans.add(new ChartBean(sortUtil.getAverage(5),student.getName_5()));
        chartBeans.add(new ChartBean(sortUtil.getAverage(6),student.getName_6()));
        RadarChartUtil.initRadarChartUtil(radarChart,chartBeans, Color.RED,Color.GREEN,"班级内部相对优劣势学科分析");
    }
}
