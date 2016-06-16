package com.spottechnicians.caudit.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.spottechnicians.caudit.R;
import com.spottechnicians.caudit.models.Atm;
import com.spottechnicians.caudit.models.Visit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ganesh on 6/1/2016.
 */
public class DbHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME="CAudit";
    private static final  int DB_VERSION=3;
    public static final String COLUMN_ID="id";
    public static final String COLUMN_ID_ATM="id";
    public static final String COLUMN_SUPERVISOR_ID_ATM="supervisor_id";
    public static final String COLUMN_ATM_ID_ATM="atm_id";
    public static final String TABLE_ATM="atm";
    public static final String COLUMN_BANK_NAME_ATM="bank_name";
    public static final String COLUMN_CUSTOMER_NAME_ATM="customer_name";
    public static final String COLUMN_ADDRESS_ATM="address";
    public static final String COLUMN_CITY_ATM="city";
    public static final String COLUMN_STATE_ATM="state";
    public static final String COLUMN_TYPE_ATM="type";

//    for CTQuestions
    public static final String TABLE_CT_REPORT="CtReport";

    public static final String COLUMN_VISIT_ID="visit_id";
    public static final String COLUMN_ATM_ID="atm_id";
    public static final String COLUMN_USER_ID="user_id";
    public static final String COLUMN_LOCATION="location";
    public static final String COLUMN_BANK_NAME="bank_name";
    public static final String COLUMN_CUSTOMER_NAME="customer_name";
    public static final String COLUMN_DATE_OF_VISIT="date_of_visit";
    public static final String COLUMN_TIME_OF_VISIT="time_of_visit";

