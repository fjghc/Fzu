/**
 * SettingActivity.java
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

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * ClassName:SettingActivity
 * Function: 设置主界面
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @since    Ver 1.1
 * @Date	 2013-12-13		下午4:34:20
 */
public class SettingActivity extends Activity{
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.setting_activity);
		
		
	}

}

