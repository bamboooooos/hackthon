package com.example.lin9080.fzuscorey;

import org.litepal.crud.LitePalSupport;

public class Student extends LitePalSupport {
    private int term;
    private String Stuid,password,stu_name;
    private String name_1,name_2,name_3,name_4,name_5,name_6;
    private int score_1,score_2,score_3,score_4,score_5,score_6;
    private int rank,average;
    private String percentage;

    public Student() {

    }

    public Student(int term, String Stuid, String password, String stu_name,
                   String name_1, String name_2, String name_3, String name_4,
                   String name_5, String name_6, int score_1, int score_2,
                   int score_3, int score_4, int score_5, int score_6, int rank,
                   String percentage, int average) {
        this.term = term;
        this.Stuid = Stuid;
        this.password = password;
        this.stu_name = stu_name;
        this.name_1 = name_1;
        this.name_2 = name_2;
        this.name_3 = name_3;
        this.name_4 = name_4;
        this.name_5 = name_5;
        this.name_6 = name_6;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score_3 = score_3;
        this.score_4 = score_4;
        this.score_5 = score_5;
        this.score_6 = score_6;
        this.rank = rank;
        this.percentage = percentage;
        this.average = average;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getStuid() {
        return Stuid;
    }

    public void setStuid(String stuid) {
        Stuid = stuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getName_1() {
        return name_1;
    }

    public void setName_1(String name_1) {
        this.name_1 = name_1;
    }

    public String getName_2() {
        return name_2;
    }

    public void setName_2(String name_2) {
        this.name_2 = name_2;
    }

    public String getName_3() {
        return name_3;
    }

    public void setName_3(String name_3) {
        this.name_3 = name_3;
    }

    public String getName_4() {
        return name_4;
    }

    public void setName_4(String name_4) {
        this.name_4 = name_4;
    }

    public String getName_5() {
        return name_5;
    }

    public void setName_5(String name_5) {
        this.name_5 = name_5;
    }

    public String getName_6() {
        return name_6;
    }

    public void setName_6(String name_6) {
        this.name_6 = name_6;
    }

    public int getScore_1() {
        return score_1;
    }

    public void setScore_1(int score_1) {
        this.score_1 = score_1;
    }

    public int getScore_2() {
        return score_2;
    }

    public void setScore_2(int score_2) {
        this.score_2 = score_2;
    }

    public int getScore_3() {
        return score_3;
    }

    public void setScore_3(int score_3) {
        this.score_3 = score_3;
    }

    public int getScore_4() {
        return score_4;
    }

    public void setScore_4(int score_4) {
        this.score_4 = score_4;
    }

    public int getScore_5() {
        return score_5;
    }

    public void setScore_5(int score_5) {
        this.score_5 = score_5;
    }

    public int getScore_6() {
        return score_6;
    }

    public void setScore_6(int score_6) {
        this.score_6 = score_6;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
