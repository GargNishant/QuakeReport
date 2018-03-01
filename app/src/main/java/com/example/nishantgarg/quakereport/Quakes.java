package com.example.nishantgarg.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nishant Garg on 22-11-2017.
 */

public class Quakes {

    private double mMagnitude;
    private String mPlace;
    //private Date mDateObject;
    long mDate;
    private String mUrl;

    Quakes(double magnitude,String place, long time, String url){
        mMagnitude=magnitude;
        mPlace=place;
        mDate = time;
        mUrl=url;
    }

    double getmMagnitude() {
        return mMagnitude;
    }

    String getmPlace() {
        return mPlace;
    }

    /*String getmTime() {
        SimpleDateFormat timeFormatter =new SimpleDateFormat("h:mm a z");
        return timeFormatter.format(mDateObject);
    }

    String getmDate(){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(mDateObject);
    }*/
    String getTime(){
        String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (mDate));
        return date;
    }

    String getmUrl() {
        return mUrl;
    }
}