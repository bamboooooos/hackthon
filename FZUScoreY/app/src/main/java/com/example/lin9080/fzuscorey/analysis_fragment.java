package com.example.lin9080.fzuscorey;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import org.litepal.LitePal;

import java.util.ArrayList;

public class analysis_fragment extends Fragment {
    private ArrayList<Student> students=new ArrayList<>();
    private ArrayList<ChartBean> chartBeans=new ArrayList<>();
    private Student mstudent;
    private String acc;
    private View view;
    private RadarChart chart;
    private int term=1;
    private RadioGroup radioGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_analysis_fragment, container, false);
        students.clear();
        students.addAll((ArrayList<Student>) LitePal.findAll(Student.class));
        StuActivity stuActivity = (StuActivity) getActivity();
        acc = stuActivity.getAcco();
        radioGroup = (RadioGroup) view.findViewById(R.id.stu_f2_l1_rg);
        getDataNeed();
        chart=(RadarChart)view.findViewById(R.id.radarchart);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.stu_f2_l1_rb1:
                        Log.d("按键", "1 ");
                        term = 1;
                        getDataNeed();
                        break;
                    case R.id.stu_f2_l1_rb2:
                        Log.d("按键", "1 ");
                        term = 2;
                        getDataNeed();
                        break;
                    case R.id.stu_f2_l1_rb3:
                        term = 3;
                        getDataNeed();
                        break;
                    case R.id.stu_f2_l1_rb4:
                        term = 4;
                        getDataNeed();
                        break;
                }
                RadarChartUtil.initRadarChartUtil(chart,chartBeans,Color.RED,Color.GREEN,"个人优劣势学科分析");
            }
        });
        RadarChartUtil.initRadarChartUtil(chart,chartBeans,Color.RED,Color.GREEN,"个人优劣势学科分析");
        return view;
    }
    private void getDataNeed() {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStuid().equals(acc)) {
                if (students.get(i).getTerm() == term) {
                    mstudent=students.get(i);
                }
            }
        }
        chartBeans.clear();
        chartBeans.add(new ChartBean(mstudent.getScore_1(),mstudent.getName_1()));
        chartBeans.add(new ChartBean(mstudent.getScore_2(),mstudent.getName_2()));
        chartBeans.add(new ChartBean(mstudent.getScore_3(),mstudent.getName_3()));
        chartBeans.add(new ChartBean(mstudent.getScore_4(),mstudent.getName_4()));
        chartBeans.add(new ChartBean(mstudent.getScore_5(),mstudent.getName_5()));
        chartBeans.add(new ChartBean(mstudent.getScore_6(),mstudent.getName_6()));
    }
}