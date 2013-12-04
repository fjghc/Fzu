/**
 * MainActivity.java
 * com.example.fzu.UI
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-11-29 		zdd
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
*/

package com.example.fzu.UI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import com.example.fzu.Fzu;
import com.example.fzu.R;
import com.example.fzu.entity.Global;
import com.example.fzu.html.MyJsoup;
import com.example.fzu.http.MyHttpClient;
import com.example.fzu.task.AsyncTaskListener;
import com.example.fzu.task.GeneralTask;
import com.example.fzu.utils.FileUtil;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:MainActivity
 * Function: TODO 主界面
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @since    Ver 1.1
 * @Date	 2013-11-29		下午9:25:16
 */
public class MainActivity extends Activity implements OnClickListener{
	private static final String LOG_TAG="MainActivity";
	private LinearLayout tabCourseTable;
	private LinearLayout tabScore;
	private LinearLayout tabSetting;
	private TextView tvCourseTable;
	private TextView tvScore;
	private TextView tvSetting;
	private TextView tvTitleName;
	private ImageView imgCourse;
	private ImageView imgScore;
	private ImageView imgSetting;
	
	public void onCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		initView();
		initEvent();
		
		
	}
	
	public void initView()
	{
		tabCourseTable=(LinearLayout)findViewById(R.id.tab_course_table);
		tabScore=(LinearLayout)findViewById(R.id.tab_score);
		tabSetting=(LinearLayout)findViewById(R.id.tab_setting);
		tvCourseTable=(TextView)findViewById(R.id.tv_course_table);
		tvScore=(TextView)findViewById(R.id.tv_score);
		tvSetting=(TextView)findViewById(R.id.tv_setting);
		imgCourse=(ImageView)findViewById(R.id.course_img);
		imgScore=(ImageView)findViewById(R.id.score_img);
		imgSetting=(ImageView)findViewById(R.id.setting_img);
		tvTitleName=(TextView)findViewById(R.id.main_title_text);
	}
	
	public void initEvent()
	{
		tabCourseTable.setOnClickListener(this);
		tabScore.setOnClickListener(this);
		tabSetting.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.tab_course_table:
			tvCourseTable.setTextColor(getResources().getColor(R.color.lightblue));
			tvScore.setTextColor(getResources().getColor(R.color.white));
			tvSetting.setTextColor(getResources().getColor(R.color.white));
			imgCourse.setBackgroundDrawable(getResources().getDrawable(R.drawable.course_pressed));
			imgScore.setBackgroundDrawable(getResources().getDrawable(R.drawable.score_normal));
			imgSetting.setBackgroundDrawable(getResources().getDrawable(R.drawable.setting_normal));
			tvTitleName.setText(getString(R.string.course_table));
			GeneralTask getCourseTask=new GeneralTask(getString(R.string.getting_course_table),this);
			getCourseTask.setTaskListener(new getCourseTableListener());
			getCourseTask.execute();
			break;
		case R.id.tab_score:
			tvCourseTable.setTextColor(getResources().getColor(R.color.white));
			tvScore.setTextColor(getResources().getColor(R.color.lightblue));
			tvSetting.setTextColor(getResources().getColor(R.color.white));
			imgCourse.setBackgroundDrawable(getResources().getDrawable(R.drawable.course_normal));
			imgScore.setBackgroundDrawable(getResources().getDrawable(R.drawable.score_pressed));
			imgSetting.setBackgroundDrawable(getResources().getDrawable(R.drawable.setting_normal));
			tvTitleName.setText(getString(R.string.score));
			break;
		case R.id.tab_setting:
			tvCourseTable.setTextColor(getResources().getColor(R.color.white));
			tvScore.setTextColor(getResources().getColor(R.color.white));
			tvSetting.setTextColor(getResources().getColor(R.color.lightblue));
			imgCourse.setBackgroundDrawable(getResources().getDrawable(R.drawable.course_normal));
			imgScore.setBackgroundDrawable(getResources().getDrawable(R.drawable.score_normal));
			imgSetting.setBackgroundDrawable(getResources().getDrawable(R.drawable.setting_pressed));
			tvTitleName.setText(getString(R.string.setting));
			break;
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
				Toast.makeText(MainActivity.this,getString(R.string.getting_fail),Toast.LENGTH_SHORT).show();
				return ;
			}
		}
		
		
		
		
	}
	
	private class getScoreListener implements AsyncTaskListener
	{

		@Override
		public boolean doTaskInBackground() {
			// TODO Auto-generated method stub
			return false;
			
		}

		@Override
		public void onUIProgressUpdate() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUIPostExecute(boolean isSuccess) {
			// TODO Auto-generated method stub
			
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

