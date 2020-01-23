package com.invent;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Tampil_Item extends Activity {
	
	ImageView iV;
	ListView lV;
	TextView nm;
	String pindah, nama;
	int Index;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_item);
        
        pindah = getIntent().getExtras().getString("Pindah");
        lV = (ListView)findViewById(R.id.list);
        iV = (ImageView)findViewById(R.id.back);
        nm = (TextView)findViewById(R.id.nm);
        int Ind = -1;
        do {
            Ind++;
        } while (this.pindah.charAt(Ind) != '/');
        Index = Integer.valueOf(this.pindah.substring(0, Ind)).intValue();
        ArrayList<String> item = new ArrayList<String>();
        Cursor cursor = new DBH(this).getItem(Index);
    	cursor.moveToFirst();
    	nama = cursor.getString(1) + " - " + cursor.getString(2);
    	cursor.close();
    	Cursor cr;
    	int jumlah = 0;

        cursor = new DBH(this).getInfo(Index);
    	cursor.moveToFirst();
    	do{
    		int rid = Integer.valueOf(cursor.getString(1)).intValue();
    		cr = new DBH(this).getRuang(rid);
    		cr.moveToFirst();
    		jumlah = jumlah + Integer.valueOf(cursor.getString(3)).intValue();
    		item.add(cr.getString(1) + " = " + cursor.getShort(3));
    		cr.close();
    	}while(cursor.moveToNext());
  	    cursor.close();
  	    
        nm.setText("Jumlah total " + nama + " = " + String.valueOf(jumlah));
  	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	 	           R.layout.mylist, R.id.textView2,  item);
	        lV.setAdapter(adapter);
	        
  	    
		iV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	Tampil_Item.this.startActivity(new Intent(Tampil_Item.this, Main.class));
              }
          });
    }       
}
