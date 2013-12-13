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

import com.example.fzu.R;
import com.example.fzu.UI.adapter.CourseFragmentAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ViewPager mPager;
    private CourseFragmentAdapter mAdapter;
    private TextView[] tips=new TextView[7];
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

}

