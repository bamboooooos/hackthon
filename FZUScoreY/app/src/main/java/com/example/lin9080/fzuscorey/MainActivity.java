package com.example.lin9080.fzuscorey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String[] strings = {"b1","b2","a1","a2"};
    private ArrayList<Student> students=new ArrayList<>();
    private int flag=0;//0为学生，1为班长
    private static Button login,StuLog,MonLog;
    private static EditText Account,Password;
    private final String mon_api="http://192.168.43.98:5000/api/monitor";
    private final String stu_api="http://192.168.43.98:5000/api/all";
    private int isFindData=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 在这里发送请求获取数据
        LayoutInit();
        //以上为界面的初始化
        LitePal.getDatabase();//数据库初始化
        SharedPreferences preferences=getPreferences(MODE_PRIVATE);
        isFindData=preferences.getInt("isFindData",1);
        if(isFindData==1){
            sendRequestWithHttpURLConnection();
        }else{
            students.addAll(LitePal.findAll(Student.class));
        }
        /*((Button)findViewById(R.id.testinit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestInit();//测试方法
                testinit();
                students.addAll(LitePal.findAll(Student.class));
            }
        });
        ((Button)findViewById(R.id.testclear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Student.class);
                students.addAll(LitePal.findAll(Student.class));
            }
        });*/
    }
    private void LayoutInit(){
        login=(Button)findViewById(R.id.login);
        StuLog=(Button)findViewById(R.id.stu_log);
        MonLog=(Button)findViewById(R.id.mon_log);
        Account=(EditText)findViewById(R.id.account);
        Password=(EditText)findViewById(R.id.password);
        ButtonChange(flag);
        ButtonInit();
    }
    private void ButtonInit(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String acc=Account.getText().toString();
                String pas=Password.getText().toString();
                if(flag==0){
                    int i;
                    for (i=0;i<students.size();i++){
                        Student student=students.get(i);
                        if((student.getStuid()).equals(acc)){
                            if(student.getPassword().equals(pas)){
                                Intent intent=new Intent(MainActivity.this,StuActivity.class);
                                intent.putExtra("acco",acc);
                                startActivity(intent);
                                break;
                            }
                        }
                    }
                    if(i>=students.size()){
                        Toast.makeText(MainActivity.this,"账号不存在或密码错误，登录失败",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    SharedPreferences preferences=getPreferences(MODE_PRIVATE);
                    if((acc.equals(preferences.getString("mon_id","wrong")))&&(pas.equals(preferences.getString("mon_pas","wrong")))) {
                        Intent intent1 = new Intent(MainActivity.this, MonActivity.class);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(MainActivity.this,"账号不存在或密码错误，登录失败",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        StuLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                ButtonChange(flag);
            }
        });
        MonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                ButtonChange(flag);
            }
        });
    }
    private void ButtonChange(int flag){//按键颜色的改变
        if(flag==0){
            StuLog.setTextColor(Color.BLUE);
            MonLog.setTextColor(Color.BLACK);
        }else{
            MonLog.setTextColor(Color.BLUE);
            StuLog.setTextColor(Color.BLACK);
        }
    }
    /*private void testinit(){
        for(int term=1;term<5;term++){
            Student student1 = new Student();
            student1.setTerm(term);
            student1.setStuid("");
            student1.setPassword("");
            student1.setName_1("大物"+term);
            student1.setScore_1(getRan());
            student1.setName_2("高数"+term);
            student1.setScore_2(getRan());
            student1.setName_3("离散"+term);
            student1.setScore_3(getRan());
            student1.setName_4("算法"+term);
            student1.setScore_4(getRan());
            student1.setName_5("毛概"+term);
            student1.setScore_5(getRan());
            student1.setName_6("英语"+term);
            student1.setScore_6(getRan());
            student1.setRank(40);
            student1.setPercentage("40%");
            student1.setAverage(70);
            student1.save();
        }
    }
    private void TestInit(){
        for(int term=1;term<5;term++)
        for(int i=0;i<20;i++) {
            Student student=new Student();
            student.setTerm(term);
            student.setStuid(getRan()+"");
            student.setPassword(getRan()+"");
            student.setName_1("大物"+term);
            student.setScore_1(getRan());
            student.setName_2("高数"+term);
            student.setScore_2(getRan());
            student.setName_3("离散"+term);
            student.setScore_3(getRan());
            student.setName_4("算法"+term);
            student.setScore_4(getRan());
            student.setName_5("毛概"+term);
            student.setScore_5(getRan());
            student.setName_6("英语"+term);
            student.setScore_6(getRan());
            student.setRank(40);
            student.setPercentage("40%");
            student.setAverage(70);
            student.save();
        }
    }
    private int getRan(){
        return (int)(Math.random()*60+41);
    }*/
    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(mon_api).build();
                    Response response = client.newCall(request).execute();
                    String data = response.body().string();
                    setMonitor(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LitePal.deleteAll(Student.class);
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(stu_api).build();
                    Response response = client.newCall(request).execute();
                    String data = response.body().string();
                    getJson(data);
                    students.addAll(LitePal.findAll(Student.class));//获取数据库中所有学生对象
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        SharedPreferences preferences=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("isFindData",1);
        editor.apply();
    }

    private void getJson(final String data){
        Gson gson = new Gson();
        ArrayList<Student> students = gson.fromJson(data, new TypeToken<ArrayList<Student>>(){}.getType());
        for(Student student:students)
        student.save();
    }

    private void setMonitor(String data){
        Gson gson = new Gson();
        Monitor monitor = gson.fromJson(data,Monitor.class);
        SharedPreferences preferences=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("mon_id",monitor.getMon_id());
        editor.putString("mon_pas",monitor.getMon_pas());
        editor.apply();
    }
}
