package com.example.capstone2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.database.SQLException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = findViewById(R.id.imageView14);
        iv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UsefulActivity.class);
                startActivity(intent);
            }
        });



 /*       Cursor c = null;
        SubwayDatabaseHelper myDbHelper = new SubwayDatabaseHelper(MainActivity.this); // Reading SQLite database.
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c = myDbHelper.query("subwayData", null, null, null, null, null, null); // SQLDataRead


        final SubsamplingScaleImageView imageView = (SubsamplingScaleImageView) findViewById(R.id.imageView7); // 지하철역 이미지뷰
        //Refer to ID value.
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // gesture 디텍팅으로 지하철 위치 읽기
            @Override
            public boolean onSingleTapUp(MotionEvent ev) {
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(ev.getX(), ev.getY());
                    int x_cor = (int) sCoord.x;
                    int y_cor = (int) sCoord.y;

                    // Loop for finding the station.
                    if (c.moveToFirst()) {
                        do {

                            if ((x_cor > c.getInt(2)) && (x_cor < c.getInt(4)) && (y_cor > c.getInt(3)) && (y_cor < c.getInt(5))) {
                                String targetStation = c.getString(1)); // 유저가 클릭한 지하철역
                            } // send Station Name (column 1)
                        } while (c.moveToNext());
                    }

                    /////////////////////////////////////////////////////////////////////////////

                }
                return super.onSingleTapUp(ev);
            }
        });*/

    }
}



