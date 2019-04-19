package com.example.lin9080.fzuscorey;

import android.graphics.Color;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LineChartUtil {
    public static void initLineChart(LineChart chart, ArrayList<ArrayList<ChartBean>> chartBeanList,
                                     int[] colors,int[] circleColors,ArrayList<String> labels){//chart实例,
                                        // chartBean表列的表列(每条线一个),对应线色，对应图例
        XAxis xAxis=chart.getXAxis();
        YAxis leftYAxis=chart.getAxisLeft();
        YAxis rightYAxis=chart.getAxisRight();
        leftYAxis.setEnabled(true);
        rightYAxis.setEnabled(false);
        xAxis.setTextColor(Color.BLACK);//x轴字体颜色
        xAxis.setTextSize(11f);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(4f);
        xAxis.setDrawAxisLine(true);//轴线
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        leftYAxis.setTextColor(Color.RED);
        leftYAxis.setTextSize(11f);
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(100f);
        leftYAxis.setDrawLabels(true);
        leftYAxis.setDrawAxisLine(true);
        leftYAxis.setDrawGridLines(true);
        leftYAxis.setGranularity(1f);
        ArrayList<String> xAxisValue=new ArrayList<>();
        ArrayList<ChartBean> example=chartBeanList.get(1);
        for(int i=0;i<example.size();i++){
            xAxisValue.add(example.get(i).getValName());
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));
        initData(chart,chartBeanList,colors,circleColors,labels);
    }
    public static void initLineDataSet(LineDataSet dataSet,int color,int circleColor){
        dataSet.setColor(color);//线色
        dataSet.setCircleColor(circleColor);//点色
        dataSet.setLineWidth(1f);//线宽
    }

    public static void initData(LineChart chart,ArrayList<ArrayList<ChartBean>> chartBeanList,int[] colors,int[] circleColors,ArrayList<String> labels){
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        for(int i=0;i<chartBeanList.size();i++){
            ArrayList<Entry> entries=new ArrayList<>();
            ArrayList<ChartBean> chartBeans=chartBeanList.get(i);
            for(int j=0;j<chartBeans.size();j++){
                entries.add(new Entry(j,chartBeans.get(j).getValue()));
            }
            LineDataSet dataSet=new LineDataSet(entries,labels.get(i));
            initLineDataSet(dataSet,colors[i],circleColors[i]);
            dataSets.add(dataSet);
        }
        LineData data=new LineData(dataSets);
        data.setDrawValues(true);//点值
        chart.setData(data);
        chart.invalidate();
    }
}
