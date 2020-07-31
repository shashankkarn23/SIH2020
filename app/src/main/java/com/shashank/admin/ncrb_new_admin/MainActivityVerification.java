package com.shashank.admin.ncrb_new_admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityVerification extends AppCompatActivity {

    String ServerURL = "https://asapro.000webhostapp.com/Sdata.php";
    Button search_all, check, verify, notVerify,verifyte, notVerifyte, checkte;
    Spinner sp1;
    static public String type;
    static public RelativeLayout list_frag, servant_layout, Crime_layout,tenant_layout;
    static public TextView e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,reg;
    static public TextView s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,reg_te;
    static public TextView Name,Crimes, Punishment;
    static public ImageView imageView,imageView_te;

    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    static public List<Vari> variList;
    static public RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_verification);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        if (toolbar != null) {
            toolbar.setTitle("Sanrakshan(Police)");
            setSupportActionBar(toolbar);
        }
        Name=(TextView) findViewById(R.id.Crime_Name_edt);
        Crimes=(TextView) findViewById(R.id.Crimes_edt);
        Punishment=(TextView) findViewById(R.id.Punishment_edt);

        imageView_te=(ImageView)findViewById(R.id.imageView_te);
        reg_te=(TextView) findViewById(R.id.Reg_edt_te);
        s1=(TextView) findViewById(R.id.Tenant_name_edt);
        s2=(TextView) findViewById(R.id.Tenant_F_name_edt);
        s3=(TextView) findViewById(R.id.Tenant_age_edt);
        s4=(TextView) findViewById(R.id.Tenant_mob_edt);
        s5=(TextView) findViewById(R.id.Tenant_occ_edt);
        s6=(TextView) findViewById(R.id.Tenant_office_address_edt);
        s7=(TextView) findViewById(R.id.Tenant_office_mob_edt);
        s8=(TextView) findViewById(R.id.Tenant_permanent_address_edt);
        s9=(TextView) findViewById(R.id.Tenant_Id_edt);
        s10=(TextView) findViewById(R.id.Tenant_Id_no_edt);
        s11=(TextView) findViewById(R.id.Landlord_name_edt);
        s12=(TextView) findViewById(R.id.Landlord_address_edt);
        s13=(TextView) findViewById(R.id.Landlord_mob_edt);

        imageView=(ImageView)findViewById(R.id.imageView);
        reg=(TextView) findViewById(R.id.Reg_edt);
        e1=(TextView) findViewById(R.id.Name_edt);
        e2=(TextView) findViewById(R.id.Age_edt);
        e3=(TextView) findViewById(R.id.mob_edt);
        e4=(TextView) findViewById(R.id.F_Name_edt);
        e5=(TextView) findViewById(R.id.M_Name_edt);
        e6=(TextView) findViewById(R.id.current_Address_edt);
        e7=(TextView) findViewById(R.id.permanent_Address_edt);
        e8=(TextView) findViewById(R.id.Aadhar_Number_edt);
        e9=(TextView) findViewById(R.id.O_Name_edt);
        e10=(TextView) findViewById(R.id.O_address_edt);
        e11=(TextView) findViewById(R.id.O_mob_edt);
        e12=(TextView) findViewById(R.id.I_Name_edt);
        e13=(TextView) findViewById(R.id.I_address_edt);
        e14=(TextView) findViewById(R.id.I_mob_edt);
        e15=(TextView) findViewById(R.id.date_edt);

        sp1 = (Spinner) findViewById(R.id.spinner_type1);

        search_all = (Button)findViewById(R.id.show);
        check = (Button)findViewById(R.id.check_previous_history_bt);
        verify = (Button)findViewById(R.id.verify_bt);
        notVerify = (Button)findViewById(R.id.not_verify);
        checkte = (Button)findViewById(R.id.check_previous_history_bt1);
        verifyte = (Button)findViewById(R.id.verify_bt1);

        list_frag = (RelativeLayout)findViewById(R.id.list_frag);
        servant_layout = (RelativeLayout)findViewById(R.id.servant_layout);
        Crime_layout = (RelativeLayout)findViewById(R.id.verification_layout);
        tenant_layout = (RelativeLayout)findViewById(R.id.tenant_layout);

        mList = (RecyclerView)findViewById(R.id.main_list);

        variList = new ArrayList<>();
        adapter = new VariAdapter(getApplicationContext(), variList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(adapter);

        search_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = sp1.getSelectedItem().toString();
                mList.setVisibility(View.INVISIBLE);
                variList.clear();
                getData(type);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = "record_check";
                String Aadhar = e8.getText().toString();
                BackgroundTask backgroundTask = new BackgroundTask(MainActivityVerification.this);
                backgroundTask.execute(method, Aadhar);
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = "verify";
                String reg_get = reg.getText().toString();
                BackgroundTask backgroundTask = new BackgroundTask(MainActivityVerification.this);
                backgroundTask.execute(method, reg_get);
            }
        });

        checkte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = "record_check";
                String Aadhar = s10.getText().toString();
                BackgroundTask backgroundTask = new BackgroundTask(MainActivityVerification.this);
                backgroundTask.execute(method, Aadhar);
            }
        });

        verifyte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = "verify";
                String reg_get = reg_te.getText().toString();
                BackgroundTask backgroundTask = new BackgroundTask(MainActivityVerification.this);
                backgroundTask.execute(method, reg_get);
            }
        });

        notVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = "notVerify";
                String reg_get;
                if(type.equals("servant")){
                    reg_get = reg.getText().toString();
                }
                else {
                    reg_get = reg_te.getText().toString();
                }

                BackgroundTask backgroundTask = new BackgroundTask(MainActivityVerification.this);
                backgroundTask.execute(method, reg_get);
            }
        });
    }

    private void getData(final String type){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,ServerURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray j= new JSONArray(response);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = j.getJSONObject(i);

                            Vari vari = new Vari();
                            vari.setReg_no(jsonObject.getString("Registration_no"));
                            vari.setName(jsonObject.getString("Name"));

                            variList.add(vari);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                    adapter.notifyDataSetChanged();
                    mList.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();

                    Toast.makeText(MainActivityVerification.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                Toast.makeText(MainActivityVerification.this, "No Results Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("type",type);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        if(Crime_layout.getVisibility()==View.VISIBLE){
            Animation animSlidedown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
            Crime_layout.startAnimation(animSlidedown);
            Crime_layout.setVisibility(View.INVISIBLE);
        }
        else if (servant_layout.getVisibility()==View.VISIBLE && Crime_layout.getVisibility()==View.INVISIBLE){
            Animation animSlidedown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
            servant_layout.startAnimation(animSlidedown);
            servant_layout.setVisibility(View.INVISIBLE);
            type = sp1.getSelectedItem().toString();
            mList.setVisibility(View.INVISIBLE);
            variList.clear();
            getData(type);
        }
        else if (tenant_layout.getVisibility()==View.VISIBLE && Crime_layout.getVisibility()==View.INVISIBLE){
            Animation animSlidedown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
            tenant_layout.startAnimation(animSlidedown);
            tenant_layout.setVisibility(View.INVISIBLE);
            type = sp1.getSelectedItem().toString();
            mList.setVisibility(View.INVISIBLE);
            variList.clear();
            getData(type);
        }
        else {
            super.onBackPressed();
        }
    }
}