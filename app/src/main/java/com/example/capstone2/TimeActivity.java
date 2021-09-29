package com.example.capstone2;

import static com.example.capstone2.util.DrawerUtil.drawerUtil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.capstone2.util.ParseData;
import com.example.capstone2.util.TrafficData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity {

    ArrayList<ArrayList<TrafficData>> timeList;

    ParseData parseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        getData();
        initView();


    }


    private void initView(){

        View drawerView = findViewById(R.id.drawer_view);
        drawerUtil.setDrawerListener(drawerView, this);

        ImageView iv = findViewById(R.id.imageView10);
        iv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });


        LineChart lineChart = (LineChart) findViewById(R.id.line_chart);
        lineChart.getDescription().setEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDrawGridBackground(true);


        Spinner spStart = findViewById(R.id.sp_start);
        Spinner spEnd = findViewById(R.id.sp_end);


        Button btnCheck = findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("클릭된아이템" , "start : " +spStart.getSelectedItem() + ", end : " +spEnd.getSelectedItem());

                setLineChart(lineChart, spStart.getSelectedItem().toString(), spEnd.getSelectedItem().toString(), false);
            }
        });

        ImageView ivLogo = findViewById(R.id.iv_logo);

        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineChart(lineChart, spStart.getSelectedItem().toString(), spEnd.getSelectedItem().toString(), true);
            }
        });


    }

    private void getData(){

        parseData = new ParseData(this);
        timeList = parseData.getTimeList();
    }


    private void setLineChart(LineChart lineChart, String startPosition, String stopPosition, boolean logoClick){


        int startLine=0;
        int stopLine=0;
        String startStation= "";
        String stopStation="";

        switch(startPosition)
        {
            case "2호선 강남" :{
                startLine = 2;
                startStation = getString(R.string.gangnam);
                break;
            }
            case "1호선 역곡" :{
                startLine = 1;
                startStation = getString(R.string.yeokgok);
                break;
            }
            case "3호선 오금" :{
                startLine = 3;
                startStation = getString(R.string.ogeum);
                break;
            }
            case "3호선 정발산" :{
                startLine = 3;
                startStation = getString(R.string.jeongbalsan);
                break;
            }
            case "2호선 합정" :{
                startLine = 2;
                startStation = getString(R.string.hapjeong);
                break;
            }
        }



        switch(stopPosition)
        {
            case "2호선 강남" :{
                stopLine = 2;
                stopStation = getString(R.string.gangnam);
                break;
            }
            case "1호선 역곡" :{
                stopLine = 1;
                stopStation = getString(R.string.yeokgok);
                break;
            }
            case "3호선 오금" :{
                stopLine = 3;
                stopStation = getString(R.string.ogeum);
                break;
            }
            case "3호선 정발산" :{
                stopLine = 3;
                stopStation = getString(R.string.jeongbalsan);
                break;
            }
            case "2호선 합정" :{
                stopLine = 2;
                stopStation = getString(R.string.hapjeong);
                break;
            }
        }

        ArrayList<Entry> pbList = new ArrayList<>();
        ArrayList<Entry> countList = new ArrayList<>();;
        pbList.add(new Entry(0,0));
        countList.add(new Entry(0,0));


        for (int i=0; i<timeList.size(); i++)
        {
            for(TrafficData trafficData : timeList.get(i))
            {
                if(trafficData.getStart_line() == startLine && trafficData.getStart_station().equals(startStation) &&
                        trafficData.getStop_line() == stopLine && trafficData.getStop_station().equals(stopStation)){

                    Log.d("선택된 data", "i : " + i +", pb : " + trafficData.getPb() );
                    pbList.add(new Entry(i+1, trafficData.getPb()));

                    if(logoClick)
                    {
                        countList.add(new Entry(i+1, trafficData.getCount()));
                    }
                }
            }
        }


        Log.d("포인트 체크", "size : "+ timeList.size() +
                ", startPoint : "+ startLine+", " + startStation + ", endPoint : " + stopLine + ", " + stopStation);

        parseData.drawLineChart(lineChart, pbList, countList, logoClick, ParseData.timeLabels);

    }





}