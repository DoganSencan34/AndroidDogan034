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
import com.example.pc.final03.Adapter.MarsAdapter;
import com.example.pc.final03.Item.ListItemEpic;
import com.example.pc.final03.Item.ListItemMars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarsActivity extends Activity {

    private static final String URLDATA="https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=1&api_key="+Constants.API_KEY+"";

    private RecyclerView recyclerView;//tanımları yapıyoruz
    private RecyclerView.Adapter adapter;
    private List<ListItemMars> listItemsmars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItemsmars=new ArrayList<>();


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

                    //JSONArray array =new JSONArray(response);

                    JSONObject o1 = new JSONObject(response);

                    JSONArray a1 = o1.getJSONArray("photos");//photos başlığı veriliyor..

                    for (int i=0;i<a1.length();i++)
                    {

                        JSONObject o=a1.getJSONObject(i);
                        String imgLink = o.getString("img_src");

                        String date = o.getString("earth_date");


                        JSONObject camera = o.getJSONObject("camera");//başlığın adı

                        String cameraName = camera.getString("full_name");

                        ListItemMars item=new ListItemMars(//veri vermek için döndrüyoruz
                                date,
                                cameraName
                        );

                        item.setImage(imgLink);
                        listItemsmars.add(item);
                    }

                    adapter=new MarsAdapter(listItemsmars,getApplicationContext());

                    recyclerView.setAdapter(adapter);

                    Toast.makeText(getApplicationContext(),"Yüklendi",Toast.LENGTH_LONG).show();


                   /* for (int i=0;i<array.length();i++)
                    {

                        JSONObject o=array.getJSONObject(i);
                        ListItemEpic item=new ListItemEpic(
                                o.getString("caption"),

                                o.getString("image"),
                                o.getString("date")
                        );
                        listItems.add(item);
                    }*/
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

}
