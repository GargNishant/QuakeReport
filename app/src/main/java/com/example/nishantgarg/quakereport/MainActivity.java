package com.example.nishantgarg.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    // The URL from which we want to extract data.
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=4&limit=90";

    //We are creating an instance of class so that we can use it inside our onCreate and AsyncTask class.
    // It does not let use like QuakeArrayAdapter.(methods in that class), so its better to use it as a instance variable
    private QuakeArrayAdapter QuakeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView ParentView=(ListView)findViewById(R.id.parentView);
        //We are creating a new adapter using the instance of the class created earlier
        QuakeAdapter=new QuakeArrayAdapter(this, new ArrayList<Quakes>());
        ParentView.setAdapter(QuakeAdapter);

        ParentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positionOfItemClicked, long id) {
                Quakes quake=QuakeAdapter.getItem(positionOfItemClicked);
                String mURL=quake.getmUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(mURL));
                startActivity(browserIntent);
            }
        });

        //Calling the AsyncTask Thread.
        QuakeAsyncTask task = new QuakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    private class QuakeAsyncTask extends AsyncTask<String, Void, List<Quakes>>{

        @Override
        protected List<Quakes> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            String jsonResponse= JsonHelper.extractEarthQuakes(urls[0]);
            List<Quakes> result = JsonHelper.extractFeatureFromJson(jsonResponse);
            return result;
        }
        @Override
        protected void onPostExecute(List<Quakes> quakes) {
            QuakeAdapter.clear();
            if (quakes != null && !quakes.isEmpty()) {
                QuakeAdapter.addAll(quakes);
            }
        }
    }
}
