package com.shashank.admin.ncrb_new_admin;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BeatInfo {
    Context ctx;
    public BeatInfo(Context ctx) {
        this.ctx=ctx;
    }

    public void execute(final String method, final String areaName, final String date, final String time) {
        //Toast.makeText(ctx, method+" "+areaName+" "+date+" "+time, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://asapro.000webhostapp.com/getbeatdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(response);
                    // fetch JSONObject named employee
                    JSONObject object = obj.getJSONObject("serv");

                    Add_Beat.City_txt.setText(object.getString("city"));
                    Add_Beat.Area_txt.setText(object.getString("area"));
                    Add_Beat.Date_txt.setText(object.getString("date"));
                    Add_Beat.Time_txt.setText(object.getString("time"));
                    Add_Beat.Head_txt.setText(object.getString("head"));
                    Add_Beat.Mem1_txt.setText(object.getString("mem1"));
                    Add_Beat.Mem2_txt.setText(object.getString("mem2"));
                    Add_Beat.Mem3_txt.setText(object.getString("mem3"));

                    Animation animSlideUp = AnimationUtils.loadAnimation(ctx.getApplicationContext(),R.anim.slide_up);
                    Add_Beat.Beat_report_layout.startAnimation(animSlideUp);
                    Add_Beat.Beat_report_layout.setVisibility(View.VISIBLE);
                    Add_Beat.main_list_Layout.setVisibility(View.INVISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                Toast.makeText(ctx, "No Results Found", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("id",method);
                params.put("area",areaName);
                params.put("date",date);
                params.put("time",time);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(ctx);
        requestQueue.add(stringRequest);
    }
}
