package com.shashank.admin.ncrb_new_admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class All_fragment extends Fragment {

    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    static public List<Notification> notificationList;
    static public RecyclerView.Adapter adapter;
    EditText text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_layout, container, false);
        mList = (RecyclerView)rootView.findViewById(R.id.notification_list);
        text = (EditText)rootView.findViewById(R.id.search);

        notificationList = new ArrayList<>();
        adapter = new NotificationAdapter(getActivity().getApplicationContext(), notificationList);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(adapter);
        getAllData("Kanpur","Uttar Pradesh");
        return rootView;
    }

    public void getAllData(final String city,final String state){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://asapro.000webhostapp.com/AllDatanotification.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray j= new JSONArray(response);
                    //Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = j.getJSONObject(i);

                            Notification notification = new Notification();
                            notification.setName(jsonObject.getString("Name"));
                            notification.setAge(jsonObject.getString("Age"));
                            notification.setCrime(jsonObject.getString("Criminal_Public"));
                            notification.setImageUrl("https://asapro.000webhostapp.com/Images/"+jsonObject.getString("Photo"));

                            notificationList.add(notification);
                        } catch (JSONException e) {
                            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                    mList.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                Toast.makeText(getContext(), "No Results Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("city",city);
                params.put("state",state);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
