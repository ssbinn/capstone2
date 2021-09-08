package com.example.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class WeekActivity extends AppCompatActivity {

    ImageView iv;

    ArrayList<ArrayList<Week>> weekList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        initView();
        getData();
    }

    private void initView(){

        iv = (ImageView)findViewById(R.id.imageView10);
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
        weekList = new ArrayList<>();
        getDayData(getString(R.string.monday));
        getDayData(getString(R.string.tuesday));
        getDayData(getString(R.string.wednesday));
        getDayData(getString(R.string.thursday));
        getDayData(getString(R.string.friday));
        getDayData(getString(R.string.saturday));
        getDayData(getString(R.string.sunday));
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


        for (int i=0; i<weekList.size(); i++)
        {
            for(Week week : weekList.get(i))
            {
                if(week.getStart_line() == startLine && week.getStart_station().equals(startStation) &&
                week.getStop_line() == stopLine && week.getStop_station().equals(stopStation)){

                    Log.d("선택된 week", "i : " + i +", pb : " + week.getPb() );
                    pbList.add(new Entry(i+1, week.getPb()));

                    if(logoClick)
                    {
                        countList.add(new Entry(i+1, week.getCount()));
                    }
                }
            }
        }


        Log.d("포인트 체크", "size : "+ weekList.size() +
                ", startPoint : "+ startLine+", " + startStation + ", endPoint : " + stopLine + ", " + stopStation);

        drawLineChart(lineChart, pbList, countList, logoClick);

    }

    private void drawLineChart(LineChart lineChart, ArrayList<Entry> pbList,  ArrayList<Entry> countList, boolean logoClick){


        String[] labels = {"","월","화","수","목","금","토","일",""};

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAxisMinimum(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        LineDataSet pbSet = new LineDataSet(pbList, "pb");
        LineDataSet countSet = new LineDataSet(countList, "count");
        pbSet.setColor(R.color.black);

        pbSet.setCircleColor(R.color.black);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(pbSet);
        if(logoClick) dataSets.add(countSet);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);
        lineChart.invalidate();
    }





    private void getDayData(String fileName){
        try{

            InputStream is = getResources().getAssets().open(fileName + ".xls");
            Workbook wb = Workbook.getWorkbook(is);


            ArrayList<Week> list = new ArrayList<>();


            if(wb!=null)
            {
                Sheet sheet = wb.getSheet(0);

                if(sheet!=null){
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 1;
                    int rowTotal = sheet.getColumn(colTotal-1).length;


                    Log.d( "내용","총 줄 : " + (rowTotal-1));
                    for(int row = rowIndexStart; row<rowTotal;row++){

                        Week week = new Week(sheet.getCell(0, row).getContents(),
                                Integer.parseInt(sheet.getCell(1, row).getContents()),
                                sheet.getCell(2, row).getContents(),
                                Integer.parseInt(sheet.getCell(3, row).getContents()),
                                sheet.getCell(4, row).getContents(),
                                Integer.parseInt(sheet.getCell(5, row).getContents()),
                                Integer.parseInt(sheet.getCell(6, row).getContents()));

                        Log.d("내용" ,"week : " + week.getDate()+", "+week.getStart_line()+", "
                        + week.getStart_station()+", " + week.getStop_line()+", " +week.getStop_station()
                        +", " + week.getCount() +", " +week.getPb());

                        list.add(week);
                    }

                    weekList.add(list); //요일별 데이터 추가
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

}