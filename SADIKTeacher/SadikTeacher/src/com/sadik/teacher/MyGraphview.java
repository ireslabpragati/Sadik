package com.sadik.teacher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class MyGraphview extends View
{
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] value_degree;
    private int[] COLORS={Color.parseColor("#82C9FF"), Color.parseColor("#FF420E"), 
    		Color.parseColor("#004586"), Color.parseColor("#8DC723"), Color.parseColor("#FED420"), 
    		Color.parseColor("#820026")};     
    RectF rectf = new RectF (10, 10, 200, 200);
    float temp=0.0f;
    
    public MyGraphview(Context context, float[] values) {

        super(context);
        values=calculateData(values);
        value_degree=new float[values.length];
        for(int i=0;i<values.length;i++)
        {
            value_degree[i]=values[i];
            Log.i("Angle", ""+value_degree[i]);
        }
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        for (int i = 0; i < value_degree.length; i++) {//values2.length; i++) {
            if (i == 0) {
                paint.setColor(COLORS[i]);
                canvas.drawArc(rectf, 0, value_degree[i], true, paint);
                
            } 
            else
            {
                    temp += value_degree[i - 1];
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
                    Log.i("sum"+(temp+value_degree[i]), temp+" : "+value_degree[i]);

            }
        }
    }
    
    private float[] calculateData(float[] data) {
        // TODO Auto-generated method stub
        float total=0;
        for(int i=0;i<data.length;i++)
        {
            total+=data[i];
        }
        for(int i=0;i<data.length;i++)
        {
        data[i]=360*(data[i]/total);            
        }
        return data;

    }


}
