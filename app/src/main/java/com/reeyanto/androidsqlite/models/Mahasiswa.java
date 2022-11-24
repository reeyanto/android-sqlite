package com.reeyanto.androidsqlite.models;

public class Mahasiswa {

    private String nim, nama, jurusan;
    private Double latitude, longtitude;
    private String mobileConnection;

    public Mahasiswa(String nim, String nama, String jurusan, Double latitude, Double longtitude, String mobileConnection) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.mobileConnection = mobileConnection;
    }

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

    public String getMobileConnection() {
        return mobileConnection;
    }
}
