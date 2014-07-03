package com.sadik.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Demo extends Activity {
    /** Called when the activity is first created. */
    float values[]={300,400,150,220,450,50};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pi);
        LinearLayout linear=(LinearLayout) findViewById(R.id.linear);
        
        linear.addView(new MyGraphview(this,values));

    }
}