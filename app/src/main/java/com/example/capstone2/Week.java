package com.example.capstone2;

public class Week {


    private String Date;
    private Integer Start_line;
    private String Start_station;
    private Integer Stop_line;
    private String Stop_station;
    private Integer Count;
    private Integer Pb;

    public Week(String date, Integer start_line, String start_station, Integer stop_line, String stop_station, Integer count, Integer pb) {
        Date = date;
        Start_line = start_line;
        Start_station = start_station;
        Stop_line = stop_line;
        Stop_station = stop_station;
        Count = count;
        Pb = pb;
    }


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
