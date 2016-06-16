package com.spottechnicians.caudit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.spottechnicians.caudit.DatabaseHandler.DbHelper;

public class Home extends AppCompatActivity {
    TextView daily_report_tv,other_report_tv,audited_tv,unaudited_tv,sync_tv;

    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbHelper=new DbHelper(this);



      //  Toast.makeText(this,s,Toast.LENGTH_LONG).show();

    }

    public void aduditClicked(View view)
    {
        //Intent intent=new Intent(this,auited_tab.class);
        //startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater .inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void openDailyVisitReport(View view)
    {
        Intent intent=new Intent(this,Daily_Audit.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("Eng"))
        {
            daily_report_tv=(TextView)findViewById(R.id.daily_report_tv);
            daily_report_tv.setText(R.string.daily_visit_hindi);
            other_report_tv=(TextView)findViewById(R.id.other_report_tv);
            other_report_tv.setText(R.string.srm_report_hindi);
            audited_tv=(TextView)findViewById(R.id.audited_tv);
            audited_tv.setText(R.string.audited_hindi);
            unaudited_tv=(TextView)findViewById(R.id.unaudited_tv);
            unaudited_tv.setText(R.string.unaudited_hindi);
            sync_tv=(TextView)findViewById(R.id.sycn_tv);
            sync_tv.setText(R.string.sync_hindi);
            item.setTitle("Hindi");




        }
        else if(item.getTitle().equals("Hindi")) {
            daily_report_tv = (TextView) findViewById(R.id.daily_report_tv);
            daily_report_tv.setText(R.string.daily_visit);
            other_report_tv = (TextView) findViewById(R.id.other_report_tv);
            other_report_tv.setText(R.string.srm_report);
            audited_tv = (TextView) findViewById(R.id.audited_tv);
            audited_tv.setText(R.string.audited);
            unaudited_tv = (TextView) findViewById(R.id.unaudited_tv);
            unaudited_tv.setText(R.string.unaudited);
            sync_tv = (TextView) findViewById(R.id.sycn_tv);
            sync_tv.setText(R.string.sync);
            item.setTitle("Eng");

        }

        return super.onOptionsItemSelected(item);
    }
}
