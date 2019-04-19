package com.example.lin9080.fzuscorey;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;

import org.litepal.LitePal;

import java.util.ArrayList;

public class curve_fragment extends Fragment {
    private ArrayList<Student> students=new ArrayList<>();
    private ArrayList<ChartBean> chartBeans=new ArrayList<>();
    private ArrayList<ArrayList<ChartBean>> chartBeansList=new ArrayList<>();
    private ArrayList<Student> mstudents=new ArrayList<>();
    private String acc;
    private View view;
    private LineChart chart;
    private int term=1;
    private int[] colors=new int[2];
    private int[] circleColors=new int[2];
    private ArrayList<String> labels=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_curve_fragment, container, false);
        students.clear();
        students.addAll((ArrayList<Student>) LitePal.findAll(Student.class));
        StuActivity stuActivity = (StuActivity) getActivity();
        acc = stuActivity.getAcco();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStuid().equals(acc)) {
                mstudents.add(students.get(i));
            }
        }
        getDataNeed();
        chart=(LineChart)view.findViewById(R.id.lineChat1);
        labels.add("百分比排名");
        labels.add("平均分");
        LineChartUtil.initLineChart(chart,chartBeansList, colors,circleColors,labels);
        return view;
    }
    private void getDataNeed() {
        chartBeansList.clear();
        ArrayList<ChartBean> chartBeans1=new ArrayList<>();//百分比排名
        ArrayList<ChartBean> chartBeans2=new ArrayList<>();
        for(term=1;term<5;term++) {
            SortUtil sortUtil = new SortUtil(students, term);
            sortUtil.SortStudent(0);//按全部排名
            Student mstudent=new Student();
            for(int i=0;i<mstudents.size();i++){
                if(mstudents.get(i).getTerm()==term){
                    mstudent=mstudents.get(i);
                    break;
                }
            }
            chartBeans1.add(new ChartBean((int)sortUtil.getDoublePrank(mstudents.get(0).getStuid(),0),"第"+term+"学期"));
            chartBeans2.add(new ChartBean((mstudent.getScore_1()+mstudent.getScore_2()+
                    mstudent.getScore_3()+mstudent.getScore_4()+
                    mstudent.getScore_5()+mstudent.getScore_6())/6,"第"+term+"学期"));
        }
        chartBeansList.add(chartBeans1);
        colors[0]=Color.GREEN;
        circleColors[0]=Color.LTGRAY;
        chartBeansList.add(chartBeans2);
        colors[1]=Color.DKGRAY;
        circleColors[1]=Color.RED;
    }
}
