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

import com.example.fzu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ClassName:MainActivity
 * Function: TODO 主界面
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @version  
 * @since    Ver 1.1
 * @Date	 2013-11-29		下午9:25:16
 *
 * @see 	 
 * @deprecated 
 */
public class MainActivity extends Activity implements OnClickListener{
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

}