/*
    public static final String COLUMN_CTQ1="Is CT present at site";
    public static final String COLUMN_CTQ2="Is CT wearing ID card";
    public static final String COLUMN_CTQ3="Is CT wearing proper uniform";
    public static final String COLUMN_CTQ4="CT telephone working";
    public static final String COLUMN_CTQ5="Is CT chair available at site";
    public static final String COLUMN_CTQ6="Is CT working for more than 12hrs at stretch";
    public static final String COLUMN_CTQ7="All mandatory registers and Logbook are maintained";
    public static final String COLUMN_CTQ8="Does CT know where to deposit cash and cheque";
    public static final String COLUMN_CTQ9="Does CT politely help customer with use of ATM";
    public static final String COLUMN_CTQ10="Does CT ensure only one person per ATM machine";
    public static final String COLUMN_CTQ11="Important numbers are displayed in the ATM";
    public static final String COLUMN_CTQ12="Is the ATM down due to any major problem";*/


    public static final String COLUMN_CTQ1="Q1";
    public static final String COLUMN_CTQ2="Q2";
    public static final String COLUMN_CTQ3="Q3";
    public static final String COLUMN_CTQ4="Q4";
    public static final String COLUMN_CTQ5="Q5";
    public static final String COLUMN_CTQ6="Q6";
    public static final String COLUMN_CTQ7="Q7";
    public static final String COLUMN_CTQ8="Q8";
    public static final String COLUMN_CTQ9="Q9";
    public static final String COLUMN_CTQ10="Q10";
    public static final String COLUMN_CTQ11="Q11";
    public static final String COLUMN_CTQ12="Q12";

    public static final String COLUMN_CT_NAME="caretaker_name";
    public static final String COLUMN_CT_NO="caretaker_number";
    public static final String COLUMN_LATITUDE="latitude";
    public static final String COLUMN_LONGITUDE="longitude";
    public static final String COLUMN_CT_PHOTO1="CT_Photo1";
    public static final String COLUMN_CT_PHOTO2="CT_Photo2";
    public static final String COLUMN_CT_PHOTO3="CT_Photo3";
    public static final String COLUMN_CT_SIGNATURE="CT_Signature";

    Visit visit;

    Visit visitt;



    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public List<Atm> fetchAtms()
{
    List<Atm> atmList=new ArrayList<Atm>();
    String selectQuery="SELECT * FROM "+TABLE_ATM;
    SQLiteDatabase databaseRead=this.getReadableDatabase();
    Cursor cursor=databaseRead.rawQuery(selectQuery,null);
    if(cursor.moveToFirst())
    {
        do{

            atmList.add(new Atm(cursor.getString(cursor.getColumnIndex(COLUMN_SUPERVISOR_ID_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ATM_ID_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CITY_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_STATE_ATM))
                    ,cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_ATM))));
        }while(cursor.moveToNext());
    }
    return atmList;
}

    public List<Atm> fetchAtmsByType(String type)
    {
        List<Atm> atmList=new ArrayList<Atm>();
        String selectQuery="SELECT * FROM "+TABLE_ATM+ " WHERE "+COLUMN_TYPE_ATM+" = "+type;
        SQLiteDatabase databaseRead=this.getReadableDatabase();
        Cursor cursor=databaseRead.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do{

                atmList.add(new Atm(cursor.getString(cursor.getColumnIndex(COLUMN_SUPERVISOR_ID_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ATM_ID_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CITY_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_STATE_ATM))
                        ,cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_ATM))));
            }while(cursor.moveToNext());
        }
        return atmList;
    }
    public  boolean insertATM(List<Atm> atmList)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        long errorCode;
        boolean insertStatus=true;
        ContentValues contentValues=new ContentValues();
        for(int i=0;i<atmList.size();i++)
        {
            contentValues.put(COLUMN_SUPERVISOR_ID_ATM,atmList.get(i).getSupervisorId());
            contentValues.put(COLUMN_ATM_ID_ATM,atmList.get(i).getAtmId());
            contentValues.put(COLUMN_BANK_NAME_ATM,atmList.get(i).getBankName());
            contentValues.put(COLUMN_CUSTOMER_NAME_ATM,atmList.get(i).getCustomerName());
            contentValues.put(COLUMN_ADDRESS_ATM,atmList.get(i).getAddress());
            contentValues.put(COLUMN_CITY_ATM,atmList.get(i).getCity());
            contentValues.put(COLUMN_STATE_ATM,atmList.get(i).getState());
            contentValues.put(COLUMN_STATE_ATM,atmList.get(i).getType());
            errorCode= database.insert(TABLE_ATM,null,contentValues);
            if(errorCode==-1)
            {
                insertStatus=false;
            }
        }



        return insertStatus;
    }







    public ArrayList<Visit> fetchCTReport()
    {
        ArrayList<Visit> visitList=new ArrayList<Visit>();
        String selectQuery="SELECT * FROM "+TABLE_CT_REPORT;
        SQLiteDatabase databaseRead=this.getReadableDatabase();
        Cursor cursor=databaseRead.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do{


                /*visitList.add(new Visit(cursor.getString(cursor.getColumnIndex(COLUMN_VISIT_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ATM_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_VISIT))
                        ,cursor.getString(cursor.getColumnIndex(COLUMN_TIME_OF_VISIT)),
                        new String[] {
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ1)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ2)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ3)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ4)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ5)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ6)),

                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ7)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ8)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ9)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ10)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ11)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ12))},

                        cursor.getString(cursor.getColumnIndex(COLUMN_CT_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CT_NO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE)),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO1))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO2))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO3))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_SIGNATURE)))
                        ));*/



                visitList.add(new Visit(cursor.getString(cursor.getColumnIndex(COLUMN_VISIT_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ATM_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_VISIT))
                        ,cursor.getString(cursor.getColumnIndex(COLUMN_TIME_OF_VISIT)),
                       /* new String[] {
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ1)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ2)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ3)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ4)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ5)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ6)),

                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ7)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ8)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ9)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ10)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ11)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ12))},*/

                        cursor.getString(cursor.getColumnIndex(COLUMN_CT_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CT_NO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE))
                       /* getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO1))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO2))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO3))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_SIGNATURE)))*/
                ));



            }while(cursor.moveToNext());
        }
        return visitList;
    }

    private Bitmap getBitMapFromByte(byte[] blob) {


        return BitmapFactory.decodeByteArray(blob,0,blob.length);

    }


    public  boolean insertCTReport(Visit visitList)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        long errorCode;
        boolean insertStatus=true;
        ContentValues contentValues=new ContentValues();


            contentValues.put(COLUMN_VISIT_ID,visitList.getVisitId());
            contentValues.put(COLUMN_ATM_ID,visitList.getAtmId());
            contentValues.put(COLUMN_USER_ID,visitList.getUserId());
            contentValues.put(COLUMN_LOCATION,visitList.getLocation());
            contentValues.put(COLUMN_BANK_NAME,visitList.getBankName());
            contentValues.put(COLUMN_CUSTOMER_NAME,visitList.getCustomerName());
            contentValues.put(COLUMN_DATE_OF_VISIT,visitList.getDate());
            contentValues.put(COLUMN_TIME_OF_VISIT,visitList.getTime());

            String[] ctReport=visitList.getCt();


            contentValues.put(COLUMN_CTQ1,ctReport[0]);
            contentValues.put(COLUMN_CTQ2,ctReport[1]);
            contentValues.put(COLUMN_CTQ3,ctReport[2]);
            contentValues.put(COLUMN_CTQ4,ctReport[3]);
            contentValues.put(COLUMN_CTQ5,ctReport[4]);
            contentValues.put(COLUMN_CTQ6,ctReport[5]);
            contentValues.put(COLUMN_CTQ7,ctReport[6]);
            contentValues.put(COLUMN_CTQ8,ctReport[7]);
            contentValues.put(COLUMN_CTQ9,ctReport[8]);
            contentValues.put(COLUMN_CTQ10,ctReport[9]);
            contentValues.put(COLUMN_CTQ11,ctReport[10]);
            contentValues.put(COLUMN_CTQ12,ctReport[11]);
