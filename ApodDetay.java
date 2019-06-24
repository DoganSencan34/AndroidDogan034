package com.example.pc.final03;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ApodDetay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod_detay);

        TextView tarih=findViewById(R.id.dateTv);
        TextView aciklama=findViewById(R.id.aciklamaTv);
        TextView baslik=findViewById(R.id.baslikTv);
        ImageView resim=findViewById(R.id.imageView);

        aciklama.setText(getIntent().getExtras().getString("Aciklama"));
        tarih.setText(getIntent().getExtras().getString("Tarih"));
        baslik.setText(getIntent().getExtras().getString("Baslik"));
        String url=(getIntent().getExtras().getString("Resim"));

        Picasso.get().load(url).into(resim);

    }
}
