package com.example.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.capstone2.util.ParseData;
import com.example.capstone2.util.StationData;
import com.example.capstone2.util.TrafficData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.capstone2.util.DrawerUtil.drawerUtil;

public class GraphActivity2 extends AppCompatActivity {

    private ArrayList<ArrayList<TrafficData>> timeList;
    private ArrayList<ArrayList<TrafficData>> weekList;
    private ParseData parseData;

    private EditText etStartLine;
    private EditText etStartStation;
    private EditText etGoalLine;
    private EditText etGoalStation;
    private LineChart lineChart;

    private int searchWeek = 0;
    private int searchTime = 1;
    private int currentSearchType = searchWeek;

    private HashMap<String, ArrayList<Entry>> pbMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph2);

        initView();
        initData();
        ImageView ivSearch = findViewById(R.id.iv_marker_map);
        ivSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView()
    {
        View drawerView = findViewById(R.id.drawer_view);
        drawerUtil.setDrawerListener(drawerView, this);

        Button btnWeek = findViewById(R.id.btn_week);
        Button btnTime = findViewById(R.id.btn_time);

        btnTime.setOnClickListener(onClickListener);
        btnWeek.setOnClickListener(onClickListener);

        ImageView ivGraph = findViewById(R.id.iv_graph);

        ivGraph.setOnClickListener(onClickListener);



        etStartLine = findViewById(R.id.et_start_line);
        etStartStation = findViewById(R.id.et_start_station);
        etGoalLine = findViewById(R.id.et_goal_line);
        etGoalStation = findViewById(R.id.et_goal_station);

        lineChart = (LineChart) findViewById(R.id.line_chart);
        lineChart.getDescription().setEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDrawGridBackground(true);

        dataCheck();

    }

    private void dataCheck(){

        if(StationData.startStation!=null && StationData.startLine!= null)
        {
            etStartLine.setText(StationData.startLine);
            etStartStation.setText(StationData.startStation);
        }

        if(StationData.goalStation!=null && StationData.goalLine!= null)
        {

            etGoalStation.setText(StationData.goalStation);
            etGoalLine.setText(StationData.goalLine);

        }

        StationData.startLine = null;
        StationData.startStation = null;
        StationData.goalLine = null;
        StationData.goalStation = null;

    }


    private void initData(){

        pbMap = new HashMap<>();
        parseData = new ParseData(this);
        timeList = parseData.getTimeList();
        weekList = parseData.getWeekList();

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(etStartLine.getText().toString().isEmpty() || etGoalLine.getText().toString().isEmpty()) return;

            if(v.getId() == R.id.btn_week) {
                pbMap.clear();
                currentSearchType = searchWeek;
                drawLineChart(searchWeek);
            }
            else if (v.getId()== R.id.btn_time){
                pbMap.clear();
                currentSearchType = searchTime;
                drawLineChart(searchTime);
            }
            else if(v.getId()==R.id.iv_graph) { //이때 새로운 라인 추가
                if(pbMap.size()==3) pbMap.clear(); //그래프 3개일 경우 초기화
                drawLineChart(currentSearchType);
            }

        }
    };

    private void drawLineChart(int weekOrTime){


        int startLine = Integer.parseInt(etStartLine.getText().toString());
        String startStation = etStartStation.getText().toString();
        int goalLine = Integer.parseInt(etGoalLine.getText().toString());
        String goalStation = etGoalStation.getText().toString();


        StringBuilder sb = new StringBuilder(); // 역 정보를 담는 StringBuilder
        sb.append(startStation);
        sb.append(startLine);
        sb.append("호선 ");
        sb.append(goalStation);
        sb.append(goalLine);
        sb.append("호선");

        if(pbMap.containsKey(sb.toString()))  return;

        ArrayList<Entry> pbList = new ArrayList<>();
        pbList.add(new Entry(0,0));


        if(weekOrTime == searchWeek)
        {
            for (int i=0; i<weekList.size(); i++)
            {
                for(TrafficData trafficData : weekList.get(i))
                {

                    if(trafficData.getStart_line() == startLine && trafficData.getStart_station().equals(startStation) &&
                            trafficData.getStop_line() == goalLine && trafficData.getStop_station().equals(goalStation)){

                        pbList.add(new Entry(i+1, trafficData.getPb()));

                    }
                }
            }

            if(pbList.size()>1)
            {
                pbMap.put(sb.toString(), pbList);
                parseData.drawPbLineChart(lineChart, pbMap, ParseData.weekLabels);
            }

        }else if (weekOrTime == searchTime)
        {
            for (int i=0; i<timeList.size(); i++)
            {
                for(TrafficData trafficData : timeList.get(i))
                {
                    if(trafficData.getStart_line() == startLine && trafficData.getStart_station().equals(startStation) &&
                            trafficData.getStop_line() == goalLine && trafficData.getStop_station().equals(goalStation)){

                        pbList.add(new Entry(i+1, trafficData.getPb()));


                    }
                }
            }

            if(pbList.size()>1)
            {
                pbMap.put(sb.toString(), pbList);
                parseData.drawPbLineChart(lineChart, pbMap, ParseData.timeLabels);
            }

        }

    }

}

