package com.spottechnicians.caudit.ModuleCT;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.spottechnicians.caudit.DatabaseHandler.DbHelper;
import com.spottechnicians.caudit.R;
import com.spottechnicians.caudit.models.Visit;

public class Signatuere_Of_CT extends AppCompatActivity {

    EditText etName, etNumber;
    ImageView ivSignature;
    Visit visit = new Visit();
    Bitmap bitmap;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signatuere__of__ct);
        ivSignature = (ImageView) findViewById(R.id.ivSignature);
        etName = (EditText) findViewById(R.id.etCtName);
        etNumber = (EditText) findViewById(R.id.etCtNumber);
        visit = getIntent().getExtras().getParcelable("Visit2");
        bitmap = visit.getCtphoto1();
        ivSignature.setVisibility(View.VISIBLE);
        ivSignature.setImageBitmap(bitmap);

        dbHelper=new DbHelper(this);

    }


    public void onSignaturePressed(View view) {
        ivSignature.setVisibility(View.GONE);
        Intent intent = new Intent(this, SignatureCanvas.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                byte[] byteArray = data.getByteArrayExtra("signature");
                Bitmap bitmapSignature = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                ivSignature.setVisibility(View.VISIBLE);
                visit.setSignature(bitmapSignature);
                ivSignature.setImageBitmap(bitmapSignature);
            }
            if (resultCode == RESULT_CANCELED) {
                //Toast.makeText(this,"result was not ok",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void onSave(View view) {

        boolean status = true;

        if (visit.getSignature() != null) {
            String ctName = etName.getText().toString();
            String ctNumber = etNumber.getText().toString();
            if (ctName != null && ctNumber != null) {
                visit.setCaretakeName(ctName);
                visit.setCaretakerNumber(ctNumber);



            } else if (ctName == "") {
                Toast.makeText(this, "Enter the Name", Toast.LENGTH_LONG).show();
            } else if (ctNumber == "") {
                Toast.makeText(this, "Enter the Phone Number", Toast.LENGTH_LONG).show();
            } else {
                status = false;
                Toast.makeText(this, "Enter Name and Number", Toast.LENGTH_LONG).show();
            }
        } else {
            status = false;
            Toast.makeText(this, "Put your Signature", Toast.LENGTH_LONG).show();
        }

        if (status) {
            if(dbHelper.insertCTReport(visit))
            {Toast.makeText(this,"CT Report inserted successfully",Toast.LENGTH_LONG).show();}

        }


    }

    public void onUpload(View view) {
        onSave(view);

    }

}
