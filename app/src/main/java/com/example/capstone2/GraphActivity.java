package com.example.capstone2;

import static com.example.capstone2.util.DrawerUtil.drawerUtil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class GraphActivity extends AppCompatActivity {


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


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

        parseData = new ParseData(this);
        timeList = parseData.getTimeList();
        weekList = parseData.getWeekList();

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(etStartLine.getText().toString().isEmpty() || etGoalLine.getText().toString().isEmpty()) return;

            if(v.getId() == R.id.btn_week)
            {
                currentSearchType = searchWeek;
                drawLineChart(false, searchWeek);

            }
            else if (v.getId()== R.id.btn_time){
                currentSearchType = searchTime;
                drawLineChart(false, searchTime);
            }
            else if(v.getId()==R.id.iv_graph) {
                drawLineChart(true, currentSearchType);
            }

        }
    };

    private void drawLineChart(boolean logoClick, int weekOrTime){

        int startLine = Integer.parseInt(etStartLine.getText().toString());
        String startStation = etStartStation.getText().toString();
        int goalLine = Integer.parseInt(etGoalLine.getText().toString());
        String goalStation = etGoalStation.getText().toString();

        ArrayList<Entry> pbList = new ArrayList<>();
        ArrayList<Entry> countList = new ArrayList<>();;
        pbList.add(new Entry(0,0));
        countList.add(new Entry(0,0));


        if(weekOrTime == searchWeek)
        {
            for (int i=0; i<weekList.size(); i++)
            {
                for(TrafficData trafficData : weekList.get(i))
                {

                    if(trafficData.getStart_line() == startLine && trafficData.getStart_station().equals(startStation) &&
                            trafficData.getStop_line() == goalLine && trafficData.getStop_station().equals(goalStation)){

                        pbList.add(new Entry(i+1, trafficData.getPb()));

                        if(logoClick)
                        {
                            countList.add(new Entry(i+1, trafficData.getCount()));
                        }
                    }
                }
            }

            parseData.drawLineChart(lineChart, pbList, countList, logoClick, ParseData.weekLabels);

        }else if (weekOrTime == searchTime)
        {
            for (int i=0; i<timeList.size(); i++)
            {
                for(TrafficData trafficData : timeList.get(i))
                {
                    if(trafficData.getStart_line() == startLine && trafficData.getStart_station().equals(startStation) &&
                            trafficData.getStop_line() == goalLine && trafficData.getStop_station().equals(goalStation)){

                        pbList.add(new Entry(i+1, trafficData.getPb()));

                        if(logoClick)
                        {
                            countList.add(new Entry(i+1, trafficData.getCount()));
                        }
                    }
                }
            }

            parseData.drawLineChart(lineChart, pbList, countList, logoClick, ParseData.timeLabels);
        }

    }

}