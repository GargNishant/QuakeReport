package com.example.nishantgarg.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nishant Garg on 22-11-2017.
 */

public class Quakes {

    private double mMagnitude;
    private String mPlace;
    private Date dateObject;

    Quakes(double magnitude,String place, long time){
        mMagnitude=magnitude;
        mPlace=place;
        dateObject = new Date(time);
    }

    double getmMagnitude() {
        return mMagnitude;
    }

    String getmPlace() {
        return mPlace;
    }

    String getmTime() {
        SimpleDateFormat timeFormatter =new SimpleDateFormat("h:mm a");
        return timeFormatter.format(dateObject);
    }

    String getmDate(){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dateObject);
    }
}