/*
        contentValues.put(COLUMN_CTQ1,visitList.getType());
        contentValues.put(COLUMN_CTQ2,visitList.getType());
        contentValues.put(COLUMN_CTQ3,visitList.getType());
        contentValues.put(COLUMN_CTQ4,visitList.getType());
        contentValues.put(COLUMN_CTQ5,visitList.getType());
        contentValues.put(COLUMN_CTQ6,visitList.getType());
        contentValues.put(COLUMN_CTQ7,visitList.getType());
        contentValues.put(COLUMN_CTQ8,visitList.getType());
        contentValues.put(COLUMN_CTQ9,visitList.getType());
        contentValues.put(COLUMN_CTQ10,visitList.getType());
        contentValues.put(COLUMN_CTQ11,visitList.getType());
        contentValues.put(COLUMN_CTQ12,visitList.getType());*/

            contentValues.put(COLUMN_CT_NAME,visitList.getCaretakeName());
            contentValues.put(COLUMN_CT_NO,visitList.getCaretakerNumber());
            contentValues.put(COLUMN_LATITUDE,visitList.getLatitude());
            contentValues.put(COLUMN_LONGITUDE,visitList.getLongitude());
            contentValues.put(COLUMN_CT_PHOTO1,visitList.getCtphoto1ByteArray());
            contentValues.put(COLUMN_CT_PHOTO2,visitList.getCtphoto2ByteArray());
            contentValues.put(COLUMN_CT_PHOTO3,visitList.getCtphoto3ByteArray());
            contentValues.put(COLUMN_CT_SIGNATURE,visitList.getCtSignatureByteArray());


            errorCode= database.insert(TABLE_CT_REPORT,null,contentValues);
            if(errorCode==-1)
            {
                insertStatus=false;
            }




        return insertStatus;
    }



    
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try
            {
                String createDb = "CREATE TABLE " + TABLE_ATM + "(" + COLUMN_ID_ATM + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + COLUMN_SUPERVISOR_ID_ATM + " TEXT ,"
                        + COLUMN_ATM_ID_ATM + " TEXT ,"
                        + COLUMN_BANK_NAME_ATM + " TEXT ,"
                        + COLUMN_CUSTOMER_NAME_ATM + " TEXT ,"
                        + COLUMN_ADDRESS_ATM + " TEXT ,"
                        + COLUMN_CITY_ATM + " TEXT ,"
                        + COLUMN_STATE_ATM + " TEXT ,"
                        + COLUMN_TYPE_ATM+" TEXT)";

                String createCTReport = "CREATE TABLE " + TABLE_CT_REPORT + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + COLUMN_VISIT_ID + " TEXT ,"
                        + COLUMN_ATM_ID + " TEXT ,"
                        + COLUMN_USER_ID + " TEXT ,"
                        + COLUMN_LOCATION + " TEXT ,"
                        + COLUMN_BANK_NAME + " TEXT ,"
                        + COLUMN_CUSTOMER_NAME + " TEXT ,"
                        + COLUMN_DATE_OF_VISIT + " TEXT ,"
                        + COLUMN_TIME_OF_VISIT + " TEXT ,"
                        + COLUMN_CTQ1 + " TEXT ,"+ COLUMN_CTQ2 + " TEXT ," + COLUMN_CTQ3 + " TEXT ,"
                        + COLUMN_CTQ4 + " TEXT ,"+ COLUMN_CTQ5 + " TEXT ," + COLUMN_CTQ6 + " TEXT ,"
                        + COLUMN_CTQ7 + " TEXT ,"+ COLUMN_CTQ8 + " TEXT ," + COLUMN_CTQ9 + " TEXT ,"
                        + COLUMN_CTQ10 + " TEXT ,"+ COLUMN_CTQ11 + " TEXT ," + COLUMN_CTQ12 + " TEXT ,"
                        + COLUMN_CT_NAME + " TEXT ,"
                        + COLUMN_CT_NO + " TEXT ,"
                        + COLUMN_LATITUDE + " TEXT ,"
                        + COLUMN_LONGITUDE + " TEXT ,"
                        + COLUMN_CT_PHOTO1 + " BLOB ," + COLUMN_CT_PHOTO2 + " BLOB ," + COLUMN_CT_PHOTO3 + " BLOB ,"
                        + COLUMN_CT_SIGNATURE + " BLOB ,"
                        + COLUMN_TYPE_ATM+" TEXT)";

                sqLiteDatabase.execSQL(createDb);
                sqLiteDatabase.execSQL(createCTReport);

                Log.e("MangalB","Table ATM create successfully");

            }catch(SQLException e)
            {
                Log.e("MangalB",e.toString());
            }



    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_ATM);
        onCreate(sqLiteDatabase);

    }


    public Visit getVisitFromId(int i) {




        String selectQuery="SELECT * FROM "+TABLE_CT_REPORT+ " WHERE "+COLUMN_ID+" = "+i;

        SQLiteDatabase databaseRead=this.getReadableDatabase();
        Cursor cursor=databaseRead.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {



                /*visitList.add(new Visit(cursor.getString(cursor.getColumnIndex(COLUMN_VISIT_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ATM_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_VISIT))
                        ,cursor.getString(cursor.getColumnIndex(COLUMN_TIME_OF_VISIT)),
                        new String[] {
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ1)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ2)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ3)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ4)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ5)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ6)),

                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ7)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ8)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ9)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ10)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ11)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ12))},

                        cursor.getString(cursor.getColumnIndex(COLUMN_CT_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CT_NO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE)),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO1))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO2))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO3))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_SIGNATURE)))
                        ));*/



             visitt= new Visit(cursor.getString(cursor.getColumnIndex(COLUMN_VISIT_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ATM_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_VISIT))
                        ,cursor.getString(cursor.getColumnIndex(COLUMN_TIME_OF_VISIT)),
                        new String[] {
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ1)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ2)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ3)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ4)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ5)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ6)),

                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ7)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ8)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ9)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ10)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ11)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_CTQ12))},

                        cursor.getString(cursor.getColumnIndex(COLUMN_CT_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CT_NO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE)),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO1))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO2))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_PHOTO3))),
                        getBitMapFromByte(cursor.getBlob(cursor.getColumnIndex(COLUMN_CT_SIGNATURE)))
                );




        }
        return visitt;





    }
}
