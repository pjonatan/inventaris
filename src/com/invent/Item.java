package com.invent;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Item extends Activity {
	
	ImageView  iV1, iV2, iV3;
	ListView lV;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        
        iV1  = (ImageView)findViewById(R.id.edit);
        iV2  = (ImageView)findViewById(R.id.plus);      
        iV3  = (ImageView)findViewById(R.id.back);
        lV   = (ListView)findViewById(R.id.list);
        
        ArrayList<String> data_ruang = new ArrayList<String>();
    	Cursor cursor = new DBH(this).getAllItem();
    	cursor.moveToFirst();
    	if(cursor.getCount()>0){
     	   do {
     		    data_ruang.add(cursor.getString(0) + "/" + cursor.getString(1));
     		  }while(cursor.moveToNext());
     	}
     	cursor.close();  
	 	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	 			R.layout.mylist, R.id.textView2,  data_ruang);
        lV.setAdapter(adapter);

        
        lV.setOnItemClickListener(new OnItemClickListener(){
        	
        	@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3) {
		
				try {
					   finish();
				       Intent intent = new Intent(Item.this, Tampil_Item.class);
				       String pindah = (String) adapter.getItemAtPosition(position); 
				       intent.putExtra("Pindah",pindah);
				       startActivity(intent);
				       } catch (Exception e) {      }
			 }
           }		
        );
        
		iV1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	Item.this.startActivity(new Intent(Item.this, Cari_Item.class));
              }
          });
        
		iV2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	Item.this.startActivity(new Intent(Item.this, T_Item.class));
              }
          });
        
		iV3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	Item.this.startActivity(new Intent(Item.this, Main.class));
              }
          });
        
    }    
}
