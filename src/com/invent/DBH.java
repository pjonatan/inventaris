package com.invent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBH extends SQLiteOpenHelper {
	
    private static final String DATABASE_NAME = "/mnt/sdcard/invent.db";    // Database Name
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME1 = "ruang";   // Table Name ruang
    private static final String TABLE_NAME2 = "item";   // Table Name ruang
    private static final String TABLE_NAME3 = "data_ruangan";   // Table Name ruang
    private static final String ID1="r_id";     // Column I (Primary Key)
    private static final String NAMA1 = "nama";    //Column II
    private static final String ID2="i_id";     // Column I (Primary Key)
    private static final String NAMA2 = "nama";    //Column II
    private static final String INFO = "info";    //Column III
    private static final String ID3="d_id";     // Column I (Primary Key)
    private static final String ID4="r_id";    //Column II
    private static final String ID5 ="i_id";    //Column III
    private static final String JUMLAH = "jumlah";    //Column IV
    
    private static final String CREATE_TABLE1 = "CREATE TABLE "+TABLE_NAME1+
	            " ("+ID1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAMA1+" TEXT);";
    
    private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME2+
            " ("+ID2+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAMA2+" TEXT, "+INFO+
            " TEXT);";
    
    private static final String CREATE_TABLE3 = "CREATE TABLE "+TABLE_NAME3+
            " ("+ID3+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ID4+" INTEGER,"+ID5+
    		" INTEGER, "+JUMLAH+" INTEGER);";
	    
    public DBH(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	 
    @Override
    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(CREATE_TABLE1);
	        db.execSQL(CREATE_TABLE2);
	        db.execSQL(CREATE_TABLE3);
	    }
	 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
	        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
	        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
	        onCreate(db);
	    }

    public Cursor getAllRuang() {
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor cursor = db.rawQuery("SELECT * FROM ruang", null);
    	return cursor; 
    }

    public Cursor getTampil(int index) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM data_ruangan WHERE r_id = " + index, null);
        return cursor;
    }

    public Cursor getTampil2(int index) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM data_ruangan WHERE d_id = " + index, null);
        return cursor;
    }

    public Cursor getInfo(int index) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM data_ruangan WHERE i_id = " + index, null);
        return cursor;
    }
    
    public Cursor getRuang(int index) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ruang WHERE r_id = " + index, null);
        return cursor;
    }
    
    public Cursor getItem(int index) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM item WHERE i_id = " + index, null);
        return cursor;
    }
    
    public Cursor getAllItem() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM item", null);
        return cursor;
    }

    public void updateData(String jumlah, int Index) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JUMLAH, jumlah);
        db.update(TABLE_NAME3, contentValues, "d_id = " + Index, null);
        db.close();;
    }
    
    public void updateRuang(String ruang, int Index) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA1, ruang);
        db.update(TABLE_NAME1, contentValues, "r_id = " + Index, null);
        db.close();;
    }

    public void updateItem(String item, String info, int Index) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA1, item);
        contentValues.put(INFO, info);
        db.update(TABLE_NAME2, contentValues, "i_id = " + Index, null);
        db.close();;
    }

    public void deleteData(int Index)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME3 + " WHERE " + ID3 + "=" + Index);    	
    }
    
    public void deleteRuang(int Index)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1 + " WHERE " + ID1 + "=" + Index);    	
    }
    
    public void deleteItem(int Index)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME2 + " WHERE " + ID2 + "=" + Index);    	
    }
    
    public void addRuang(String ruang) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAMA1, ruang);
        db.insert(TABLE_NAME1, null, values);
        db.close();
    }
    
    public void addItem(String item, String info){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAMA1, item);
        values.put(INFO, info);
    	db.insert(TABLE_NAME2, null, values);
        db.close();
    }

    public void addInventaris(int ruang, int item, int jumlah){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID4, ruang);
        values.put(ID5, item);
        values.put(JUMLAH, jumlah);
    	db.insert(TABLE_NAME3, null, values);
        db.close();
    }

}
