package com.example.fzu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.fzu.entity.Fzu;
import com.example.fzu.entity.Student;
import com.example.fzu.http.MyHttpClient;
import com.example.fzu.task.AsyncTaskListener;
import com.example.fzu.task.GeneralTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity implements OnClickListener,AsyncTaskListener{
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
		GeneralTask loginTask=new GeneralTask(getString(R.string.login_logining),this);
		loginTask.setTaskListener(this);
		loginTask.execute();

		return false;
	}

	@Override
	public boolean doTaskInBackground() {
		// TODO Auto-generated method stub
		MyHttpClient httpclient=new MyHttpClient();
		httpclient.init(this);
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("muser","xx"));
		params.add(new BasicNameValuePair("passwd","xx"));
		httpclient.doPost(Fzu.TARGET_URL, params);
		return false;
	}

	@Override
	public void onUIProgressUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUIPostExecute(boolean isSuccess) {
		// TODO Auto-generated method stub
		if(!isSuccess)
			Toast.makeText(this,getString(R.string.loging_fail),Toast.LENGTH_SHORT).show();
	}

}