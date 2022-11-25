package com.reeyanto.androidsqlite.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {

    private String nim, nama, jurusan;
    private Double latitude, longtitude;

    public Mahasiswa(String nim, String nama, String jurusan, Double latitude, Double longtitude) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    protected Mahasiswa(Parcel in) {
        nim = in.readString();
        nama = in.readString();
        jurusan = in.readString();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longtitude = null;
        } else {
            longtitude = in.readDouble();
        }
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nim);
        parcel.writeString(nama);
        parcel.writeString(jurusan);
        if (latitude == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(latitude);
        }
        if (longtitude == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(longtitude);
        }
    }
}
