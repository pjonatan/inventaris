package com.invent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class E_Item extends Activity {
	
	ImageView iV;
	TextView tV1, tV2;
	EditText eT1, eT2;
	String pindah;
	int Index;
		
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_item);
        
        pindah = getIntent().getExtras().getString("Pindah");

        iV  =  (ImageView)findViewById(R.id.back);
        tV1 =  (TextView)findViewById(R.id.ubah);
        tV2 =  (TextView)findViewById(R.id.hapus);
        eT1 =  (EditText)findViewById(R.id.nama);
        eT2 =  (EditText)findViewById(R.id.info);
        
        int Ind = -1;
        do {
            Ind++;
        } while (this.pindah.charAt(Ind) != '/');
        Index = Integer.valueOf(this.pindah.substring(0, Ind)).intValue();
        Cursor cursor = new DBH(this).getItem(Index);
    	cursor.moveToFirst();
    	eT1.setText(cursor.getString(1));
        eT2.setText(cursor.getString(2));
        
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
		
		iV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	E_Item.this.startActivity(new Intent(E_Item.this, Main.class));
              }
          });
    }
 
    public void pesan()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Confirm");
    	builder.setMessage("Yakin mau merubah?");

    	builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

    	    public void onClick(DialogInterface dialog, int which) {
    	    	ubah();
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
    	builder.setMessage("Yakin mau menghapus?");

    	builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

    	    public void onClick(DialogInterface dialog, int which) {
    	    	hapus();
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
    
    public void ubah()
    {
    	DBH handler = new DBH(this);
    	String nama = eT1.getText().toString();
    	String info = eT2.getText().toString();
    	handler.updateItem(nama, info, Index);
    }
    
    public void hapus()
    {
    	DBH handler = new DBH(this);
    	handler.deleteItem(Index);
    }     

}
