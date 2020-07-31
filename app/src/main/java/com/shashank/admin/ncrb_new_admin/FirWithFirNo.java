package com.shashank.admin.ncrb_new_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FirWithFirNo extends AppCompatActivity {

    EditText editTextFirNo,editTextVictimPhone,editTextCopID,editTextCopPhone;
    String messageToCop;
    String messageToVctim,messageToOfficer;
    Button Register_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fir_with_fir_no);

        editTextFirNo= findViewById(R.id.VictimFirNo);
        editTextVictimPhone=findViewById(R.id.contactNoVictim);
        editTextCopID=findViewById(R.id.CopId);
        editTextCopPhone=findViewById(R.id.phone);
        Register_btn=findViewById(R.id.login);

        //Bhai dekh messageToVctim or messageToOfficer ye 2 messages hai ab tjhe
        //officerNo pr messageToOfficer send krna hai or victimPhoneNo pr messageToVctim send krna hai
        
        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firNo=editTextFirNo.getText().toString();
                final String officerNo=editTextCopPhone.getText().toString();
                String copId=editTextCopID.getText().toString();
                final String victimPhoneNo=editTextVictimPhone.getText().toString();

                messageToVctim= "Your Fir Number "+firNo+" has been Registered and now your F.I.R. file has been approved through N.C.R.B. channels"+"\n"+
                        "Current Status of your F.I.R file is OPEN. For further proceedings you can contact to the assigned police officer, whose contact number is "+officerNo;

                messageToOfficer= "Hello Sir/Ma'am "+copId+" you are assigned for the new task By your respective police Station"+"\n"+
                        "You must have to check the status of fir Number "+firNo+". We are also providing the contact number of Victim"+victimPhoneNo+" now you can start your investigation";
                sendMessage(victimPhoneNo,messageToVctim,officerNo,messageToOfficer);
            }
        });
    }

    private void sendMessage(final String victimPhoneNo, final String messageToVctim, final String officerNo, final String messageToOfficer) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://asapro.000webhostapp.com/FirSMS.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(FirWithFirNo.this, response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                //Toast.makeText(FirWithFirNo.this, "No Results Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("victimPhoneNo",victimPhoneNo);
                params.put("messageToVctim",messageToVctim);
                params.put("officerNo",officerNo);
                params.put("messageToOfficer",messageToOfficer);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}