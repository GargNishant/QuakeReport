package com.example.nishantgarg.quakereport;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends Activity {
    // The URL from which we want to extract data.
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=4&limit=90";

    private RecyclerView ParentView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<Quakes> quakesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParentView=(RecyclerView) findViewById(R.id.parentView);
        layoutManager = new LinearLayoutManager(this);
        ParentView.setHasFixedSize(true);
        ParentView.setLayoutManager(layoutManager);
        loadData();

        /*ParentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positionOfItemClicked, long id) {
                Quakes quake=QuakeAdapter.getItem(positionOfItemClicked);
                String mURL=quake.getmUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(mURL));
                startActivity(browserIntent);
            }
        });*/
    }

    private void loadData() {
        Toast.makeText(this, "Starting Process", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, USGS_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject baseJsonResponse = new JSONObject(response);
                            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");
                            for (int i = 0; i < earthquakeArray.length(); i++) {
                                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                                JSONObject properties = currentEarthquake.getJSONObject("properties");
                                double magnitude = properties.getDouble("mag");
                                String location = properties.getString("place");
                                long time = properties.getLong("time");
                                String url = properties.getString("url");

                                Quakes earthquake = new Quakes(magnitude, location, time, url);
                                quakesList.add(earthquake);
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, "Almost Done", Toast.LENGTH_SHORT).show();
                        adapter = new QuakeArrayAdapter(quakesList);
                        ParentView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    /*private class QuakeAsyncTask extends AsyncTask<String, Void, List<Quakes>>{

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
            if (quakes != null && !quakes.isEmpty()) {
                QuakeAdapter = new QuakeArrayAdapter(quakesList);
                Toast.makeText(MainActivity.this, "Fetched Data", Toast.LENGTH_SHORT).show();
                layoutManager = new LinearLayoutManager(MainActivity.this);
                ParentView.setHasFixedSize(true);
                ParentView.setLayoutManager(layoutManager);
                QuakeAdapter = new QuakeArrayAdapter(quakesList);
                ParentView.setAdapter(QuakeAdapter);


            }
        }
    }*/
}
