package com.invent;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class T_Ruang extends Activity {

	ImageView iV1;
	TextView tV1, tV2;
	EditText eT1, eT2;
	Spinner sP1, sP2;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_ruang);
        
        iV1  =  (ImageView)findViewById(R.id.back);
        eT1  =  (EditText)findViewById(R.id.nama);
        eT2  =  (EditText)findViewById(R.id.jumlah);
        tV1  =  (TextView)findViewById(R.id.tambah);
        tV2  =  (TextView)findViewById(R.id.tambah_ruangan);
        sP1  =  (Spinner)findViewById(R.id.spin1);
        sP2  =  (Spinner)findViewById(R.id.spin2);
        
        ArrayList<String> ruang = new ArrayList<String>();
    	Cursor cursor = new DBH(this).getAllRuang();
    	cursor.moveToFirst();
  	    do {
		    ruang.add(cursor.getString(1));
		  }while(cursor.moveToNext());
  	    cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ruang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sP1.setAdapter(adapter);
        
        ArrayList<String> item = new ArrayList<String>();
    	Cursor cur = new DBH(this).getAllItem();
    	cur.moveToFirst();
  	    do {
		    item.add(cur.getString(1));
		  }while(cur.moveToNext());
  	    cur.close();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sP2.setAdapter(adapter2);
        
        tV1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	pesan();
              }
          });

        tV2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	pesan2();
              }
          });
        
        iV1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	T_Ruang.this.startActivity(new Intent(T_Ruang.this, Main.class));
              }
          });   
    }    
    
    public void simpan()
    {
       String ruang = eT1.getText().toString();
       DBH handler = new DBH(this);
       handler.addRuang(ruang);
    }

    public void tambah()
    {
       int ruang  = sP1.getSelectedItemPosition() + 1;
       int item   = sP2.getSelectedItemPosition() + 1;
       int jumlah = Integer.valueOf(eT2.getText().toString());
       DBH handler = new DBH(this);
       handler.addInventaris(ruang, item, jumlah);
    }
    
    public void pesan()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Confirm");
    	builder.setMessage("Yakin mau menambah?");

    	builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

    	    public void onClick(DialogInterface dialog, int which) {
    	    	simpan();
    	        dialog.dismiss();
    	    }
    	});

    	builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {

    	        // Do nothing
    	        dialog.dismiss();
    	    }
    	});

    	builder.create();
    	builder.show();    	
    }
    
    public void pesan2()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Confirm");
    	builder.setMessage("Mau menambah inventaris?");

    	builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

    	    public void onClick(DialogInterface dialog, int which) {
    	    	tambah();
    	        dialog.dismiss();
    	    }
    	});

    	builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {

    	        // Do nothing
    	        dialog.dismiss();
    	    }
    	});

    	builder.create();
    	builder.show();    	
    }
}
