package com.example.fzu;

import com.example.fzu.entity.Student;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity implements OnClickListener{
	private Button btnLogin;
    private EditText edtUserSid;
    private EditText edtUserPasswd;
    private Student mstudent;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_activity);
		initView();
		initEvent();
		mstudent=new Student();
		
	}
	
	private void initView()
	{
		btnLogin = (Button)findViewById(R.id.login_in_btn);
		edtUserSid = (EditText)findViewById(R.id.user_sid_eidt);
		edtUserPasswd = (EditText)findViewById(R.id.user_passwd_eidt);
	}
	
	private void initEvent()
	{
		btnLogin.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.login_in_btn:
			mstudent.setSid(edtUserSid.getEditableText().toString().trim());
		    mstudent.setPasswd(edtUserPasswd.getEditableText().toString().trim());
		    doLogin(mstudent);
		}
	}
	
	
	private boolean doLogin(Student student)
	{
		
		
		
		return false;
	}

}