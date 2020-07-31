package com.shashank.admin.ncrb_new_admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    public String Str;

    public BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params){
        String var_url = "https://asapro.000webhostapp.com/a.php";
        String record_url = "https://asapro.000webhostapp.com/record_check.php";
        String verify_url = "https://asapro.000webhostapp.com/verify.php";
        String not_verify_url = "https://asapro.000webhostapp.com/notVerify.php";

        String method=params[0];
        if (method.equals("var_check"))
        {
            String reg_TL=params[1];
            String reg_no_ack=params[2];
            Str = "pass";


            try {
                URL url=new URL(var_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //encode data before send it
                //no space permitted in equals sign
                String data= URLEncoder.encode("reg_no_ack","UTF-8")+"="+ URLEncoder.encode(reg_no_ack,"UTF-8")+"&"+
                        URLEncoder.encode("reg_TL","UTF-8")+"="+ URLEncoder.encode(reg_TL,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));//iso-encoding format
                String response="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
                //get response from server
                //  InputStream is=httpURLConnection.getInputStream();
                // is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (method.equals("record_check"))
        {
            String Aadhar=params[1];
            Str = "Aadhar";

            try {
                URL url=new URL(record_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //encode data before send it
                //no space permitted in equals sign
                String data= URLEncoder.encode("Aadhar","UTF-8")+"="+ URLEncoder.encode(Aadhar,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));//iso-encoding format
                String response="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
                //get response from server
                //  InputStream is=httpURLConnection.getInputStream();
                // is.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (method.equals("verify"))
        {
            String reg_get=params[1];
            String type;
            if (reg_get.startsWith("S"))
            {
                type="servant";
            }
            else {
                type="tenant";
            }
            Str = "reg_get";

            try {
                URL url=new URL(verify_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //encode data before send it
                //no space permitted in equals sign
                String data= URLEncoder.encode("reg_get","UTF-8")+"="+ URLEncoder.encode(reg_get,"UTF-8")+"&"+
                        URLEncoder.encode("type","UTF-8")+"="+ URLEncoder.encode(type,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));//iso-encoding format
                String response="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
                //get response from server
                //  InputStream is=httpURLConnection.getInputStream();
                // is.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (method.equals("notVerify"))
        {
            String reg_get=params[1];
            String type;
            if (reg_get.startsWith("S"))
            {
                type="servant";
            }
            else {
                type="tenant";
            }
            Str = "reg_not_get";

            try {
                URL url=new URL(not_verify_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //encode data before send it
                //no space permitted in equals sign
                String data= URLEncoder.encode("reg_get","UTF-8")+"="+ URLEncoder.encode(reg_get,"UTF-8")+"&"+
                        URLEncoder.encode("type","UTF-8")+"="+ URLEncoder.encode(type,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));//iso-encoding format
                String response="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
                //get response from server
                //  InputStream is=httpURLConnection.getInputStream();
                // is.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if(Str=="pass"){
            Str="";
            if(MainActivityVerification.type.equals("servant")) {
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(result);
                    // fetch JSONObject named employee
                    JSONObject employee = obj.getJSONObject("serv");
                    // get employee name and salary
                    String Registration_no = employee.getString("Registration_no");
                    String Name = employee.getString("Name");
                    String Age = employee.getString("Age");
                    String mobile = employee.getString("mobile");
                    String Father_Name = employee.getString("Father_Name");
                    String Mother_Name = employee.getString("Mother_Name");
                    String Current_Address = employee.getString("Current_Address");
                    String Permanent_Address = employee.getString("Permanent_Address");
                    String Aadhar_Number = employee.getString("Aadhar_Number");
                    String Owner_Name = employee.getString("Owner_Name");
                    String Owner_Address = employee.getString("Owner_Address");
                    String Owner_Mobile = employee.getString("Owner_Mobile");
                    String Introducers_Name = employee.getString("Introducers_Name");
                    String Introducers_Address = employee.getString("Introducers_Address");
                    String Introducers_Mobile = employee.getString("Introducers_Mobile");
                    String Date = employee.getString("Date");
                    String Photo = employee.getString("Photo");
                    String Status = employee.getString("Status");

                    String url = "https://asapro.000webhostapp.com/Images/"+Photo;

                    Glide.with(ctx).load(url).into(MainActivityVerification.imageView);
                    // set employee name and salary in TextView's
                    MainActivityVerification.reg.setText(Registration_no);
                    MainActivityVerification.e1.setText(Name);
                    MainActivityVerification.e2.setText(Age);
                    MainActivityVerification.e3.setText(mobile);
                    MainActivityVerification.e4.setText(Father_Name);
                    MainActivityVerification.e5.setText(Mother_Name);
                    MainActivityVerification.e6.setText(Current_Address);
                    MainActivityVerification.e7.setText(Permanent_Address);
                    MainActivityVerification.e8.setText(Aadhar_Number);
                    MainActivityVerification.e9.setText(Owner_Name);
                    MainActivityVerification.e10.setText(Owner_Address);
                    MainActivityVerification.e11.setText(Owner_Mobile);
                    MainActivityVerification.e12.setText(Introducers_Name);
                    MainActivityVerification.e13.setText(Introducers_Address);
                    MainActivityVerification.e14.setText(Introducers_Mobile);
                    MainActivityVerification.e15.setText(Date);

                    Animation animSlideup = AnimationUtils.loadAnimation(ctx.getApplicationContext(), R.anim.slide_up);
                    MainActivityVerification.servant_layout.setVisibility(View.VISIBLE);
                    MainActivityVerification.servant_layout.startAnimation(animSlideup);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            else if (MainActivityVerification.type.equals("tenant")){
                Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(result);
                    // fetch JSONObject named employee
                    JSONObject employee = obj.getJSONObject("serv");
                    // get employee name and salary
                    String Registration_no = employee.getString("Registration_no");
                    String Name = employee.getString("Name");
                    String Father_Name = employee.getString("Father_Name");
                    String Age = employee.getString("Age");
                    String mobile = employee.getString("mobile");
                    String Occupation = employee.getString("Occupation");
                    String Office_Address = employee.getString("Office_Address");
                    String Office_Contact = employee.getString("Office_Contact");
                    String Permanent_Address = employee.getString("Permanent_Address");
                    String ID = employee.getString("ID");
                    String ID_Number = employee.getString("ID_Number");
                    String Landlord_Name = employee.getString("Landlord_Name");
                    String Landlord_Address = employee.getString("Landlord_Address");
                    String Landlord_Mobile = employee.getString("Landlord_Mobile");
                    String Photo = employee.getString("Photo");

                    String url = "https://asapro.000webhostapp.com/Images/"+Photo;

                    Glide.with(ctx).load(url).into(MainActivityVerification.imageView_te);

                    // set employee name and salary in TextView's
                    MainActivityVerification.reg_te.setText(Registration_no);
                    MainActivityVerification.s1.setText(Name);
                    MainActivityVerification.s2.setText(Father_Name);
                    MainActivityVerification.s3.setText(Age);
                    MainActivityVerification.s4.setText(mobile);
                    MainActivityVerification.s5.setText(Occupation);
                    MainActivityVerification.s6.setText(Office_Address);
                    MainActivityVerification.s7.setText(Office_Contact);
                    MainActivityVerification.s8.setText(Permanent_Address);
                    MainActivityVerification.s9.setText(ID);
                    MainActivityVerification.s10.setText(ID_Number);
                    MainActivityVerification.s11.setText(Landlord_Name);
                    MainActivityVerification.s12.setText(Landlord_Address);
                    MainActivityVerification.s13.setText(Landlord_Mobile);

                    Animation animSlideup = AnimationUtils.loadAnimation(ctx.getApplicationContext(), R.anim.slide_up);
                    MainActivityVerification.tenant_layout.setVisibility(View.VISIBLE);
                    MainActivityVerification.tenant_layout.startAnimation(animSlideup);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        else if (Str=="Aadhar"){
            Str="";
            if(result.equals("No Criminal Record Found")){
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ctx);
                builder.setTitle(result);
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });
                builder.show();
            }

            else {
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(result);
                    // fetch JSONObject named employee
                    JSONObject employee = obj.getJSONObject("serv");
                    // get employee name and salary
                    String Name = employee.getString("Name");
                    String Crime= employee.getString("Crime");
                    String Punishment= employee.getString("Punishment");

                    // set employee name and salary in TextView's
                    MainActivityVerification.Name.setText(Name);
                    MainActivityVerification.Crimes.setText(Crime);
                    MainActivityVerification.Punishment.setText(Punishment);

                    Animation animSlideup = AnimationUtils.loadAnimation(ctx.getApplicationContext(),R.anim.slide_up);
                    MainActivityVerification.Crime_layout.setVisibility(View.VISIBLE);
                    MainActivityVerification.Crime_layout.startAnimation(animSlideup);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        else if (Str=="reg_get"){
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    ctx);
            builder.setTitle(result);
            builder.setIcon(R.drawable.check_circle);
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {

                        }
                    });
            builder.show();
        }

        else if (Str=="reg_not_get"){
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    ctx);
            builder.setTitle("Information...");
            builder.setMessage(result);
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {

                        }
                    });
            builder.show();
        }

        else if(result.equals("No Record Found")){
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    ctx);
            builder.setTitle("Information...");
            builder.setMessage(result);
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {

                        }
                    });
            builder.show();
        }

    }
}