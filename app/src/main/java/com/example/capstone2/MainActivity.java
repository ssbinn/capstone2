package com.example.capstone2;

import static com.example.capstone2.util.DrawerUtil.drawerUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.capstone2.ui.dialog.CustomDialog;
import com.example.capstone2.util.StationData;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SdCardPath")
    public static final String ROOT_DIR = "/data/data/com.example.capstone2/databases/";

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mContext = this;
        initView();


    }

    @Override
    protected void onResume() {
        super.onResume();


        if(StationData.startStation==null && StationData.startLine== null
        &&StationData.goalStation==null && StationData.goalLine==null)
        {
            TextView tvSearchStation = findViewById(R.id.tv_serach_station);
            tvSearchStation.setText("");
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView()
    {
        View drawerView = findViewById(R.id.drawer_view);

        drawerUtil.setDrawerListener(drawerView, this);


        ImageView ivSearch = findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                startActivity(intent);
            }
        });

        SubsamplingScaleImageView ssvSubwayMap = findViewById(R.id.ssv_subway_map);
        GestureDetector gestureDetector = getGestureDetector(ssvSubwayMap);

        ssvSubwayMap.setZoomEnabled(true);
        ssvSubwayMap.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return false;
        });


    }

    private GestureDetector getGestureDetector(SubsamplingScaleImageView subsamplingScaleImageView)
    {

        GestureDetector gestureDetector;


        SubwayDatabaseHelper myDbHelper = new SubwayDatabaseHelper(this); // Reading SQLite database.
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        finally {
            myDbHelper.openDataBase();

            Cursor c = myDbHelper.query("coordinate", null, null, null, null, null, null); // SQLDataRead
            gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // gesture 디텍팅으로 지하철 위치 읽기
                @Override
                public boolean onSingleTapUp(MotionEvent event) {

                    Log.d("픽셀좌표 테스트", "onSingleTapUp");
                    if(subsamplingScaleImageView.isReady())
                    {
                        PointF sCoord = subsamplingScaleImageView.viewToSourceCoord(event.getX(), event.getY());
                        int x_cor = (int) sCoord.x;
                        int y_cor = (int) sCoord.y;

                        Log.d("픽셀좌표 테스트", "x_cor : " + x_cor +", y_cor : " +y_cor + ", event.x : " +event.getX() + ", event.getY" + event.getY() );

                        // Loop for finding the station.
                        if (c.moveToFirst()) {
                            do {

                                if ((x_cor > c.getInt(2)) && (x_cor < c.getInt(4)) && (y_cor > c.getInt(3)) && (y_cor < c.getInt(5))) {
                                    String targetStation = c.getString(1);
                                    Log.d("픽셀좌표 테스트", "targetStation : " + targetStation);

                                    CustomDialog customDialog = new CustomDialog(mContext, event.getX(), event.getY(),  targetStation);
                                    customDialog.create();

                                    customDialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {


                                            TextView tvSearchStation = findViewById(R.id.tv_serach_station);
                                            StringBuilder sb = new StringBuilder();

                                            if(StationData.startStation!=null && StationData.startLine!= null){

                                                sb.append("출발역 : ");
                                                sb.append(StationData.startStation);
                                                sb.append(" ");
                                                sb.append(StationData.startLine);
                                                sb.append("호선");
                                                sb.append("\n");
                                            }

                                            if(StationData.goalStation!=null && StationData.goalLine!= null){

                                                sb.append("도착역 : ");
                                                sb.append(StationData.goalStation);
                                                sb.append(" ");
                                                sb.append(StationData.goalLine);
                                                sb.append("호선");
                                            }


                                            tvSearchStation.setText(sb.toString());


                                        }
                                    });

                                } // send Station Name (column 1)
                            } while (c.moveToNext());
                        }

                    }


                    return super.onSingleTapUp(event);
                }
            });

        }

        return gestureDetector;



    }




}



