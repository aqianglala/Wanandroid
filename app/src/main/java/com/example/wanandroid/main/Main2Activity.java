package com.example.wanandroid.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
    }

}
