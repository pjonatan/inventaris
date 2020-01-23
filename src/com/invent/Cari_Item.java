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

public class Cari_Item extends Activity {
	
	ImageView iV1;
	ListView lV;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cr_item);
        iV1  = (ImageView)findViewById(R.id.back);
        lV   = (ListView)findViewById(R.id.list);
        
        ArrayList<String> item = new ArrayList<String>();
    	Cursor cursor = new DBH(this).getAllItem();
    	cursor.moveToFirst();
    	if(cursor.getCount()>0){
     	   do {
     		    item.add(cursor.getString(0) + "/" + cursor.getString(1));
     		  }while(cursor.moveToNext());
     	}
     	cursor.close();  
	 	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
 	           R.layout.mylist, R.id.textView2,  item);
        lV.setAdapter(adapter);

        
        lV.setOnItemClickListener(new OnItemClickListener(){
        	
        	@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3) {
		
				try {
					   finish();
				       Intent intent = new Intent(Cari_Item.this, E_Item.class);
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
            	Cari_Item.this.startActivity(new Intent(Cari_Item.this, Main.class));
              }
          });
        
    }    


}
