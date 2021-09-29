package com.example.capstone2.util;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.capstone2.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ParseData {

    Context mContext;

    public static String[] timeLabels = {"","6~9","9~12","12~15","15~18","18~21","21~24"};

    public static String[] weekLabels = {"","월","화","수","목","금","토","일",""};


    public ParseData(Context mContext) {
        this.mContext = mContext;
    }


    public ArrayList<ArrayList<TrafficData>> getWeekList(){
        ArrayList<ArrayList<TrafficData>> weekList = new ArrayList<>();
        getDayData(weekList, mContext.getString(R.string.monday));
        getDayData(weekList, mContext.getString(R.string.tuesday));
        getDayData(weekList, mContext.getString(R.string.wednesday));
        getDayData(weekList, mContext.getString(R.string.thursday));
        getDayData(weekList, mContext.getString(R.string.friday));
        getDayData(weekList, mContext.getString(R.string.saturday));
        getDayData(weekList, mContext.getString(R.string.sunday));

        return weekList;
    }

    public ArrayList<ArrayList<TrafficData>> getTimeList(){
        ArrayList<ArrayList<TrafficData>> timeList = new ArrayList<>();
        timeList = new ArrayList<>();
        getTimeData(timeList, mContext.getString(R.string.six_to_nine));
        getTimeData(timeList, mContext.getString(R.string.nine_to_twelve));
        getTimeData(timeList, mContext.getString(R.string.twelve_to_fifteen));
        getTimeData(timeList, mContext.getString(R.string.fifteen_to_eighteen));
        getTimeData(timeList, mContext.getString(R.string.eighteen_to_twenty_one));
        getTimeData(timeList, mContext.getString(R.string.twenty_one_to_twenty_four));

        return timeList;
    }

    private void getDayData(ArrayList<ArrayList<TrafficData>> weekList, String fileName){
        try{

            InputStream is = mContext.getResources().getAssets().open(fileName + ".xls");
            Workbook wb = Workbook.getWorkbook(is);


            ArrayList<TrafficData> list = new ArrayList<>();


            if(wb!=null)
            {
                Sheet sheet = wb.getSheet(0);

                if(sheet!=null){
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 1;
                    int rowTotal = sheet.getColumn(colTotal-1).length;


//                    Log.d( "내용","총 줄 : " + (rowTotal-1));
                    for(int row = rowIndexStart; row<rowTotal;row++){

                        TrafficData trafficData = new TrafficData(sheet.getCell(0, row).getContents(),
                                Integer.parseInt(sheet.getCell(1, row).getContents()),
                                sheet.getCell(2, row).getContents(),
                                Integer.parseInt(sheet.getCell(3, row).getContents()),
                                sheet.getCell(4, row).getContents(),
                                Integer.parseInt(sheet.getCell(5, row).getContents()),
                                Integer.parseInt(sheet.getCell(6, row).getContents()));
//
//                        Log.d("내용" ,"week : " + trafficData.getDate()+", "+ trafficData.getStart_line()+", "
//                                + trafficData.getStart_station()+", " + trafficData.getStop_line()+", " + trafficData.getStop_station()
//                                +", " + trafficData.getCount() +", " + trafficData.getPb());

                        list.add(trafficData);
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



    private void getTimeData(ArrayList<ArrayList<TrafficData>> timeList, String fileName){
        try{

            InputStream is = mContext.getResources().getAssets().open(fileName + ".xls");

            Workbook wb = Workbook.getWorkbook(is);


            ArrayList<TrafficData> list = new ArrayList<>();


            if(wb!=null)
            {
                Sheet sheet = wb.getSheet(0);

                if(sheet!=null){
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 1;
                    int rowTotal = sheet.getColumn(colTotal-1).length;


//                    Log.d( "내용","총 줄 : " + (rowTotal-1));
                    for(int row = rowIndexStart; row<rowTotal;row++){

                        TrafficData trafficData = new TrafficData(sheet.getCell(0, row).getContents(),
                                Integer.parseInt(sheet.getCell(1, row).getContents()),
                                sheet.getCell(2, row).getContents(),
                                Integer.parseInt(sheet.getCell(3, row).getContents()),
                                sheet.getCell(4, row).getContents(),
                                Integer.parseInt(sheet.getCell(5, row).getContents()),
                                Integer.parseInt(sheet.getCell(6, row).getContents()));
//
//                        Log.d("내용" ,"week : " + trafficData.getDate()+", "+ trafficData.getStart_line()+", "
//                                + trafficData.getStart_station()+", " + trafficData.getStop_line()+", " + trafficData.getStop_station()
//                                +", " + trafficData.getCount() +", " + trafficData.getPb());

                        list.add(trafficData);
                    }

                    timeList.add(list); //요일별 데이터 추가
                }
            }

        } catch (Exception e) {
            Log.d("에러", "fileName : "+ fileName);
            e.printStackTrace();
        }
    }


    public void drawLineChart(LineChart lineChart, ArrayList<Entry> pbList, ArrayList<Entry> countList, boolean logoClick, String[] labels){

        lineChart.setExtraBottomOffset(20);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAxisMinimum(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        LineDataSet pbSet = new LineDataSet(pbList, "pb값");
        LineDataSet countSet = new LineDataSet(countList, "원본값");
        pbSet.setLineWidth(2f);
        pbSet.setColor(ContextCompat.getColor(mContext,R.color.black));
        pbSet.setCircleColor(ContextCompat.getColor(mContext,R.color.black));
        countSet.setLineWidth(2f);
        countSet.setColor(ContextCompat.getColor(mContext,R.color.color_red));
        countSet.setCircleColor(ContextCompat.getColor(mContext,R.color.color_red));


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(pbSet);
        if(logoClick) dataSets.add(countSet);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);
        lineChart.invalidate();
    }


    public void drawPbLineChart(LineChart lineChart, Map<String,ArrayList<Entry>> pbMap,  String[] labels){

        lineChart.setExtraBottomOffset(20);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAxisMinimum(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();


        int cnt =0;
        for(String key : pbMap.keySet())
        {

            cnt++;
//            LineDataSet pbSet = new LineDataSet(pbMap.get(key), key); // key 역정보 String

            LineDataSet pbSet = new LineDataSet(pbMap.get(key), "역정보" + cnt);
            pbSet.setLineWidth(2f);

            int color = R.color.color_red;
            switch (cnt)
            {
                case 1:{
                    color = R.color.color_red;
                    break;
                }
                case 2:{
                    color = R.color.color_green;
                    break;
                }
                case 3:{
                    color = R.color.color_blue;
                    break;
                }
            }

            pbSet.setColor(ContextCompat.getColor(mContext,color));
            pbSet.setCircleColor(ContextCompat.getColor(mContext,color));

            dataSets.add(pbSet);
        }


        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
    }





}
