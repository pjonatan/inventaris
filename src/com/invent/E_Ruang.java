package com.invent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class E_Ruang extends Activity {

	ImageView iV;
	TextView tV1, tV2, tV3, tV4;
	EditText eT1;
	String pindah;
	int Index;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_ruang);
        
        pindah = getIntent().getExtras().getString("Pindah");

        iV  =  (ImageView)findViewById(R.id.back);
        tV1 =  (TextView)findViewById(R.id.nmru);
        tV2 =  (TextView)findViewById(R.id.ubah);
        tV3 =  (TextView)findViewById(R.id.nmit);
        tV4 =  (TextView)findViewById(R.id.hapus);
        eT1 =  (EditText)findViewById(R.id.jumlah);
        
        int iid, iir, Ind = -1;
        do {
            Ind++;
        } while (this.pindah.charAt(Ind) != '/');
        Index = Integer.valueOf(this.pindah.substring(0, Ind)).intValue();
        Cursor cursor = new DBH(this).getTampil2(Index);
    	cursor.moveToFirst();
    	iir = Integer.valueOf(cursor.getString(1)).intValue();
    	Cursor cur = new DBH(this).getRuang(iir);
    	cur.moveToFirst();
    	tV1.setText(cur.getString(1));
    	iid = Integer.valueOf(cursor.getString(2)).intValue();
    	Cursor cr = new DBH(this).getItem(iid);
    	cr.moveToFirst();
    	tV3.setText(cr.getString(1));
        eT1.setText(cursor.getString(3));
        
		tV2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                pesan();            
              }
          });
		
		tV4.setOnClickListener(new View.OnClickListener()
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
            	E_Ruang.this.startActivity(new Intent(E_Ruang.this, Main.class));
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
    	String jumlah = eT1.getText().toString();
    	handler.updateData(jumlah, Index);
    }
    
    public void hapus()
    {
    	DBH handler = new DBH(this);
    	handler.deleteData(Index);
    }
    
}
