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
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:MainActivity
 * Function: 程序主界面
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @since    Ver 1.1
 * @Date	 2013-11-29		下午9:25:16
 */
public class MainActivity extends TabActivity implements OnClickListener{

    private TabHost mTabHost;
	
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		init();

	}
	
	public void init()
	{
		mTabHost=getTabHost();
        
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(setTabView(R.string.course_table,R.drawable.course_normal)).setContent(new Intent(this,CourseFragmentActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(setTabView(R.string.setting,R.drawable.setting_normal)).setContent(new Intent(this,SettingActivity.class)));
		mTabHost.setCurrentTab(0);
		
		
	}
	
	/**
	 * 
	 * @Description:设置tab的样式
	 * @param text   tab显示的文字
	 * @param draw   tab显示的图案
	 * @throws
	 */
	public View setTabView(int text,int draw){
		View tabView=LayoutInflater.from(this).inflate(R.layout.tab_style, null);
		TextView tabTV=(TextView)tabView.findViewById(R.id.tab_text);
		tabTV.setText(text);
		tabTV.setTextColor(getResources().getColor(R.color.white));
		ImageView tabIV=(ImageView)tabView.findViewById(R.id.tab_img);
		tabIV.setBackgroundDrawable(getResources().getDrawable(draw));	
		return tabView;
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}


}

