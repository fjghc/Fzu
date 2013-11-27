package com.example.fzu;

import com.example.fzu.R;
import com.example.fzu.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class LoginActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_activity);
		
		
	}

}