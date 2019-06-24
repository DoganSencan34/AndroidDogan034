package com.example.pc.final03;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TechPortDetay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_port_detay);




        TextView durumu=findViewById(R.id.status);


        TextView id=findViewById(R.id.id);

        TextView title=findViewById(R.id.title);

        TextView startdate=findViewById(R.id.startdate);

        TextView tvend=findViewById(R.id.tvend);

        TextView description=findViewById(R.id.description);

        id.setText(getIntent().getExtras().getString("Id"));

        durumu.setText(getIntent().getExtras().getString("Durumu"));

        title.setText(getIntent().getExtras().getString("Title"));

        startdate.setText(getIntent().getExtras().getString("StartDate"));

        tvend.setText(getIntent().getExtras().getString("EndDate"));

        description.setText(getIntent().getExtras().getString("Aciklama"));

    }
}
