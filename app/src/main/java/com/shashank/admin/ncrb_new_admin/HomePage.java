package com.shashank.admin.ncrb_new_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomePage extends AppCompatActivity {

    private TextInputEditText uidPolice;
    private Button btnUid;
    public static String contact;
    public static String str1;
    public static ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        uidPolice= findViewById(R.id.uniqueIdentityEdit);
        btnUid= findViewById(R.id.uniqueIDsubmit);

        btnUid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str1= uidPolice.getText().toString();
                String jsonString="{\n" +
                        "\t\n" +
                        "\t\"id\":\"" + str1 + "\"\n" +
                        "}";

                if(!str1.isEmpty()){

                RetrieveFeedTask2 retrieveFeedTask2 = new RetrieveFeedTask2("https://asapro.000webhostapp.com/Retrive_Police.php", jsonString,getApplication());
                retrieveFeedTask2.execute();
                    progressDialog2 = new ProgressDialog(HomePage.this);
                    progressDialog2.setMessage("Loading...");
                    progressDialog2.show();

                }
                else{
                    Toast.makeText(HomePage.this,"Try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}



class RetrieveFeedTask2 extends AsyncTask<String, String, String> {
    //Context context = new FormActivity();
    HomePage mFinish=new HomePage();
    String S = "";
    String fileName = "";
    String S2 = "";
    String finalStatus = null;
    String statusreturn = "";
    Context context;


    public RetrieveFeedTask2(String RegUrl, String jsonString, Context context) {
        this.S = RegUrl;
        this.S2 = jsonString;
        this.context=context.getApplicationContext();
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        String readLineFromBuffer = null;
        int status = 0;
        URL url = null;
        InputStream inStream = null;
        try {

            url = new URL(this.S);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type",
                    "application/json");

            DataOutputStream wr = new DataOutputStream(
                    urlConnection.getOutputStream());
            wr.writeBytes(this.S2.replaceAll("\n","").replaceAll("\t",""));
            wr.flush();
            wr.close();



            status = urlConnection.getResponseCode();
            if(status == 200){

                InputStream inputStream=urlConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));//iso-encoding format
                String response="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                urlConnection.disconnect();
                return response;

            }
            Log.i("Information",readLineFromBuffer);
        } catch (Exception e) {
            String str = e.toString();
            String x  =str;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        this.statusreturn = status + finalStatus;
        return statusreturn;
    }

    protected void onPostExecute(String result) {

        try {
            JSONObject obj = new JSONObject(result);

            HomePage.contact= obj.getString("contact");
            try {
                if (HomePage.contact.equals(MainActivity.phoneNumber)) {

                    SharedPreferences sp = context.getSharedPreferences("USER_DATA1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("userID", HomePage.str1);
                    e.commit();

                    context.startActivity(new Intent(context, AdminPage.class));
                    HomePage.progressDialog2.dismiss();
                } else {
                    Toast.makeText(context, "Wrong Input", Toast.LENGTH_SHORT).show();
                    HomePage.progressDialog2.dismiss();
                }
                HomePage.progressDialog2.dismiss();
            }catch (Exception e){
                HomePage.progressDialog2.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "Wrong Input", Toast.LENGTH_SHORT).show();
            }
         } catch (JSONException e) {
            HomePage.progressDialog2.dismiss();
            e.printStackTrace();
            Toast.makeText(context, "Wrong Input", Toast.LENGTH_SHORT).show();
        }

    }

 }