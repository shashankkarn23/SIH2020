package com.shashank.admin.ncrb_new_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shashank.admin.ncrb_new_admin.MainActivityVerification.adapter;

public class FirAdmin extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static  RecyclerView.Adapter adapterFir;
    private LinearLayoutManager linearLayoutManagerFir;

    public static ArrayList<String>firNo= new ArrayList<>();
    public static ArrayList<String>name= new ArrayList<>();
    public static ArrayList<String>adharNo= new ArrayList<>();
    public static ArrayList<String>firNo1= new ArrayList<>();
    public static ArrayList<String>name1= new ArrayList<>();
    public static ArrayList<String>adharNo1= new ArrayList<>();
    String URL_DETAILS ="https://asapro.000webhostapp.com/FirExtract.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fir_admin);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        if (toolbar != null) {
            toolbar.setTitle("F.I.R Lists");
            setSupportActionBar(toolbar);
        }
        recyclerView= findViewById(R.id.FirList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();

        adapterFir = new PAdatpter(firNo,name,adharNo,getApplicationContext());
        linearLayoutManagerFir = new LinearLayoutManager(this);
        linearLayoutManagerFir.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManagerFir);

        recyclerView.setAdapter(adapterFir);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        String myMessage = firNo1.get(position);
                        bundle.putString("message", myMessage );
                        FirFragment fragInfo = new FirFragment();
                        fragInfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragInfo).commit();

                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    private void getData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray j= new JSONArray(response);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = j.getJSONObject(i);

                            firNo.add("Fir Number :- "+jsonObject.getString("fir_no"));
                            name.add("Victim Phone Number :- "+jsonObject.getString("victim_phone_no"));
                            adharNo.add("Victim Adhar Number :-"+jsonObject.getString("victim_adhar_no"));
                            firNo1.add(jsonObject.getString("fir_no"));
                            name1.add(jsonObject.getString("victim_phone_no"));
                            adharNo1.add(jsonObject.getString("victim_adhar_no"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                    adapterFir.notifyDataSetChanged();
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();

                    Toast.makeText(FirAdmin.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                Toast.makeText(FirAdmin.this, "No Results Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        firNo.clear();
        name.clear();
        adharNo.clear();
        firNo1.clear();
        name1.clear();
        adharNo1.clear();
        Intent intent= new Intent(FirAdmin.this,AdminPage.class);
        startActivity(intent);
        finish();
    }
}