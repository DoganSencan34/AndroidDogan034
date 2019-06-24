package com.example.pc.final03;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MarsDetay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_detay);

        ImageView resim=findViewById(R.id.ImgResim);


        TextView tarih=findViewById(R.id.tvTarih);


        TextView aciklama=findViewById(R.id.tvAciklama);

        aciklama.setText(getIntent().getExtras().getString("Aciklama"));

        String date = getIntent().getExtras().getString("Tarih");

        date = date.split(" ")[0];
        date = date.replace("-", "/");

        tarih.setText(date);

        String img=getIntent().getExtras().getString("Resim");
        Picasso.get().load(img).into(resim);


    }
}
