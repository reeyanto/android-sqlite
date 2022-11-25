package com.reeyanto.androidsqlite.models;

public class Analyze {

    private String jurusan;
    private String jumlah;

    public Analyze(String jurusan, String jumlah) {
        this.jurusan = jurusan;
        this.jumlah = jumlah;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getJumlah() {
        return jumlah;
    }
}
