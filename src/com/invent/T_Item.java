package com.invent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class T_Item extends Activity {

	ImageView iV1;
	TextView tV1;
	EditText eT1, eT2;
		
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_item);
        
        iV1  =  (ImageView)findViewById(R.id.back);
        eT1   =  (EditText)findViewById(R.id.nama);
        eT2   =  (EditText)findViewById(R.id.info);
        tV1  =  (TextView)findViewById(R.id.tambah_item);
                
        tV1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	pesan();
              }
          });

        iV1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
            	T_Item.this.startActivity(new Intent(T_Item.this, Main.class));
              }
          });   
    }    
    
    public void tambah()
    {
       String item  = eT1.getText().toString();
       String info  = eT2.getText().toString();
       DBH handler = new DBH(this);
       handler.addItem(item, info);
    }
    
    public void pesan()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Confirm");
    	builder.setMessage("Yakin mau menambah?");

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
