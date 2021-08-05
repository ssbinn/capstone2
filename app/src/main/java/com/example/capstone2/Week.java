package com.example.capstone2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Week {

    public String Date;
    public Integer Start_line;
    public String Start_station;
    public Integer Stop_line;
    public String Stop_station;
    public Integer Count;
    public Integer Pb;

    public void setDate(String Date){this.Date = Date;}
    public void setStart_line(int Start_line){this.Start_line = Start_line;}
    public void setStart_station(String Start_station){this.Start_station = Start_station;}
    public void setStop_line(int Stop_line){this.Stop_line = Stop_line;}
    public void setStop_station(String Stop_station){this.Stop_station = Stop_station;}
    public void setCount(int Count){this.Count = Count;}
    public void setPb(int Pb){this.Pb = Pb;}

    public String getDate(){ return this.Date;}
    public int getStart_line(){return this.Start_line;}
    public String getStart_station(){return this.Start_station;}
    public int getStop_line(){return this.Stop_line;}
    public String getStop_station(){return this.Stop_station;}
    public int getCount(){return this.Count;}
    public int getPb(){return this.Pb;}

}
