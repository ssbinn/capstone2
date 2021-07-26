package com.example.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button developer_info_btn = (Button) findViewById(R.id.button4);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), WeekActivity.class);
                startActivity(intent);
            }
        });

        Button developer_info_btn2 = (Button) findViewById(R.id.button7);
        developer_info_btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), TimeActivity.class);
                startActivity(intent);
            }
        });

        Button developer_info_btn3 = (Button) findViewById(R.id.button8);
        developer_info_btn3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UsefulActivity.class);
                startActivity(intent);
            }
        });
    }

}