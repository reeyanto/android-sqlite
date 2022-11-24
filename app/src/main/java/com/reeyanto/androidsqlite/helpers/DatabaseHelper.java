package com.reeyanto.androidsqlite.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.reeyanto.androidsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // db and table name
    private static final String DB_NAME = "akademik";
    private static final String TABLE_NAME = "mahasiswa";

    // columns
    private static final String COLUMN_NIM = "nim";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_JURUSAN = "jurusan";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGTITUDE = "longtitude";
    private static final String COLUMN_MOBILE_CONNECTION = "mobile_connection";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+ TABLE_NAME;
               sql+= "(";
               sql+= COLUMN_NIM + " TEXT PRIMARY KEY,";
               sql+= COLUMN_NAMA + " TEXT NOT NULL,";
               sql+= COLUMN_JURUSAN + " TEXT NOT NULL,";
               sql+= COLUMN_LATITUDE + " REAL,";
               sql+= COLUMN_LONGTITUDE + " REAL,";
               sql+= COLUMN_MOBILE_CONNECTION + " TEXT";
               sql+= ")";

       sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mahasiswa> getAllMahasiswa() {
        ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
        String sql = "SELECT * FROM "+ TABLE_NAME;

        SQLiteDatabase reader = this.getReadableDatabase();
        Cursor cursor = reader.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Mahasiswa mahasiswa = new Mahasiswa(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4),
                        cursor.getString(5)
                );
                mahasiswas.add(mahasiswa);
            }
        }
        cursor.close();
        reader.close();
        return mahasiswas;
    }

    private ContentValues setData(Mahasiswa mahasiswa) {
        ContentValues values  = new ContentValues();

        values.put(COLUMN_NIM, mahasiswa.getNim());
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_JURUSAN, mahasiswa.getJurusan());
        values.put(COLUMN_LATITUDE, mahasiswa.getLatitude());
        values.put(COLUMN_LONGTITUDE, mahasiswa.getLongtitude());
        values.put(COLUMN_MOBILE_CONNECTION, mahasiswa.getMobileConnection());

        return values;
    }

    public long insertMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase writer = this.getWritableDatabase();
        ContentValues values  = setData(mahasiswa);

        long result = writer.insert(TABLE_NAME, null, values);
        writer.close();

        return result;
    }

    public int updateMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase writer = this.getWritableDatabase();
        ContentValues values  = setData(mahasiswa);

        int result = writer.update(TABLE_NAME, values, "nim=?", new String[]{mahasiswa.getNim()});
        writer.close();

        return result;
    }

    public int deleteMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase writer = this.getWritableDatabase();
        int result = writer.delete(TABLE_NAME, "nim=?", new String[]{mahasiswa.getNim()});
        writer.close();

        return result;
    }
}
