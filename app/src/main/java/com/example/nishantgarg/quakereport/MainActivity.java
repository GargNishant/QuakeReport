package com.example.nishantgarg.quakereport;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Quakes> quakes=JsonHelper.extractEarthQuakes();

        QuakeArrayAdapter adapter=new QuakeArrayAdapter(MainActivity.this,0,quakes);
        ListView ParentView=(ListView)findViewById(R.id.parentView);
        ParentView.setAdapter(adapter);
    }
}
