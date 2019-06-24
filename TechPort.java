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
import com.example.pc.final03.Adapter.MarsAdapter;
import com.example.pc.final03.Adapter.TechPortAdapter;
import com.example.pc.final03.Item.FCallBack;
import com.example.pc.final03.Item.ListItemMars;
import com.example.pc.final03.Item.ListItemTechPort;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TechPort extends Activity {



    private RecyclerView recyclerView;//tanımları yapıyoruz
    private TechPortAdapter adapter;
    private List<ListItemTechPort> listItemstech;



    DateFormat df = new SimpleDateFormat("yyyy-MM-01");
    String now = df.format(new Date());

    private final String URLDATA="https://api.nasa.gov/techport/api/projects?updatedSince="+now+"&api_key=" + Constants.API_KEY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_port);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItemstech=new ArrayList<>();


        loadRecyclerViewData();
    }
    private void loadRecyclerViewData()
    {
        adapter=new TechPortAdapter(getApplicationContext());

        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URLDATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    //JSONArray array =new JSONArray(response);



                    JSONObject o1 = new JSONObject(response);

                    JSONObject a1 = o1.getJSONObject("projects");//photos başlığı veriliyor..

                    JSONArray arr = a1.getJSONArray("projects");

                    for (int i=0;i < arr.length();i++)
                    {
                        JSONObject o = arr.getJSONObject(i);
                        final String id = o.getString("id");

                        load2(id, new FCallBack() {
                            @Override
                            public void run(JSONObject o) {


                                try {
                                    String title = o.getString("title");
                                    String status = o.getString("status");
                                    String startDate = o.getString("startDate");
                                    String endDate = o.getString("endDate");
                                    String description = o.getString("description");



                                    ListItemTechPort item = new ListItemTechPort(id, title, status, startDate, endDate, description);
                                    adapter.addItem(item);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });



                    }



                    recyclerView.setAdapter(adapter);

                    Toast.makeText(getApplicationContext(),"Yüklendi",Toast.LENGTH_LONG).show();



                }
                catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
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

    private void load2(String id, final FCallBack callBack)
    {
        String url2 = "https://api.nasa.gov/techport/api/projects/" + id + "?api_key=" + Constants.API_KEY;



        StringRequest stringRequest=new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    //JSONArray array =new JSONArray(response);

                    JSONObject o1 = new JSONObject(response);

                    JSONObject a1 = o1.getJSONObject("project");//photos başlığı veriliyor..

                    callBack.run(a1);

                }
                catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
