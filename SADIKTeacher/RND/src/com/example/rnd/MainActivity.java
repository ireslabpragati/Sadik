package com.example.rnd;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	LinearLayout linearLayout;
	TextView text1, text2;
	TextView clickedText;

	ArrayList<Button> buttons = new ArrayList<Button>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		text1 = (TextView) findViewById(R.id.text1);
		text2 = (TextView) findViewById(R.id.text2);
		
		addButtons(2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void addButtons(int count)
	{
		for (int i = 0; i < count; i++) {

			Button b = new Button(MainActivity.this);
			b.setSingleLine();
			b.setText("Button "+(i+1));
			b.setTag("Button "+(i+1));
			b.setPadding(0, 0, 0, 0);
			b.setTextColor(Color.parseColor("#474644"));
			
			LinearLayout.LayoutParams leftMarginParams;
			if(i == 0)
			{
				b.setBackgroundResource(R.drawable.round_green_dark);				
				leftMarginParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.FILL_PARENT,(1.0f/count));
				
				text2.setTag(b);
			}
			else
			{
				b.setBackgroundResource(R.drawable.round_green_light);				
				leftMarginParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.FILL_PARENT,(1.0f/count));
		        leftMarginParams.leftMargin = 1;
			}
	        
	        b.setLayoutParams(leftMarginParams);
	        
	        b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					unselectAll();
					Button b1 = (Button) v;
					b1.setBackgroundResource(R.drawable.round_green_dark);
						text2.setTag(b1);
						text2.performClick();
				}
			});
	        
	        linearLayout.addView(b);
		}
		
		text1.setTag(text2);

		clickedText = (TextView) text1.getTag();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button btn = (Button) clickedText.getTag();
				Log.i("btn", ": "+btn.getText().toString());
			}
		});
	}
	
	private void unselectAll()
	{
		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			Button b = (Button) linearLayout.getChildAt(i);
			b.setBackgroundResource(R.drawable.round_green_light);
		}
	}
}
