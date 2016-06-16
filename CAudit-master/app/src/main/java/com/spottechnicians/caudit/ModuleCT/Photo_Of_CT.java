package com.spottechnicians.caudit.ModuleCT;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.spottechnicians.caudit.R;
import com.spottechnicians.caudit.models.Visit;

public class Photo_Of_CT extends AppCompatActivity {

    public ImageView selectedImageView;
    ImageView ivCtPhoto1, ivCtPhoto2, ivCtPhoto3;
    Bitmap bitmap[] = new Bitmap[3];
    int imageViewIds[] = {R.id.ivCtPhoto1, R.id.ivCtPhoto2, R.id.ivCtPhoto3};
    Visit visit;
    private byte[] img = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo__of__ct);
        // visit=new Visit();

        visit = getIntent().getExtras().getParcelable("Visit");
        Toast.makeText(this, visit.getAtmId(), Toast.LENGTH_SHORT).show();

        ivCtPhoto1 = (ImageView) findViewById(R.id.ivCtPhoto1);

        ivCtPhoto2 = (ImageView) findViewById(R.id.ivCtPhoto2);

        ivCtPhoto3 = (ImageView) findViewById(R.id.ivCtPhoto3);


    }

    public void next(View view) {
        boolean status = true;
        for (int i = 0; i < bitmap.length; i++) {
            if (bitmap[i] == null) {

                status = false;
            }
        }


        if (status) {
            visit.setCtphoto1(bitmap[0]);
            visit.setCtphoto2(bitmap[1]);
            visit.setCtphoto3(bitmap[2]);
            Intent intent = new Intent(Photo_Of_CT.this, Signatuere_Of_CT.class);
            Log.v("atmid", visit.getAtmId());
            intent.putExtra("Visit2", visit);


            startActivity(intent);

        } else {
            Toast.makeText(this, "Take all the photo", Toast.LENGTH_SHORT).show();
        }


    }

    public void captureImage(View v) {
        /*switch (v.getId())
        {
            case R.id.ivCtPhoto1:
                Toast.makeText(this,1+"",Toast.LENGTH_SHORT).show();
                selectedImageViewId=ivCtPhoto1.getId();
                break;
            case R.id.ivCtPhoto2:
                Toast.makeText(this,1+"",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivCtPhoto3:
                Toast.makeText(this,1+"",Toast.LENGTH_SHORT).show();
                break;


        }*/

        selectedImageView = (ImageView) v;


    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permission", "Permission is granted");
                return true;
            } else {

                Log.v("permission", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permission", "Permission is granted");
            return true;
        }


    }


    public void imageClicked(View view) {
        selectedImageView = (ImageView) view;
        if (isStoragePermissionGranted()) {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        } else {
            Toast.makeText(this, "app donot have the permision", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("permission", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
            //imageClicked();
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {

            Bitmap bp = (Bitmap) data.getExtras().get("data");


            Bitmap.Config config = bp.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }

            Bitmap newBitmap = Bitmap.createBitmap(bp.getWidth(), bp.getHeight(), config);
            Canvas canvas = new Canvas(newBitmap);

            canvas.drawBitmap(bp, 0, 0, null);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setTextSize(20);
            canvas.drawText("Some Text here", 10, 30, paint);


            // Log.e("compress",newBitmap.getByteCount()+"");

            for (int i = 0; i < imageViewIds.length; i++) {
                if (imageViewIds[i] == selectedImageView.getId()) {
                    bitmap[i] = newBitmap;
                }
            }
            //bitmap[0]=newBitmap;
            selectedImageView.setImageBitmap(newBitmap);

            //ByteArrayOutputStream bos=new ByteArrayOutputStream();
            // newBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            //img=bos.toByteArray();
            //img=Utils.getImageBytes(newBitmap);
            // Log.e("compress",Utils.getStringImage(newBitmap)+"ganesh");


        } else {
            Toast.makeText(this, "image cancelled", Toast.LENGTH_SHORT).show();
        }


    }


}
