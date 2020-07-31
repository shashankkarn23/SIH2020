package com.shashank.admin.ncrb_new_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class AvailableCops extends AppCompatActivity {
    public static RecyclerView recyclerView2;
    public static  RecyclerView.Adapter adapterFir2;
    private LinearLayoutManager linearLayoutManagerFir;
    private DividerItemDecoration dividerItemDecorationFir;
    public static ArrayList<String> copID= new ArrayList<>();
    public static ArrayList<String> copName= new ArrayList<>();
    public static ArrayList<String> copContact= new ArrayList<>();
    public static ArrayList<String> copStatus= new ArrayList<>();
    public static ArrayList<String> copStatus2= new ArrayList<>();
    public static String URL_DETAILS="https://asapro.000webhostapp.com/availableExtract.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_cops);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        if (toolbar != null) {
            toolbar.setTitle("Availability of Cops");
            setSupportActionBar(toolbar);
        }
        recyclerView2= findViewById(R.id.available_cops);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        getData();

        adapterFir2 = new PAdatpterFirAssign(copID,copName,copContact,copStatus,getApplicationContext());
        linearLayoutManagerFir = new LinearLayoutManager(this);
        linearLayoutManagerFir.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecorationFir = new DividerItemDecoration(recyclerView2.getContext(), linearLayoutManagerFir.getOrientation());

        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(linearLayoutManagerFir);
        recyclerView2.addItemDecoration(dividerItemDecorationFir);
        recyclerView2.setAdapter(adapterFir2);

        recyclerView2.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView2 ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        if(copStatus2.get(position).equals("available")){
                            Intent intent= new Intent(AvailableCops.this,FirWithFirNo.class);
                            startActivity(intent);
                        }
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

                            copID.add("Cop Id :- "+jsonObject.getString("id"));
                            copName.add("Name :- "+jsonObject.getString("name"));
                            copContact.add("Phone Number :-"+jsonObject.getString("contact"));
                            copStatus.add("Status :-"+jsonObject.getString("availability"));
                            copStatus2.add(jsonObject.getString("availability"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                    adapterFir2.notifyDataSetChanged();
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();

                    Toast.makeText(AvailableCops.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                Toast.makeText(AvailableCops.this, "No Results Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        copID.clear();
        copName.clear();
        copContact.clear();
        copStatus.clear();
        Intent intent = new Intent(AvailableCops.this,AdminPage.class);
        startActivity(intent);
    }
}