package com.example.lin9080.fzuscorey;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/*
 * 此类为BarChart的工具类
 * init为初始化BarChart,第一参数为BarChart实例,第二参数为ChartBean列表
 *       1-4:学期1-4,5:按成绩
 * show为显示,输入值方法,第一参数为BarChart实例,第二为BarChart所需要值的类ChartBean列表,第三第四为图例名和颜色
 *ChartBean有两个参数:值和名,为柱状图柱的两个参数
 *
 * */

public class BarChartUtil {
    /* XAxis是x轴，YAxis是y轴
    *
    * */
    private static XAxis xAxis;
    private static YAxis leftAxis;
    private static YAxis rightAxis;
    public static void initBarChart(BarChart barChart,ArrayList<ChartBean> chartBeans){
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setDrawBarShadow(false);//阴影
        barChart.setDrawGridBackground(false);//网格
        barChart.setHighlightFullBarEnabled(true);//边框
        barChart.setDrawBorders(true);//动画
        barChart.animateX(1000);
        barChart.animateY(1000);
        xAxis = barChart.getXAxis();//x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//底部x轴
        xAxis.setAxisMinimum(0f);//x轴显示最低值
        xAxis.setGranularity(1f);//x轴标记间隔
        xAxis.setAxisMinimum(-0.5f);//使第一个柱正常显示
        List<String> xAxisValue=new ArrayList<>();
        for(int i=0;i<chartBeans.size();i++){
            xAxisValue.add(chartBeans.get(i).getValName());
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));
        leftAxis = barChart.getAxisLeft();//左侧y轴
        rightAxis = barChart.getAxisRight();//右侧y轴
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);
        Legend legend = barChart.getLegend();//图例
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }
    public static void initBarDataSet(BarDataSet dataSet, int color){
        dataSet.setColor(color);
        dataSet.setFormLineWidth(1f);
        dataSet.setFormSize(15.f);
        dataSet.setDrawValues(true);
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(color);
    }

    public static void showBarChart(BarChart barChart,ArrayList<ChartBean> chartBeans, String label, int color) {//单个数据
        //chartBeans为表格数据来源，包括值和名称,name为图例,color为颜色
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < chartBeans.size(); i++) {
            BarEntry barEntry = new BarEntry(i,chartBeans.get(i).getValue());//每个柱子
            entries.add(barEntry);
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);
        initBarDataSet(barDataSet, color);
        BarData data = new BarData(barDataSet);
        barChart.setData(data);
    }
    public static void showBarChart(BarChart barChart,ArrayList<ArrayList<ChartBean>> chartBeansList,int[] color,ArrayList<String> labels) {//多个数据
        //chartBeans为表格数据来源，包括值和名称
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        for(int i=0;i<chartBeansList.size();i++){
            ArrayList<BarEntry> entries=new ArrayList<>();
            ArrayList<ChartBean> chartBeans=chartBeansList.get(i);
            for(int j=0;j<chartBeans.size();j++){
                entries.add(new BarEntry(j,chartBeans.get(j).getValue()));
            }
            BarDataSet dataSet=new BarDataSet(entries,labels.get(i));
            initBarDataSet(dataSet,color[i]);
            dataSets.add(dataSet);
        }
        BarData data = new BarData(dataSets);

        int barAmount = 6; //需要显示柱状图的类别 数量
//设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        float groupSpace = 0.1f; //柱状图组之间的间距
        float barWidth = 0.1f;
        float barSpace = 0.05f;
//设置柱状图宽度
        data.setBarWidth(barWidth);
//(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0f, groupSpace, barSpace);
        barChart.setData(data);
    }
}