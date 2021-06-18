package com.example.capstone2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;

//public class MainActivity extends AppCompatActivity {
//    Button btnBarChart;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        BarChart barChart = (BarChart) findViewById(R.id.bar_chart);
//
//        btnBarChart = findViewById(R.id.button);
//        btnBarChart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent I = new Intent(MainActivity.this, BarChartMainActivity.class);
//                startActivity(I);
//            }
//        });
//    }
//}

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button developer_info_btn = (Button) findViewById(R.id.button);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), BarChartMainActivity.class);
                startActivity(intent);
            }
        });
    }
}



