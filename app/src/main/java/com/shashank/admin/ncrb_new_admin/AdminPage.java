package com.shashank.admin.ncrb_new_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AdminPage extends AppCompatActivity {

    CardView btnLogout,btnVerification,btnFir,btnPatrol,btnBeats, btnFIRAssign;
    ImageView img1,img2,img3,img4,img5,img6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        btnLogout= findViewById(R.id.cardview_item4);
        btnVerification= findViewById(R.id.cardview_item2);
        btnFir= findViewById(R.id.cardview_item3);
        btnFIRAssign= findViewById(R.id.fir_Assign);
        btnPatrol=findViewById(R.id.cardview_item1);
        btnBeats= findViewById(R.id.beatCV);

        img1=findViewById(R.id.icon1Police);
        img2=findViewById(R.id.icon2Verification);
        img3=findViewById(R.id.icon3firFiles);
        img4=findViewById(R.id.fir_Assign_img);
        img5=findViewById(R.id.icon4logOut);
        img6= findViewById(R.id.beatIV);
        try {
            Picasso.get().load("https://asapro.000webhostapp.com/Images/fir.png").into(img3);
            Picasso.get().load("https://asapro.000webhostapp.com/Images/fir.png").into(img4);
            Picasso.get().load("https://asapro.000webhostapp.com/Images/patrolling.png").into(img1);
            Picasso.get().load("https://asapro.000webhostapp.com/Images/patrolling.png").into(img6);
            Picasso.get().load("https://asapro.000webhostapp.com/Images/vari.png").into(img2);
            Picasso.get().load("https://asapro.000webhostapp.com/Images/logout.png").into(img5);


        }catch (Exception ex){}

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        if (toolbar != null) {
            toolbar.setTitle("Sanrakshan(Police)");
            setSupportActionBar(toolbar);
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp= getSharedPreferences("USER_DATA1" , Context.MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                e.putString("userID" , null);
                e.commit();

                Intent intent= new Intent(AdminPage.this,SplashScreen.class);
                startActivity(intent);
                finish();
            }
        });

        btnVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminPage.this,MainActivityVerification.class);
                startActivity(intent);
                finish();
            }
        });

        btnFir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminPage.this,FirAdmin.class);
                startActivity(intent);

            }
        });

        btnPatrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminPage.this,Add_Beat.class);
                startActivity(intent);
            }
        });

        btnFIRAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this,AssignmentFir.class);
                startActivity(intent);
            }
        });
        btnBeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this,Add_Activity_police.class);
                startActivity(intent);
            }
        });
    }
}