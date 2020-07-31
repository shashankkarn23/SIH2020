package com.shashank.admin.ncrb_new_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class AssignmentFir extends AppCompatActivity {


    Button btnCheck;
    TextInputEditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_fir);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar2);

        pass= findViewById(R.id.etUniqueIDEdit);
        btnCheck= findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inchargeId=pass.getText().toString();
                if(inchargeId.equals("8787023479")){
                    Intent intent= new Intent(AssignmentFir.this,AvailableCops.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}