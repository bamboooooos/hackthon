package com.example.lin9080.fzuscorey;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MonActivity extends AppCompatActivity {
    private int Term=1;
    private BottomNavigationView bnv;
    private general_fragment1 fragment1;
    private analysis_fragment1 fragment2;
    private curve_fragment1 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);
        fragment1 = new general_fragment1();
        fragment2 = new analysis_fragment1();
        fragment3 = new curve_fragment1();
        bnv = (BottomNavigationView) findViewById(R.id.bnv1);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.general1:
                        replaceFragment(fragment1);
                        break;
                    case R.id.analysis1:
                        replaceFragment(fragment2);
                        break;
                    case R.id.curve1:
                        replaceFragment(fragment3);
                        break;
                }
                return true;
            }
        });
        bnv.setSelectedItemId(R.id.general1);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment1,fragment);
        fragmentTransaction.commit();
    }

    public int getTerm(){
        return this.Term;
    }
}
