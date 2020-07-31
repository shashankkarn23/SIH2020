package com.shashank.admin.ncrb_new_admin;

public class Beat {
    public String area;
    public String date;
    public String time;

    public Beat(){

    }

    public Beat(String area, String date, String time){
        this.area=area;
        this.date=date;
        this.time=time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
