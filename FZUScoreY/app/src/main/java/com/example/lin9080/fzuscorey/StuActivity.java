package com.example.lin9080.fzuscorey;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class StuActivity extends AppCompatActivity {
    private String acco;
    private Student student;
    private BottomNavigationView bnv;
    private general_fragment fragment1;
    private analysis_fragment fragment2;
    private curve_fragment fragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu);
        Intent intent = getIntent();
        acco = intent.getStringExtra("acco");
        fragment1 = new general_fragment();
        fragment2 = new analysis_fragment();
        fragment3 = new curve_fragment();
        bnv = (BottomNavigationView) findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.general:
                        replaceFragment(fragment1);
                        break;
                    case R.id.analysis:
                        replaceFragment(fragment2);
                        break;
                    case R.id.curve:
                        replaceFragment(fragment3);
                        break;
                }
                return true;
            }
        });
        bnv.setSelectedItemId(R.id.general);
    }

    @Override
    protected void onResume() {
        bnv.setSelectedItemId(bnv.getSelectedItemId());
        super.onResume();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();
    }

    public String getAcco(){
        return this.acco;
    }
}