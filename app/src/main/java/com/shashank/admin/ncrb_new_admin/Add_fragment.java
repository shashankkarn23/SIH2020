package com.shashank.admin.ncrb_new_admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class Add_fragment extends Fragment {

    CheckBox checkBox,state_CheckBox,city_CheckBox,criminal_CheckBox,public_CheckBox;
    EditText region_txt,name_txt,age_txt,crime_txt,fir_txt;
    ImageView imageView;
    Bitmap bitmap;
    String encodedImage="";
    Button send;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_layout, container, false);
        //CheckBox
        checkBox=(CheckBox)rootView.findViewById(R.id.checkbox);
        state_CheckBox=(CheckBox)rootView.findViewById(R.id.state_checkbox);
        city_CheckBox=(CheckBox)rootView.findViewById(R.id.city_checkbox);
        criminal_CheckBox=(CheckBox)rootView.findViewById(R.id.criminal_checkbox);
        public_CheckBox=(CheckBox)rootView.findViewById(R.id.public_checkbox);

        //EditText
        region_txt=(EditText) rootView.findViewById(R.id.Region_textView);
        name_txt=(EditText) rootView.findViewById(R.id.Name_textView);
        age_txt=(EditText) rootView.findViewById(R.id.Age_textView);
        crime_txt=(EditText) rootView.findViewById(R.id.Crime_textView);
        fir_txt=(EditText) rootView.findViewById(R.id.Fir_textView);

        //ImageView
        imageView=(ImageView)rootView.findViewById(R.id.imageView);

        //Button
        send=(Button)rootView.findViewById(R.id.button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Send To police", Toast.LENGTH_SHORT).show();
                String name = name_txt.getText().toString();
                String age = age_txt.getText().toString();
                String crime;
                String region;
                String background;
                String status;
                String fir = fir_txt.getText().toString();
                if(checkBox.isChecked()){
                    region = "India";
                }
                else{
                    region = region_txt.getText().toString();
                }
                if(criminal_CheckBox.isChecked()){
                    background = "Criminal";
                    crime = crime_txt.getText().toString();
                }
                else {
                    background = "Victim";
                    crime = "";
                }
                if(city_CheckBox.isChecked()){
                    status = "Show";
                }
                else {
                    status = "Request";
                }
                if (encodedImage.equals("")){
                    Toast.makeText(getContext(), "Please Select a Photo", Toast.LENGTH_SHORT).show();
                }
                else {
                    sendDetails(name,age,crime,region,encodedImage,background,status,fir);
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
            }
        });

        criminal_CheckBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                criminal_CheckBox.setChecked(true);
                public_CheckBox.setChecked(false);
                crime_txt.setEnabled(true);
                crime_txt.setHint("Crime");
                return true;
            }
        });

        public_CheckBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                criminal_CheckBox.setChecked(false);
                public_CheckBox.setChecked(true);
                crime_txt.setEnabled(false);
                crime_txt.setHint("");
                return true;
            }
        });

        checkBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                region_txt.setEnabled(false);
                state_CheckBox.setChecked(false);
                checkBox.setChecked(true);
                city_CheckBox.setChecked(false);
                region_txt.setHint("");
                return true;
            }
        });
        state_CheckBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                region_txt.setEnabled(true);
                checkBox.setChecked(false);
                state_CheckBox.setChecked(true);
                city_CheckBox.setChecked(false);
                region_txt.setHint("State");
                return true;
            }
        });
        city_CheckBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                checkBox.setChecked(false);
                state_CheckBox.setChecked(false);
                city_CheckBox.setChecked(true);
                region_txt.setHint("City");
                region_txt.setEnabled(true);
                return true;
            }
        });
        return  rootView;
    }

    public void sendDetails(final String name, final String age, final String crime, final String region, final String encodedImage,
                            final String background,final String status,final String fir){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Submitting...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://asapro.000webhostapp.com/Beat_notification.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                if(response.equals("Successful Registration")){
                    name_txt.getText().clear();
                    age_txt.getText().clear();
                    crime_txt.getText().clear();
                    region_txt.getText().clear();
                    imageView.setImageBitmap(null);
                    imageView.setImageResource(R.drawable.select_image);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("age", age);
                params.put("crime", crime);
                params.put("region", region);
                params.put("image", encodedImage);
                params.put("background", background);
                params.put("status", status);
                params.put("fir", fir);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getActivity().getApplicationContext().getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                imageStore(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}
