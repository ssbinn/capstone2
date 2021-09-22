package com.example.capstone2;

import static com.example.capstone2.util.DrawerUtil.drawerUtil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UsefulActivity extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful);

        View drawerView = findViewById(R.id.drawer_view);
        drawerUtil.setDrawerListener(drawerView, this);


    }
}