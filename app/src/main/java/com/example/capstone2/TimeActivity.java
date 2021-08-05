package com.example.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;

public class TimeActivity extends AppCompatActivity {

    ImageView iv;
    //private SQLiteDatabaseHandler db;
    private LineView lineView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        iv = (ImageView)findViewById(R.id.imageView10);
        iv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        lineView = (LineView) findViewById(R.id.line_chart);
        ArrayList<String> week = new ArrayList<String>();
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);
        lineView.setColorArray(new int[]{
                Color.parseColor("#BB0044")
        });
        lineView.setBottomTextList(week);
        //lineView.setDataList(data);

    }
}