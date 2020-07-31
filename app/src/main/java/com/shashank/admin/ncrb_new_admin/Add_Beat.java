package com.shashank.admin.ncrb_new_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Add_Beat extends AppCompatActivity {

    static public RelativeLayout add_beat,main_list_Layout,Beat_report_layout,details_layout;
    private static final String TAG = "Add_Beat";
    private ImageView add;
    private TextView mDate,mTime;
    private Button create, Send_Report;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    static public EditText EdCity,EdArea,EdHead,EdMem1,EdMem2,EdMem3,Report_txt;
    static public TextView City_txt,Area_txt,Date_txt,Time_txt,Head_txt,Mem1_txt,Mem2_txt,Mem3_txt,mob_txt,name_txt;

    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    static public List<Beat> beatList;
    static public RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__beat);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        if (toolbar != null) {
            toolbar.setTitle("Sanrakshan(Police)");
            setSupportActionBar(toolbar);
        }

        mList = (RecyclerView)findViewById(R.id.main_list);
        beatList = new ArrayList<>();
        adapter = new BeatAdapter(getApplicationContext(), beatList);
        linearLayoutManager = new LinearLayoutManager(Add_Beat.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //dividerItemDecoration = new DividerItemDecoration(mList.getContext(), 0);

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        //mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        //RelativeLayout
        add_beat = (RelativeLayout)findViewById(R.id.add_beat);
        main_list_Layout = (RelativeLayout)findViewById(R.id.main_list_layout);
        Beat_report_layout = (RelativeLayout)findViewById(R.id.beat_report);
        details_layout = (RelativeLayout)findViewById(R.id.details);

        //TextView
        mDate = (TextView)findViewById(R.id.Date_edt);
        mTime = (TextView)findViewById(R.id.Time_edt);
        City_txt = (TextView)findViewById(R.id.City_txt);
        Area_txt = (TextView)findViewById(R.id.Area_txt);
        Date_txt = (TextView)findViewById(R.id.Date_txt);
        Time_txt = (TextView)findViewById(R.id.Time_txt);
        Head_txt = (TextView)findViewById(R.id.Team_head_txt);
        Mem1_txt = (TextView)findViewById(R.id.Team_mem_txt_1);
        Mem2_txt = (TextView)findViewById(R.id.Team_mem_txt_2);
        Mem3_txt = (TextView)findViewById(R.id.Team_mem_txt_3);
        name_txt = (TextView)findViewById(R.id.name_txt);
        mob_txt = (TextView)findViewById(R.id.mob_txt);

        //ImageView
        add = (ImageView)findViewById(R.id.add_imageView);

        //EditText
        EdCity = (EditText)findViewById(R.id.City_edt);
        EdArea = (EditText)findViewById(R.id.Area_edt);
        EdHead = (EditText)findViewById(R.id.Team_head_edt);
        EdMem1 = (EditText)findViewById(R.id.Team_mem_edt_1);
        EdMem2 = (EditText)findViewById(R.id.Team_mem_edt_2);
        EdMem3 = (EditText)findViewById(R.id.Team_mem_edt_3);
        Report_txt = (EditText)findViewById(R.id.Report_txt);

        //Button
        create = (Button)findViewById(R.id.Create_btn);
        Send_Report = (Button)findViewById(R.id.report_btn);

        SharedPreferences sp=getSharedPreferences("USER_DATA1" , Context.MODE_PRIVATE);
        String userID= sp.getString("userID" , null);

        getData(userID);

        Head_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Head_txt.getText().toString();
                getinfo(id);
            }
        });

        Mem1_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Mem1_txt.getText().toString();
                getinfo(id);
            }
        });
        Mem2_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Mem2_txt.getText().toString();
                getinfo(id);
            }
        });
        Mem3_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Mem3_txt.getText().toString();
                getinfo(id);
            }
        });

        Send_Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = City_txt.getText().toString();
                String date = Date_txt.getText().toString();
                String time = Time_txt.getText().toString();
                String report = Report_txt.getText().toString();
                Send_Report(city,date,time,report);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = EdCity.getText().toString();
                String area = EdArea.getText().toString();
                String date = mDate.getText().toString();
                String time = mTime.getText().toString();
                String head = EdHead.getText().toString();
                String mem1 = EdMem1.getText().toString();
                String mem2 = EdMem2.getText().toString();
                String mem3 = EdMem3.getText().toString();
                setBeatdata(city,area,date,time,head,mem1,mem2,mem3);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animSlideUp = AnimationUtils.loadAnimation(Add_Beat.this.getApplicationContext(), R.anim.slide_up);
                add_beat.setVisibility(View.VISIBLE);
                add_beat.startAnimation(animSlideUp);
                main_list_Layout.setVisibility(View.INVISIBLE);

            }
        });

        mDate.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(Add_Beat.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        }));

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: "+ month +"/"+ day +"/"+ year);

                String date = day + "/" + month + "/" + year;
                mDate.setText(date);

            }
        };

        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog Tdialog = new TimePickerDialog(Add_Beat.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener,
                        hour,minute,true);
                Tdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Tdialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String time = hourOfDay+ ":" +minute;
                mTime.setText(time);
            }
        };
    }
    private  void getinfo(final String id){
        //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://asapro.000webhostapp.com/getpolicedata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Add_Beat.this, response, Toast.LENGTH_SHORT).show();
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(response);
                    // fetch JSONObject named employee
                    JSONObject object = obj.getJSONObject("serv");

                    name_txt.setText(object.getString("name"));
                    mob_txt.setText(object.getString("mob"));

                    Animation animSlideUp = AnimationUtils.loadAnimation(Add_Beat.this.getApplicationContext(), R.anim.slide_up);
                    details_layout.setVisibility(View.VISIBLE);
                    details_layout.startAnimation(animSlideUp);
                    Send_Report.setVisibility(View.INVISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Add_Beat.this, "no data available", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                Toast.makeText(Add_Beat.this, "No Results Found", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(Add_Beat.this);
        requestQueue.add(stringRequest);
    }

    private void Send_Report(final String city, final String date, final String time, final String report){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://asapro.000webhostapp.com/Update_Report.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("UPDATED")){
                    AlertDialog alertDialog = new AlertDialog.Builder(Add_Beat.this).create();
                    alertDialog.setTitle("Success");
                    alertDialog.setIcon(R.drawable.success);
                    alertDialog.setMessage("Report Sent");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    progressDialog.dismiss();
                                    dialog.dismiss();
                                    onBackPressed();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    progressDialog.dismiss();
         //           Toast.makeText(Add_Beat.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                Toast.makeText(Add_Beat.this, "No Results Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("city",city);
                params.put("date",date);
                params.put("time",time);
                params.put("report",report);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getData(final String id){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://asapro.000webhostapp.com/check_beat.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray j= new JSONArray(response);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = j.getJSONObject(i);

                            Beat beat = new Beat();
                            beat.setArea(jsonObject.getString("area"));
                            beat.setDate(jsonObject.getString("date"));
                            beat.setTime(jsonObject.getString("time"));

                            beatList.add(beat);
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

              //      Toast.makeText(Add_Beat.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                Toast.makeText(Add_Beat.this, "No Results Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setBeatdata(final String city, final String area, final String date, final String time, final String head, final String mem1, final String mem2, final String mem3) {
        final ProgressDialog progressDialog = new ProgressDialog(Add_Beat.this);
        progressDialog.setMessage("Scheduling...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://asapro.000webhostapp.com/Create_Beat.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if(response.equals("Beat Scheduled")){
                    //Toast.makeText(Add_Beat.this, response, Toast.LENGTH_SHORT).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(Add_Beat.this).create();
                    alertDialog.setTitle("Success");
                    alertDialog.setIcon(R.drawable.success);
                    alertDialog.setMessage(response);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    progressDialog.dismiss();
                                    dialog.dismiss();
                                    onBackPressed();
                                }
                            });
                    alertDialog.show();
                }
                else{
                    progressDialog.dismiss();
               //     Toast.makeText(Add_Beat.this, response, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Add_Beat.this, "Beat Scheduling Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("city", city);
                params.put("area", area);
                params.put("date", date);
                params.put("time", time);
                params.put("head", head);
                params.put("mem1", mem1);
                params.put("mem2", mem2);
                params.put("mem3", mem3);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Add_Beat.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        if(details_layout.getVisibility()==View.VISIBLE){
            Animation animSlidedown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
            details_layout.startAnimation(animSlidedown);
            details_layout.setVisibility(View.INVISIBLE);
            Send_Report.setVisibility(View.VISIBLE);
        }
        else if(add_beat.getVisibility()==View.VISIBLE && details_layout.getVisibility()==View.INVISIBLE){
            Animation animSlidedown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
            add_beat.startAnimation(animSlidedown);
            add_beat.setVisibility(View.INVISIBLE);
            main_list_Layout.setVisibility(View.VISIBLE);
            beatList.clear();
            getData("dvu");
        }
        else if(Beat_report_layout.getVisibility()==View.VISIBLE){
            Animation animSlidedown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
            Beat_report_layout.startAnimation(animSlidedown);
            Beat_report_layout.setVisibility(View.INVISIBLE);
            main_list_Layout.setVisibility(View.VISIBLE);
            beatList.clear();
            getData("dvu");
        }
        else {
            super.onBackPressed();
        }
    }
}