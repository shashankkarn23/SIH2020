package com.shashank.admin.ncrb_new_admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

import static com.shashank.admin.ncrb_new_admin.FirAdmin.firNo;

public class FirFragment extends Fragment {


    public FirFragment() {
        // Required empty public constructor
    }

    public static TextView firNoTv,suspectFirTv,descOffenceFirTv,cityDistrictFirTv,policeStationFirTv,
            CrimeDateTv,CrimeTimeFirTv,TodayDateTv,PlaceCrimeFirTv,AdharCardNoVictimFirTv,PhoneNoVictimFirTv,
            VictimEmailFirTv;
    public static String ServerURL ="https://asapro.000webhostapp.com/Retrive_fir.php";

    public static String stringfirNo,stringsuspectFir,stringdescOffenceFir,stringcityDistrictFir,stringpoliceStationFir,
            stringCrimeDate,stringCrimeTimeFir,stringTodayDate,stringPlaceCrimeFir,stringAdharCardNoVictimFir,stringPhoneNoVictimFir,
            stringVictimEmailFir;


    Button btnView;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fir, container, false);
        String myValue = getArguments().getString("message");

        firNoTv= view.findViewById(R.id.FirNo);
        suspectFirTv= view.findViewById(R.id.nameSuspectFir);
        descOffenceFirTv= view.findViewById(R.id.descOffenceFir);
        cityDistrictFirTv= view.findViewById(R.id.cityDistrictFir);
        policeStationFirTv= view.findViewById(R.id.policeStationFir);
        CrimeDateTv= view.findViewById(R.id.CrimeDate);
        CrimeTimeFirTv= view.findViewById(R.id.CrimeTimeFir);
        TodayDateTv= view.findViewById(R.id.TodayDate);
        PlaceCrimeFirTv= view.findViewById(R.id.PlaceCrimeFir);
        AdharCardNoVictimFirTv= view.findViewById(R.id.AdharCardNoVictimFir);
        PhoneNoVictimFirTv= view.findViewById(R.id.PhoneNoVictimFir);
        VictimEmailFirTv= view.findViewById(R.id.VictimEmailFir);
        btnView= view.findViewById(R.id.OK);

        //getData(myValue);
        String jsonString="{\n" +
                "\t\n" +
                "\t\"fir_no\":\"" + myValue + "\"\n" +
                "}";
        RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask(ServerURL, jsonString,getActivity());
        retrieveFeedTask.execute();

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirAdmin.firNo.clear();
                FirAdmin.name.clear();
                FirAdmin.adharNo.clear();
                FirAdmin.firNo1.clear();
                FirAdmin.name1.clear();
                FirAdmin.adharNo1.clear();

                Intent intent = new Intent(getActivity(),FirAdmin.class);
                startActivity(intent);
            }
        });



        return view;
    }



}

class RetrieveFeedTask extends AsyncTask<String, String, String> {
    ProgressDialog progressDialog;
    //Context context = new FormActivity();
    String S = "";
    String fileName = "";
    String S2 = "";
    String finalStatus = null;
    String dataOutput = "";
    String statusreturn = "";
    Context context;


    public RetrieveFeedTask(String RegUrl, String jsonString, Context context) {
        this.S = RegUrl;
        this.S2 = jsonString;
        this.context=context;
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
                    // this will close the bReader as well
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

            FirFragment.stringfirNo="Fir Number:- "+obj.getString("fir_no");
            FirFragment.stringsuspectFir="Suspect Name:- "+obj.getString("suspect_name");
            FirFragment.stringdescOffenceFir="Offence Description:- "+"\n"+obj.getString("offence_desc");
            FirFragment.stringcityDistrictFir="City / District:- "+obj.getString("City_District");

            FirFragment.stringpoliceStationFir="Police Station:- "+obj.getString("Police_Station");
            FirFragment.stringCrimeDate="Crime Date:- "+obj.getString("crime_date");
            FirFragment.stringCrimeTimeFir="Crime Time:- "+obj.getString("crime_time");
            FirFragment.stringTodayDate="Today's Date:- "+obj.getString("today_date");

            FirFragment.stringPlaceCrimeFir = "Place of crime jurisdiction:- "+"\n\t\t"+obj.getString("crime_place");
            FirFragment.stringAdharCardNoVictimFir = "Victim's Adhar Number:- "+"\n\t\t"+obj.getString("victim_adhar_no");
            FirFragment.stringPhoneNoVictimFir= "Victim's Phone Number:- "+"\n\t\t"+obj.getString("victim_phone_no");
            FirFragment.stringVictimEmailFir= "Victim's Email:- "+"\n\t\t"+obj.getString("victim_email");

            FirFragment.firNoTv.setText(FirFragment.stringfirNo);
            FirFragment.suspectFirTv.setText(FirFragment.stringsuspectFir);
            FirFragment.descOffenceFirTv.setText(FirFragment.stringdescOffenceFir);
            FirFragment.cityDistrictFirTv.setText(FirFragment.stringcityDistrictFir);

            FirFragment.policeStationFirTv.setText(FirFragment.stringpoliceStationFir);
            FirFragment.CrimeDateTv.setText(FirFragment.stringCrimeDate);
            FirFragment.CrimeTimeFirTv.setText(FirFragment.stringCrimeTimeFir);
            FirFragment.TodayDateTv.setText(FirFragment.stringTodayDate);

            FirFragment.PlaceCrimeFirTv.setText(FirFragment.stringPlaceCrimeFir);
            FirFragment.AdharCardNoVictimFirTv.setText(FirFragment.stringAdharCardNoVictimFir);
            FirFragment.PhoneNoVictimFirTv.setText(FirFragment.stringPhoneNoVictimFir);
            FirFragment.VictimEmailFirTv.setText(FirFragment.stringVictimEmailFir);

            progressDialog.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }
}