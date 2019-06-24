package com.example.pc.final03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Anasayfa extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

        Button epicbutton=findViewById(R.id.EpcBtn);
        epicbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Anasayfa.this,EpicActivity.class);
                startActivity(intent);
            }
        });

        Button astbutton=findViewById(R.id.AstBtn);
        astbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Anasayfa.this,AsteroidActivity.class);
                startActivity(intent);
            }
        });

        Button marsbutton=findViewById(R.id.MrsBtn);
        marsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Anasayfa.this,MarsActivity.class);
                startActivity(intent);
            }
        });

        Button techbutton=findViewById(R.id.TechBtn);
        techbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Anasayfa.this,TechPort.class);
                startActivity(intent);
            }
        });

    }
}
