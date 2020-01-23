package com.invent;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Tampil_Ruang extends Activity  {

	ImageView iV;
	ListView lV;
	TextView nm;
	String pindah, nama;
	int Index;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_ruang);
        
        pindah = getIntent().getExtras().getString("Pindah");
        lV = (ListView)findViewById(R.id.list);
        iV = (ImageView)findViewById(R.id.back);
        nm = (TextView)findViewById(R.id.nm);
        int iid, Ind = -1;
        do {
            Ind++;
        } while (this.pindah.charAt(Ind) != '/');
        Index = Integer.valueOf(this.pindah.substring(0, Ind)).intValue();
        ArrayList<String> dataruangan = new ArrayList<String>();
        Cursor cursor = new DBH(this).getRuang(Index);
    	cursor.moveToFirst();
    	nama = cursor.getString(1);
    	cursor.close();
    	Cursor cr;
        nm.setText("Inv. di " + nama);
        cursor = new DBH(this).getTampil(Index);
    	cursor.moveToFirst();
        do {
        	iid = Integer.valueOf(cursor.getString(2)).intValue();
        	cr = new DBH(this).getItem(iid);
        	cr.moveToFirst();
		    dataruangan.add(cursor.getString(0) + "/" + cr.getString(1) + " = " + cursor.getString(3));
		    cr.close();
		 }while(cursor.moveToNext());
  	    cursor.close();
	 	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	 	           R.layout.mylist, R.id.textView2,  dataruangan);
	        lV.setAdapter(adapter);
	        
	   lV.setOnItemClickListener(new OnItemClickListener(){
	        	
	        	@Override
				public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3) {
			
					try {
						   finish();
					       Intent intent = new Intent(Tampil_Ruang.this, E_Ruang.class);
					       String pindah = (String) adapter.getItemAtPosition(position); 
					       intent.putExtra("Pindah",pindah);
					       startActivity(intent);
					       } catch (Exception e) {      }
				 }
	        }		
	    );    
  	    
		iV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	Tampil_Ruang.this.startActivity(new Intent(Tampil_Ruang.this, Main.class));
              }
          });
    }     
}
