package com.example.lin9080.fzuscorey;

public class recycler_item {
    private String subject,score,average,prank,rank;
    public recycler_item(String subject,String score,String average,String rank,String prank){
        this.score = score;
        this.subject = subject;
        this.average = average;
        this.rank = rank;
        this.prank = prank;
    }

    public String getAverage() {
        return average;
    }

    public String getScore() {
        return score;
    }

    public String getSubject() {
        return subject;
    }

    public String getPrank() {
        return prank;
    }

    public String getRank() {
        return rank;
    }
}