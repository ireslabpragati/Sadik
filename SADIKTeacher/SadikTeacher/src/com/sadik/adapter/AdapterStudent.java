package com.sadik.adapter;

import java.util.ArrayList;

import com.sadik.model.BeanStudent;
import com.sadik.teacher.CalculateAge;
import com.sadik.teacher.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterStudent extends BaseAdapter{
	
	Context mcontext;
	ArrayList<BeanStudent> mbean;
	int imageId;
	Bitmap b;
	CalculateAge calculateAge;
	/**
	 * sets context and bean
	 * @param context
	 * @param bean
	 */
	public AdapterStudent(Context context,ArrayList<BeanStudent> bean)
	{
		mbean=bean;
		mcontext=context;
		calculateAge = new CalculateAge();
		}

	/**
	 * @inherited
	 */
	@Override
	public int getCount() {
		if(mbean!=null && mbean.size()>0)
			return mbean.size();
		return 0;
	}
/**
 * @inherited
 */
	@Override
	public BeanStudent getItem(int position) {
		if(mbean!=null && mbean.size()>0)
			return mbean.get(position);
		return null;
	}

	/**
	 * @inherited
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @inherited
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(mcontext.LAYOUT_INFLATER_SERVICE);
		final ViewHolder holder;
		
		if(convertView==null)
		{
			convertView=inflater.inflate(R.layout.layout_listview_stutent, null);
			holder=new ViewHolder();
			holder.img_AddStudent_ProfileImage = (ImageView)convertView.findViewById(R.id.img_AddStudent_ProfileImage);
			holder.txt_studentList_StudentId = (TextView)convertView.findViewById(R.id.txt_studentList_StudentId);
			holder.txt_studentList_Name = (TextView)convertView.findViewById(R.id.txt_studentList_Name);
			holder.txt_studentList_Age = (TextView)convertView.findViewById(R.id.txt_studentList_Age);

			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder)convertView.getTag();
		}
		BeanStudent dgbean=getItem(position);
		
		
		
		int age = calculateAge.Calculate(dgbean.getDob());
		String gender= dgbean.getGender();;
		if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("male"))
		{
			gender = mcontext.getResources().getString(R.string.Male);
		}
		else if(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female"))
		{
			gender = mcontext.getResources().getString(R.string.Female);
		}
		// holder.img_AddStudent_ProfileImage.setText(dgbean.getMerchantAddress());
		holder.txt_studentList_StudentId.setText(mcontext.getResources().getString(R.string.Student_Id)+": "+dgbean.getStudent_ID());
		holder.txt_studentList_StudentId.setTag(dgbean.getStudent_Pkey());
		holder.txt_studentList_Name.setText(mcontext.getResources().getString(R.string.Name)+": "+dgbean.getF_Name()+" "+dgbean.getL_Name());
		holder.txt_studentList_Age.setText(mcontext.getResources().getString(R.string.Age)+": "+age+" "+gender);
		Log.i("Listview", "call");

		return convertView;
	}
	/**
	 * Inner class for Listview row item's Views
	 */
	class ViewHolder
	{
		ImageView img_AddStudent_ProfileImage;
		TextView txt_studentList_StudentId, txt_studentList_Name, txt_studentList_Age;

	}

}