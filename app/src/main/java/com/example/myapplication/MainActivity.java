package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backBtnTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //search_btn step_btn ai_btn allergy_btn
        ImageButton search_btn = (ImageButton) findViewById(R.id.search_btn);
        ImageButton step_btn = (ImageButton) findViewById(R.id.step_btn);
        ImageButton ai_btn = (ImageButton) findViewById(R.id.ai_btn);
        ImageButton allergy_btn = (ImageButton) findViewById(R.id.allergy_btn);
        ImageButton nutrient_btn = (ImageButton) findViewById(R.id.nutrient_btn);

        //검색 버튼 누르면 검색창으로 이동
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), foodsearch.class);
                startActivity(intent);
                finish();
            }
        });

        step_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), steps.class);
                startActivity(intent);
                finish();
            }
        });

        ai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ai.class);
                startActivity(intent);
                finish();
            }
        });

        allergy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), allergy.class);

                startActivity(intent);
                finish();
            }
        });

        nutrient_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), nutrient.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }

    }



}