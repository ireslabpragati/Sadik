package com.sadik.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.sadik.teacher.R;

public class LandingActivity extends Activity {

	boolean activitynotfinished=true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_landing);
        
		new CountDownTimer(1200, 400) {

			public void onTick(long millisUntilFinished) {

			}
			 public void onFinish() {
				 
				 if(activitynotfinished)
					{
						Intent intent=new Intent(LandingActivity.this,HomeActivity.class);
						startActivity(intent);
					}
				 finish();
			 }
		}.start();
	}
    
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activitynotfinished = false;
	}
}
