package com.example.fzu.UI.adapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.example.fzu.R;
import com.example.fzu.entity.SingleCourse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CourseAdapter extends BaseAdapter{
	private ArrayList<SingleCourse> mCourses;
	private Context mctx;

	
	private class ViewHolder{
		TextView session;   //节数
		TextView name;      //课程名
		TextView location;  //地点
		TextView week;      //周数
		LinearLayout courseInformation;
	};
	
	
	public CourseAdapter(ArrayList<SingleCourse> courses,Context ctx)
	{
		mCourses=courses;
		mctx=ctx;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCourses.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mCourses.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewholder=null;
		LayoutInflater flater=(LayoutInflater)mctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		if(view==null){
			viewholder=new ViewHolder();
			view=flater.inflate(R.layout.course_fragment_item, parent,false);
			viewholder.session=(TextView)view.findViewById(R.id.course_sessions_tv);
			viewholder.name=(TextView)view.findViewById(R.id.course_name_tv);
			viewholder.location=(TextView)view.findViewById(R.id.course_location_tv);
			viewholder.week=(TextView)view.findViewById(R.id.course_week_tv);
			viewholder.courseInformation=(LinearLayout)view.findViewById(R.id.course_information_ll);
			view.setTag(viewholder);
		}else{
			viewholder=(ViewHolder)view.getTag();
		}
		
		viewholder.session.setText(mCourses.get(position).getCtl().getSessions());
		viewholder.name.setText(mCourses.get(position).getName());	
		viewholder.location.setText(mCourses.get(position).getCtl().getLocation());
		viewholder.week.setText(mCourses.get(position).getCtl().getWeekrange());
		if(mCourses.get(position).getName()==null||mCourses.get(position).getName().equals(""))
			viewholder.courseInformation.setVisibility(View.INVISIBLE);
		else
			viewholder.courseInformation.setVisibility(View.VISIBLE);
		return view;
	}

}
