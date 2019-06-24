package com.example.pc.final03;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.final03.Adapter.EpicAdapter;
import com.example.pc.final03.Item.ListItemEpic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EpicActivity extends Activity {

    private static final String URLDATA="https://api.nasa.gov/EPIC/api/natural/date/2019-05-16?api_key="+Constants.API_KEY+"";

    private RecyclerView recyclerView;//tanımları yapıyoruz
    private RecyclerView.Adapter adapter;
    private List<ListItemEpic> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epic);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();


        loadRecyclerViewData();

    }

    private void loadRecyclerViewData()
    {
        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URLDATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONArray array =new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {

                        JSONObject o=array.getJSONObject(i);
                        ListItemEpic item=new ListItemEpic(
                                o.getString("caption"),

                                o.getString("image"),
                                o.getString("date")
                        );
                        listItems.add(item);
                    }

                    adapter=new EpicAdapter(listItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                    Toast.makeText(getApplicationContext(),"Yüklendi",Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}
