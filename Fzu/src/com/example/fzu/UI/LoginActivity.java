package com.example.fzu.UI;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.example.fzu.Fzu;
import com.example.fzu.R;
import com.example.fzu.R.id;
import com.example.fzu.R.layout;
import com.example.fzu.R.string;
import com.example.fzu.entity.Student;
import com.example.fzu.http.MyHttpClient;
import com.example.fzu.task.AsyncTaskListener;
import com.example.fzu.task.GeneralTask;
import com.example.fzu.utils.FileUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private static final String LOG_TAG="LoginActivity";

	
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
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("muser","xx"));
		params.add(new BasicNameValuePair("passwd","xx"));
		int currentNum=0;
		while(currentNum<Fzu.TOTAL)
		{
		    try {
			    currentNum++;
			    doPost(Fzu.TARGET_URL, params);
			    return true;
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			    if(currentNum<Fzu.TOTAL)
			    	e.printStackTrace();
			    else
			    	Log.d(LOG_TAG,"three doPost all fail");
			    
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
			Toast.makeText(this,getString(R.string.loging_fail),Toast.LENGTH_SHORT).show();
			return ;
		}
		
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	
	public String doPost(String url,List<NameValuePair>params) throws Exception
	{
		HttpPost httpRequest=new HttpPost(url);
		String strResult="doPostError";
		
		try {
			httpRequest.addHeader("Referer", Fzu.REFERER);
			httpRequest.setEntity(new UrlEncodedFormEntity(params,"gb2312"));
			HttpResponse httpResponse=MyHttpClient.getInstance().execute(httpRequest);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				strResult=FileUtil.changeInputStream(httpResponse.getEntity().getContent(),"gb2312");
			}else{
				strResult="doPostErrorResponse:"+httpResponse.getStatusLine().getStatusCode();
			}
		} finally{
			Log.d(LOG_TAG,strResult);
			FileUtil.writeFileData("xml.txt",strResult,this);
			
		}
		
		return strResult;
	}

}