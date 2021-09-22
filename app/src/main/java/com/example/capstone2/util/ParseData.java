package com.example.capstone2.util;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.example.capstone2.R;
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
import java.util.Set;

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


    public ArrayList<ArrayList<trafficData>> getWeekList(){
        ArrayList<ArrayList<trafficData>> weekList = new ArrayList<>();
        getDayData(weekList, mContext.getString(R.string.monday));
        getDayData(weekList, mContext.getString(R.string.tuesday));
        getDayData(weekList, mContext.getString(R.string.wednesday));
        getDayData(weekList, mContext.getString(R.string.thursday));
        getDayData(weekList, mContext.getString(R.string.friday));
        getDayData(weekList, mContext.getString(R.string.saturday));
        getDayData(weekList, mContext.getString(R.string.sunday));

        return weekList;
    }

    public ArrayList<ArrayList<trafficData>> getTimeList(){
        ArrayList<ArrayList<trafficData>> timeList = new ArrayList<>();
        timeList = new ArrayList<>();
        getTimeData(timeList, mContext.getString(R.string.six_to_nine));
        getTimeData(timeList, mContext.getString(R.string.nine_to_twelve));
        getTimeData(timeList, mContext.getString(R.string.twelve_to_fifteen));
        getTimeData(timeList, mContext.getString(R.string.fifteen_to_eighteen));
        getTimeData(timeList, mContext.getString(R.string.eighteen_to_twenty_one));
        getTimeData(timeList, mContext.getString(R.string.twenty_one_to_twenty_four));

        return timeList;
    }

    private void getDayData(ArrayList<ArrayList<trafficData>> weekList, String fileName){
        try{

            InputStream is = mContext.getResources().getAssets().open(fileName + ".xls");
            Workbook wb = Workbook.getWorkbook(is);


            ArrayList<trafficData> list = new ArrayList<>();


            if(wb!=null)
            {
                Sheet sheet = wb.getSheet(0);

                if(sheet!=null){
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 1;
                    int rowTotal = sheet.getColumn(colTotal-1).length;


//                    Log.d( "내용","총 줄 : " + (rowTotal-1));
                    for(int row = rowIndexStart; row<rowTotal;row++){

                        trafficData trafficData = new trafficData(sheet.getCell(0, row).getContents(),
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



    private void getTimeData(ArrayList<ArrayList<trafficData>> timeList, String fileName){
        try{

            InputStream is = mContext.getResources().getAssets().open(fileName + ".xls");

            Workbook wb = Workbook.getWorkbook(is);


            ArrayList<trafficData> list = new ArrayList<>();


            if(wb!=null)
            {
                Sheet sheet = wb.getSheet(0);

                if(sheet!=null){
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 1;
                    int rowTotal = sheet.getColumn(colTotal-1).length;


//                    Log.d( "내용","총 줄 : " + (rowTotal-1));
                    for(int row = rowIndexStart; row<rowTotal;row++){

                        trafficData trafficData = new trafficData(sheet.getCell(0, row).getContents(),
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
        pbSet.setColor(R.color.black);
        pbSet.setCircleColor(R.color.black);
        pbSet.setCircleHoleColor(R.color.black);
        countSet.setLineWidth(1f);
        countSet.setColor(Color.parseColor("#e81e25"));
        countSet.setCircleColor(R.color.purple_200);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(pbSet);
        if(logoClick) dataSets.add(countSet);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);
        lineChart.invalidate();
    }





}
