package com.example.lin9080.fzuscorey;

import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SortUtil {
    private ArrayList<Student> students;
    private ArrayList<Student> sortedStudents=new ArrayList<>();
    public SortUtil(ArrayList<Student> students,int term){
        this.students=students;
        for(int i=0;i<students.size();i++){
            if(term==students.get(i).getTerm()){
                sortedStudents.add(students.get(i));
            }
        }
    }
    public void SortStudent(int sub){//排序学生,0是全部,1-6是按科目1到6排序,从高到低
        if(sortedStudents==null){
            Log.d("错误", "没有学生列表");
        }else{
            switch (sub){
                case 0:
                    for(int i=0;i<sortedStudents.size()-1;i++){
                        for(int j=i+1;j<sortedStudents.size();j++){
                            if(addall(sortedStudents.get(i))<addall(sortedStudents.get(j))){
                                Student student1=sortedStudents.get(i);
                                sortedStudents.set(i,sortedStudents.get(j));
                                sortedStudents.set(j,student1);
                            }
                        }
                    }
                    break;
                case 1:
                    for(int i=0;i<sortedStudents.size()-1;i++){
                        for(int j=i+1;j<sortedStudents.size();j++){
                            if(sortedStudents.get(i).getScore_1()<sortedStudents.get(j).getScore_1()){
                                Student student1=sortedStudents.get(i);
                                sortedStudents.set(i,sortedStudents.get(j));
                                sortedStudents.set(j,student1);
                            }
                        }
                    }
                    break;
                case 2:
                    for(int i=0;i<sortedStudents.size()-1;i++){
                        for(int j=i+1;j<sortedStudents.size();j++){
                            if(sortedStudents.get(i).getScore_2()<sortedStudents.get(j).getScore_2()){
                                Student student1=sortedStudents.get(i);
                                sortedStudents.set(i,sortedStudents.get(j));
                                sortedStudents.set(j,student1);
                            }
                        }
                    }
                    break;
                case 3:
                    for(int i=0;i<sortedStudents.size()-1;i++){
                        for(int j=i+1;j<sortedStudents.size();j++){
                            if(sortedStudents.get(i).getScore_3()<sortedStudents.get(j).getScore_3()){
                                Student student1=sortedStudents.get(i);
                                sortedStudents.set(i,sortedStudents.get(j));
                                sortedStudents.set(j,student1);
                            }
                        }
                    }
                    break;
                case 4:
                    for(int i=0;i<sortedStudents.size()-1;i++){
                        for(int j=i+1;j<sortedStudents.size();j++){
                            if(sortedStudents.get(i).getScore_4()<sortedStudents.get(j).getScore_4()){
                                Student student1=sortedStudents.get(i);
                                sortedStudents.set(i,sortedStudents.get(j));
                                sortedStudents.set(j,student1);
                            }
                        }
                    }
                    break;
                case 5:
                    for(int i=0;i<sortedStudents.size()-1;i++){
                        for(int j=i+1;j<sortedStudents.size();j++){
                            if(sortedStudents.get(i).getScore_5()<sortedStudents.get(j).getScore_5()){
                                Student student1=sortedStudents.get(i);
                                sortedStudents.set(i,sortedStudents.get(j));
                                sortedStudents.set(j,student1);
                            }
                        }
                    }
                    break;
                case 6:
                    for(int i=0;i<sortedStudents.size()-1;i++){
                        for(int j=i+1;j<sortedStudents.size();j++){
                            if(sortedStudents.get(i).getScore_6()<sortedStudents.get(j).getScore_6()){
                                Student student1=sortedStudents.get(i);
                                sortedStudents.set(i,sortedStudents.get(j));
                                sortedStudents.set(j,student1);
                            }
                        }
                    }
                    break;
                    default:
            }
        }
    }
    public ArrayList<Student> getSortedStudents(){
        return sortedStudents;
    }
    public int getRank(String Stuid,int i){//按各科排名
        int rank;
        SortStudent(i);
        for(rank=0;rank<sortedStudents.size();rank++){
            if(Stuid.equals(sortedStudents.get(rank).getStuid())){
                break;
            }
        }
        return rank+1;
    }
    public int getAverage(int i){//获得各科平均分,0为全部
        int average=0;
        int all=0;
        switch (i){
            case 0:
                for (int j=0;j<sortedStudents.size();j++){
                    all+=sortedStudents.get(j).getScore_1();
                    all+=sortedStudents.get(j).getScore_2();
                    all+=sortedStudents.get(j).getScore_3();
                    all+=sortedStudents.get(j).getScore_4();
                    all+=sortedStudents.get(j).getScore_5();
                    all+=sortedStudents.get(j).getScore_6();
                }
                average=all/sortedStudents.size()/6;
                break;
            case 1:
                for (int j=0;j<sortedStudents.size();j++){
                    all+=sortedStudents.get(j).getScore_1();
                }
                average=all/sortedStudents.size();
                break;
            case 2:
                for (int j=0;j<sortedStudents.size();j++){
                    all+=sortedStudents.get(j).getScore_2();
                }
                average=all/sortedStudents.size();
                break;
            case 3:
                for (int j=0;j<sortedStudents.size();j++){
                    all+=sortedStudents.get(j).getScore_3();
                }
                average=all/sortedStudents.size();
                break;
            case 4:
                for (int j=0;j<sortedStudents.size();j++){
                    all+=sortedStudents.get(j).getScore_4();
                }
                average=all/sortedStudents.size();
                break;
            case 5:
                for (int j=0;j<sortedStudents.size();j++){
                    all+=sortedStudents.get(j).getScore_5();
                }
                average=all/sortedStudents.size();
                break;
            case 6:
                for (int j=0;j<sortedStudents.size();j++){
                    all+=sortedStudents.get(j).getScore_6();
                }
                average=all/sortedStudents.size();
                break;
        }
        return average;
    }
    public int addall(Student student){
        return student.getScore_1()+student.getScore_2()+student.getScore_3()+student.getScore_4()+student.getScore_5()+student.getScore_6();
    }
    public String getPrank(String Stuid,int i){
        BigDecimal b = new BigDecimal( (getRank(Stuid,i)/(double)sortedStudents.size())*100);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"%";
    }

    public double getDoublePrank(String Stuid,int i){
        BigDecimal b = new BigDecimal((1-(getRank(Stuid,i)/(double)sortedStudents.size()))*100);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public int[] getGrade(int sub){//获取1-6个学科分别的等级
        int[] grades=new int[4];
        for(int i=0;i<sortedStudents.size();i++){
            int score=StudentUtil.getSubjectScore(sortedStudents.get(i),sub);
            int[] spicalScore={60,70,85};
            if(sub==0){
                spicalScore[0]=360;
                spicalScore[1]=420;
                spicalScore[2]=510;
            }
            if(score<spicalScore[0]){
                grades[0]++;
            }else if(score<spicalScore[1]){
                grades[1]++;
            }else if(score<spicalScore[2]){
                grades[2]++;
            }else
                grades[3]++;
        }
        for(int i=0;i<4;i++){
            double number=((double)grades[i]/(double)sortedStudents.size()*100);
            BigDecimal bd=new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
            grades[i]=Integer.parseInt(bd.toString());
        }
        return grades;
    }
}
