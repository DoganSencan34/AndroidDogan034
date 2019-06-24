package com.example.pc.final03;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.final03.model.APODModel;
import com.example.pc.final03.network.ApodRequest;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MainActivity extends Activity implements ApodRequest.ApodListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //send request(request atıcaz)

        ApodRequest request=new ApodRequest(this);

        request.execute();

    }

    @Override
    public void apodRequestDone(String result) {
        //arayüzde gösterme alanı
        Gson gson =new Gson();
        final APODModel apodModel=gson.fromJson(result,APODModel.class);
        ((TextView) findViewById(R.id.apodtitle)).setText(apodModel.title);



        ImageView resim=findViewById(R.id.imageView);
        Picasso.get().load(apodModel.url).into(resim);


        Button btn=findViewById(R.id.apodButon);
        TextView txt=findViewById(R.id.apodtitle);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,ApodDetay.class);
                intent.putExtra("Aciklama", apodModel.explanation);
                intent.putExtra("Tarih", apodModel.date);
                intent.putExtra("Baslik", apodModel.title);
                intent.putExtra("Resim",apodModel.url);
                startActivity(intent);
            }
        });
        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ApodDetay.class);
                intent.putExtra("Aciklama", apodModel.explanation);
                intent.putExtra("Tarih", apodModel.date);
                intent.putExtra("Baslik", apodModel.title);
                intent.putExtra("Resim",apodModel.url);
                startActivity(intent);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Anasayfa.class);
                startActivity(intent);
            }
        });





    }
}
