/**
 * CourseFragmentActivity.java
 * com.example.fzu.UI
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-12-13 		zdd
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
*/

package com.example.fzu.UI;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import com.example.fzu.Fzu;
import com.example.fzu.R;
import com.example.fzu.UI.adapter.CourseFragmentAdapter;
import com.example.fzu.entity.Course;
import com.example.fzu.entity.Global;
import com.example.fzu.entity.SingleCourse;
import com.example.fzu.html.MyJsoup;
import com.example.fzu.http.MyHttpClient;
import com.example.fzu.task.AsyncTaskListener;
import com.example.fzu.task.GeneralTask;
import com.example.fzu.utils.FileUtil;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:CourseFragmentActivity
 * Function: 课程表主界面（每周界面）
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @since    Ver 1.1
 * @Date	 2013-12-13		下午3:23:33
 */
public class CourseFragmentActivity extends FragmentActivity implements OnPageChangeListener,OnClickListener{
	private static final String LOG_TAG="CourseFragmentActivity";
	private ViewPager mPager;
    private CourseFragmentAdapter mAdapter;
    private TextView[] tips=new TextView[7];
    private ArrayList<Course>courseList;
    private ArrayList<SingleCourse> singleCourseList=new ArrayList<SingleCourse>();
    private ArrayList<SingleCourse> currentWeekCourseList=new ArrayList<SingleCourse>();
    private int currentWeek=10;    //当前周数
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_fragment_activity);
		
		mAdapter=new CourseFragmentAdapter(getSupportFragmentManager());
		
		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
	
		mPager.setOnPageChangeListener(this);
		init();
		initEvent();
		
	}
	
	private void init()
	{
		tips[0]=(TextView)findViewById(R.id.tv_one);
		tips[1]=(TextView)findViewById(R.id.tv_two);
		tips[2]=(TextView)findViewById(R.id.tv_three);
		tips[3]=(TextView)findViewById(R.id.tv_four);
		tips[4]=(TextView)findViewById(R.id.tv_five);
		tips[5]=(TextView)findViewById(R.id.tv_six);
		tips[6]=(TextView)findViewById(R.id.tv_seven);
	}
	
	private void initEvent()
	{
		for(int i=0;i<tips.length;i++){
			tips[i].setOnClickListener(this);
		}
		onClick(tips[0]);
		
		GeneralTask getCourseTask=new GeneralTask(getString(R.string.getting_course_table),this);
		getCourseTask.setTaskListener(new getCourseTableListener());
		getCourseTask.execute();
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		for(int i=0;i<tips.length;i++)
			if(i!=arg0){
				tips[i].setBackgroundDrawable(null);
				tips[i].setTextColor(getResources().getColor(R.color.skyblue));
			}
			else{
				tips[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_tabtext));
				tips[i].setTextColor(getResources().getColor(R.color.white));
			}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		for(int i=0;i<tips.length;i++){
			if(tips[i].getId()==arg0.getId()){
			     onPageSelected(i);
			     mPager.setCurrentItem(i);
			     break;
			}
		}	
	}
	
	private class getCourseTableListener implements AsyncTaskListener
	{

		@Override
		public boolean doTaskInBackground() {
			
			int currentNum=0;
			while(currentNum<Fzu.TOTAL)
			{
			    try {
				    currentNum++;
				    doGet(Fzu.TARGET_COURCE_URL);
				    courseList=MyJsoup.parserCourse("FZUCourse.html", CourseFragmentActivity.this);
				    for(Course course:courseList){
				    	singleCourseList.addAll(course.turnToSingleCourse());
				    }
		            for(SingleCourse singleCourse:singleCourseList){
		            	String [] temp=singleCourse.getCtl().getWeekrange().split("-");
		            	int start=Integer.parseInt(temp[0]);
		            	int end=Integer.parseInt(temp[1]);
		            	
		            	if(currentWeek>=start&&currentWeek<=end){
		            		currentWeekCourseList.add(singleCourse);
		            	}
		            }
		            mAdapter.setData(currentWeekCourseList);
		            mPager.notify();
				    return true;
			    } catch (Exception e) {
				// TODO Auto-generated catch block
				    if(currentNum<Fzu.TOTAL)
				    	e.printStackTrace();
				    else
				    	Log.d(LOG_TAG,"three doGet all fail");
				    
			    }
			}
			return false;
			
		}

		@Override
		public void onUIProgressUpdate() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUIPostExecute(boolean isSuccess) {	
			// TODO Auto-generated method stub
			if(!isSuccess){
				Toast.makeText(CourseFragmentActivity.this,getString(R.string.getting_fail),Toast.LENGTH_SHORT).show();
				return ;
			}
		}
		
	}
	
	public String doGet(String url) throws ClientProtocolException, IOException
	{
		HttpGet httpRequest=new HttpGet(url);
		String strResult="doGetError";
		
		HttpResponse httpResponse=MyHttpClient.getInstance().execute(httpRequest);
		try{
		if(httpResponse.getStatusLine().getStatusCode()==200){
			InputStream in=httpResponse.getEntity().getContent();
			strResult=FileUtil.changeInputStream(in,"gb2312");
			in.close();
		}else{
			strResult="doPostErrorResponse:"+httpResponse.getStatusLine().getStatusCode();
		}
		
		}finally{
			
			Log.d(LOG_TAG,strResult);
			FileUtil.writeFileData("FZU"+Global.currentStudent.getSid()+"Course.html",strResult,this);
            Global.currentStudent.setCourses(MyJsoup.parserCourse("FZU"+Global.currentStudent.getSid()+"Course.html",this));
		}
		return strResult;
	}

}

