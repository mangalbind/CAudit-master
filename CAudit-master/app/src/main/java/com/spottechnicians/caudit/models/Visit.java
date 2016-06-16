package com.spottechnicians.caudit.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by onestech on 04/06/16.
 */
public class Visit implements Parcelable {

    public static final Creator<Visit> CREATOR = new Creator<Visit>() {
        @Override
        public Visit createFromParcel(Parcel in) {
            return new Visit(in);
        }

        @Override
        public Visit[] newArray(int size) {
            return new Visit[size];
        }
    };
    Bitmap ctphoto1;
    Bitmap ctphoto2;
    Bitmap ctphoto3;
    Bitmap signature;
    HashMap<String, Bitmap> ctPicList;
    String[] hk, ct, srm;
    private String viewPhotos;
    private String visitId;
    private String atmId;
    private String userId;
    private String location;
    private String bankName;
    private String customerName;
    private String date;
    private String time;
    private String caretakeName;
    private String caretakerNumber;

    public Visit(String visitId, String atmId, String userId, String location, String bankName,String customerName, String date,
                 String time, String[] ct, String caretakeName, String caretakerNumber, String latitude, String longitude,
                 Bitmap ctphoto1, Bitmap ctphoto2, Bitmap ctphoto3, Bitmap signature) {



        this.visitId = visitId;
        this.atmId = atmId;
        this.userId = userId;
        this.location = location;
        this.bankName = bankName;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.ct = ct;

        this.caretakeName = caretakeName;
        this.caretakerNumber = caretakerNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ctphoto1 = ctphoto1;
        this.ctphoto2 = ctphoto2;
        this.ctphoto3 = ctphoto3;
        this.signature = signature;
    }


    public Visit(String visitId, String atmId, String userId, String location, String bankName,String customerName, String date,
                 String time,String caretakeName, String caretakerNumber, String latitude, String longitude) {



        this.visitId = visitId;
        this.atmId = atmId;
        this.userId = userId;
        this.location = location;
        this.bankName = bankName;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.ct = ct;

        this.caretakeName = caretakeName;
        this.caretakerNumber = caretakerNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ctphoto1 = ctphoto1;
        this.ctphoto2 = ctphoto2;
        this.ctphoto3 = ctphoto3;
        this.signature = signature;
    }




    private String housekeeperName;
    private String housekeeperNumber;
    private String srmName;
    private String SrmNumber;
    private String latitude;
    private String longitude;



    protected Visit(Parcel in) {
        viewPhotos = in.readString();
        visitId = in.readString();
        atmId = in.readString();
        userId = in.readString();
        location = in.readString();
        bankName = in.readString();
        customerName = in.readString();
        date = in.readString();
        time = in.readString();
        caretakeName = in.readString();
        caretakerNumber = in.readString();
        housekeeperName = in.readString();
        housekeeperNumber = in.readString();
        srmName = in.readString();
        SrmNumber = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        signature = in.readParcelable(Bitmap.class.getClassLoader());
        ctphoto1 = in.readParcelable(Bitmap.class.getClassLoader());
        ctphoto2 = in.readParcelable(Bitmap.class.getClassLoader());
        ctphoto3 = in.readParcelable(Bitmap.class.getClassLoader());
        hk = in.createStringArray();
        ct = in.createStringArray();
        srm = in.createStringArray();
    }

    public Visit() {
    }

    public Bitmap getCtphoto1() {
        return ctphoto1;
    }

    public byte[] getCtphoto1ByteArray() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ctphoto1.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        return stream.toByteArray();

    }

    public byte[] getCtphoto2ByteArray() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ctphoto2.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        return stream.toByteArray();

    }
    public byte[] getCtphoto3ByteArray() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ctphoto3.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        return stream.toByteArray();

    }

    public byte[] getCtSignatureByteArray() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        signature.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        return stream.toByteArray();

    }


    public void setCtphoto1(Bitmap ctphoto1) {
        this.ctphoto1 = ctphoto1;
    }

    public Bitmap getCtphoto2() {
        return ctphoto2;
    }

    public void setCtphoto2(Bitmap ctphoto2) {
        this.ctphoto2 = ctphoto2;
    }

    public Bitmap getCtphoto3() {
        return ctphoto3;
    }

    public void setCtphoto3(Bitmap ctphoto3) {
        this.ctphoto3 = ctphoto3;
    }

    public Bitmap getSignature() {
        return signature;
    }

    public void setSignature(Bitmap signature) {
        this.signature = signature;
    }

    public boolean checkCTComplete()
    {
        boolean status=true;
        for(int i=0;i<ct.length;i++)
        {
            if(ct[i]=="") {
                status = false;
            }
        }
        return status;
    }

    public String getViewPhotos() {
        return viewPhotos;
    }

    public void setViewPhotos(String viewPhotos) {
        this.viewPhotos = viewPhotos;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCaretakeName() {
        return caretakeName;
    }

    public void setCaretakeName(String caretakeName) {
        this.caretakeName = caretakeName;
    }

    public String getCaretakerNumber() {
        return caretakerNumber;
    }

    public void setCaretakerNumber(String caretakerNumber) {
        this.caretakerNumber = caretakerNumber;
    }

    public String getHousekeeperName() {
        return housekeeperName;
    }

    public void setHousekeeperName(String housekeeperName) {
        this.housekeeperName = housekeeperName;
    }

    public String getHousekeeperNumber() {
        return housekeeperNumber;
    }

    public void setHousekeeperNumber(String housekeeperNumber) {
        this.housekeeperNumber = housekeeperNumber;
    }

    public String getSrmName() {
        return srmName;
    }

    public void setSrmName(String srmName) {
        this.srmName = srmName;
    }

    public String getSrmNumber() {
        return SrmNumber;
    }

    public void setSrmNumber(String srmNumber) {
        SrmNumber = srmNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String[] getHk() {
        return hk;
    }

    public void setHk(String[] hk) {
        this.hk = hk;
    }

    public String[] getCt() {
        return ct;
    }

    public void setCt(String[] ct) {
        this.ct = ct;
    }

    public String[] getSrm() {
        return srm;
    }

    public void setSrm(String[] srm) {
        this.srm = srm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(viewPhotos);
        dest.writeString(visitId);
        dest.writeString(atmId);
        dest.writeString(userId);
        dest.writeString(location);
        dest.writeString(bankName);
        dest.writeString(customerName);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(caretakeName);
        dest.writeString(caretakerNumber);
        dest.writeString(housekeeperName);
        dest.writeString(housekeeperNumber);
        dest.writeString(srmName);
        dest.writeString(SrmNumber);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeParcelable(signature, flags);
        dest.writeParcelable(ctphoto1, flags);
        dest.writeParcelable(ctphoto2, flags);
        dest.writeParcelable(ctphoto3, flags);
        dest.writeStringArray(hk);
        dest.writeStringArray(ct);
        dest.writeStringArray(srm);
    }
}
