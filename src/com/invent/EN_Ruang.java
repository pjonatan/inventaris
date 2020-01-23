package com.invent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class EN_Ruang extends Activity {

	ImageView iV;
	EditText eT;
	TextView tV1, tV2;
	String pindah;
	int Index;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.en_ruang);
        
        pindah = getIntent().getExtras().getString("Pindah");
        iV = (ImageView)findViewById(R.id.back);
        eT = (EditText)findViewById(R.id.nama);
        tV1= (TextView)findViewById(R.id.edit);
        tV2= (TextView)findViewById(R.id.hapus);

        int Ind = -1;
        do {
            Ind++;
        } while (this.pindah.charAt(Ind) != '/');
        Index = Integer.valueOf(this.pindah.substring(0, Ind)).intValue();

        Cursor cursor = new DBH(this).getRuang(Index);
    	cursor.moveToFirst();
        eT.setText(cursor.getString(1));
        
		iV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	EN_Ruang.this.startActivity(new Intent(EN_Ruang.this, Main.class));
              }
          });
		
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
    	String ruang = eT.getText().toString();
    	handler.updateRuang(ruang, Index);
    }
    
    public void hapus()
    {
    	DBH handler = new DBH(this);
    	handler.deleteRuang(Index);
    }
}
