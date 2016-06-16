package com.spottechnicians.caudit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spottechnicians.caudit.DatabaseHandler.DbHelper;
import com.spottechnicians.caudit.models.Atm;

import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    List<Atm> atmList;
    ProgressDialog progressDialog;
    EditText etPassword,etUserid;


    public static SharedPreferences sharedPreferences;
    public static final String USER_ID_LOGIN_PREFERENCES="UserIdLoginPref";
    public static final String UserIdEntered="UserId";


    String password,userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserid=(EditText)findViewById(R.id.etUserid);
        etPassword=(EditText)findViewById(R.id.etpassword);

        sharedPreferences=getSharedPreferences(USER_ID_LOGIN_PREFERENCES,MODE_PRIVATE);

    }

    public void validateLogin(View view)
    {
       //DbHelper dbHelper=new DbHelper(this);
        //if(dbHelper.insertATM(atmList))
       // {

       // }
       // else
       // {

       // }

        userid=etUserid.getText().toString();
        password=etPassword.getText().toString();
       // loadAtmData();
        ValidateLoginDeatails validateLoginDeatails=new ValidateLoginDeatails();
      //  validateLoginDeatails.execute(userid,password);



        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(UserIdEntered,userid);

        editor.commit();
        Intent intent=new Intent(Login.this,Home.class);



        startActivity(intent);
    }

    public void loadAtmData()
    {
        DbHelper dbHelper=new DbHelper(this);
        List<Atm> atmList=dbHelper.fetchAtms();
        String data=atmList.get(0).getAddress();
        Log.e("ListData",data);
    }

    public void dummyDatainsert()
    {
        List<Atm> listOfAtms=new ArrayList<>();
        Atm atmObject;
        atmObject=new Atm();
        String atmid="1FDRUP02";
        atmObject.setAtmId(atmid);
        atmObject.setBankName("yes");
        atmObject.setCustomerName("deibold");
        atmObject.setAddress("sion west sies");
        atmObject.setState("maharasthra");
        atmObject.setCity("mumbai");
        Log.d("listdata",atmid);
        listOfAtms.add(atmObject);
        atmObject=new Atm();
        String atmid2="udg89797";
        atmObject.setAtmId(atmid2);
        atmObject.setBankName("icici");
        atmObject.setCustomerName("hitachi");
        atmObject.setAddress("matunga east");
        atmObject.setState("maharasthra");
        atmObject.setCity("pune");
        Log.d("listdata",atmid);
        listOfAtms.add(atmObject);
    }
    public void jsonParse(String jsonString)
    {
        Atm atmObjects=new Atm();
        atmList=new ArrayList<>();
        Boolean errorStatus;
        String message;

        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            errorStatus=jsonObject.getBoolean("error");
            message=jsonObject.getString("message");
            JSONArray jsonArray=jsonObject.getJSONArray("atms");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonAtm=jsonArray.getJSONObject(i);
                atmObjects.setSupervisorId(jsonAtm.getString("supervisorId"));
                atmObjects.setAtmId(jsonAtm.getString("atmId"));
                atmObjects.setAddress(jsonAtm.getString("address"));
                atmObjects.setBankName(jsonAtm.getString("bankName"));
                atmObjects.setCustomerName(jsonAtm.getString("customerName"));
                atmObjects.setCity(jsonAtm.getString("city"));
                atmObjects.setState(jsonAtm.getString("state"));
                atmObjects.setState(jsonAtm.getString("type"));
                atmList.add(atmObjects);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    class ValidateLoginDeatails extends AsyncTask<String,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(getBaseContext());
            progressDialog.setTitle("Login in progress");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(String... params) {
            String localUserid,localPassword,jsonStream;
            localUserid=params[0];
            String loginURL="";
            localPassword=params[1];
            HttpURLConnection httpURLConnection=null;
            URL url=null;
            try {
                url=new URL(loginURL);
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("userid", "UTF-8")+"="+URLEncoder.encode(localUserid,"UTF-8")+ "&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(localPassword,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                if(bufferedWriter!=null)
                {
                    bufferedWriter.close();
                }
                if(os!=null)
                {
                    os.close();
                }

                InputStream io=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(io));
                StringBuilder stringBuilder=new StringBuilder();
                while((jsonStream=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(jsonStream);
                }
                if(bufferedReader!=null)
                {
                    bufferedReader.close();
                }
                if(io!=null)
                {
                    io.close();
                }
                jsonStream=stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent=new Intent(Login.this,Home.class);
            startActivity(intent);
        }
    }
}
