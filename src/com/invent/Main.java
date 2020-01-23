package com.invent;

import android.app.Activity;
import android.graphics.Color;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.*;

public class Main extends Activity {

	ImageView iB, eX;
	ImageButton iB0;
	TextView tV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
        iB  = (ImageView)findViewById(R.id.ruang);		
        iB0 = (ImageButton)findViewById(R.id.item);
        eX  = (ImageView)findViewById(R.id.exit);
        tV  = (TextView)findViewById(R.id.tV);
        tV.setTextColor(Color.rgb(255,0,0));

		iB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	 finish();
            	 Main.this.startActivity(new Intent(Main.this, Ruang.class));
              }
          });
        
		iB0.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	 finish();
            	 Main.this.startActivity(new Intent(Main.this, Item.class));
              }  
          });
		
		eX.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	finish();
              }
          });
	}
}
