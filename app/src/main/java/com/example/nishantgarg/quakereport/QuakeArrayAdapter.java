package com.example.nishantgarg.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Nishant Garg on 22-11-2017.
 */

public class QuakeArrayAdapter extends ArrayAdapter<Quakes> {


    public QuakeArrayAdapter(Context context, int resource, List<Quakes> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }
        Quakes currentQuake=getItem(position);
        String place=currentQuake.getmPlace();
        String subPlace,mainPlace;

        if(place.contains("of")) {
            String[] placeParts = place.split("(?<=of)");
            subPlace = placeParts[0];
             mainPlace = placeParts[1];
        }
        else{
            subPlace="Near the";
            mainPlace=place;
        }

        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitude = formatter.format(currentQuake.getmMagnitude());

        TextView magView = (TextView)listItemView.findViewById(R.id.magnitude);
        magView.setText(magnitude);

        TextView sub_placeView = (TextView)listItemView.findViewById(R.id.sub_place);
        sub_placeView.setText(subPlace);

        TextView main_placeView = (TextView)listItemView.findViewById(R.id.main_place);
        main_placeView.setText(mainPlace);

        TextView dateView = (TextView)listItemView.findViewById(R.id.date);
        dateView.setText(String.valueOf(currentQuake.getmDate()));

        TextView timeView = (TextView)listItemView.findViewById(R.id.time);
        timeView.setText(String.valueOf(currentQuake.getmTime()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getmMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    /*private int getMagnitudeColor(double v) {
        double mag=v;
        int colorID=00000;
        if(mag>=0 && mag<2){colorID=R.color.magnitude1;}
        else if(mag>=2 && mag<3){colorID= R.color.magnitude2;}
        else if(mag>=3 && mag<4){colorID=R.color.magnitude3;}
        else if(mag>=4 && mag<5){colorID=R.color.magnitude4;}
        else if(mag>=5 && mag<6){colorID=R.color.magnitude5;}
        else if(mag>=6 && mag<7){colorID=R.color.magnitude6;}
        else if(mag>=7 && mag<8){colorID=R.color.magnitude7;}
        else if(mag>=8 && mag<9){colorID=R.color.magnitude8;}
        else if(mag>=9 && mag<10){colorID=R.color.magnitude9;}
        else if(mag>=10){colorID=R.color.magnitude10plus;}
        return ContextCompat.getColor(getContext(), mag);
    }*/
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
