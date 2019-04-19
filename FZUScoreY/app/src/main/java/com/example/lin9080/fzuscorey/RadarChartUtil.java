package com.example.lin9080.fzuscorey;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class RadarChartUtil {
    public static void initRadarChartUtil(RadarChart chart, ArrayList<ChartBean> chartBeans,int SetColor,int FillColor,String descrip){
        XAxis xAxis;
        YAxis yAxis;
        Description description=chart.getDescription();
        description.setText(descrip);
        description.setTextSize(30);
        description.setTextColor(Color.BLUE);
        description.setEnabled(true);//描述显示
        chart.setWebLineWidth(1f);//边线宽度
        chart.setWebColor(Color.LTGRAY);//边线色
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);//边线内部颜色
        chart.setWebAlpha(100);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);//样式
        chart.setLayoutParams(layoutParams);
        chart.setRotationEnabled(false);

        setData(chart,chartBeans,SetColor,FillColor);

        chart.animateXY(//设置绘制动画
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        xAxis = chart.getXAxis();
        xAxis.setTextSize(12f);//x轴文字大小
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        ArrayList<String> xAxisValue=new ArrayList<>();
        for(int i=0;i<chartBeans.size();i++){
            xAxisValue.add(chartBeans.get(i).getValName());
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));//x轴文字
        xAxis.setTextColor(Color.BLACK);//x轴文字颜色

        yAxis = chart.getYAxis();
        yAxis.setLabelCount(3, true);//设置y轴的标签数量,force:false表示不严格
        yAxis.setTextSize(12f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);//网格最大值
        yAxis.setDrawLabels(false);//绘制轴标签

        Legend legend = chart.getLegend();//图例
        legend.setForm(Legend.LegendForm.LINE);//图例样式
        legend.setEnabled(true);
    }
    private static void initDataSet(RadarDataSet set,ArrayList<RadarEntry> entries,int SetColor,int FillColor){
        set.setColor(SetColor);//线色
        set.setFillColor(FillColor);//图层填充色
        set.setDrawFilled(true);
        set.setFillAlpha(180);
        set.setLineWidth(1f);
        set.setDrawHighlightCircleEnabled(true);
        set.setDrawHighlightIndicators(false);
    }
    public static void setData(RadarChart chart,ArrayList<ChartBean> chartBeans,int SetColor,int FillColor) {//设置数据
        float mult = 100;
        float min = 20;

        ArrayList<RadarEntry> entries = new ArrayList<RadarEntry>();

        for (int i = 0; i < chartBeans.size(); i++) {
            entries.add(new RadarEntry(chartBeans.get(i).getValue()));//雷达网赋值
        }

        RadarDataSet set = new RadarDataSet(entries,"成绩");//label为图例文字
        initDataSet(set,entries,SetColor,FillColor);//雷达延伸爪初始化

        RadarData data = new RadarData(set);
        data.setValueTextSize(12f);//雷达爪值字体大小
        data.setDrawValues(true);//雷达爪值显示
        data.setValueTextColor(Color.DKGRAY);//颜色
        chart.setData(data);
        chart.invalidate();//刷新
    }
}
