package com.example.fzu.task;

import com.example.fzu.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class GeneralTask extends AsyncTask<Void,Void,Boolean>{
	private String mshowText;
	private Context mctx;
	private AsyncTaskListener mAsyncTaskListener;
	private AlertDialog mydialog;
	private TextView dialogText;
	
	public GeneralTask(String showText,Context ctx)
	{
		mshowText=showText;
		mctx=ctx;
		
	}
	
	
	
	public void setTaskListener(AsyncTaskListener asynctasklistener)
	{
		mAsyncTaskListener=asynctasklistener;
		
	}

	protected void onPreExecute(){
		mydialog=new AlertDialog.Builder(mctx).create();
		mydialog.show();
	    mydialog.getWindow().setContentView(R.layout.login_alertdialog);
	    dialogText=(TextView)(mydialog.getWindow().findViewById(R.id.alertdialog_text));
	    dialogText.setText(mshowText);
	    
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		// TODO Auto-generated method stub
		if(mAsyncTaskListener!=null)
			return mAsyncTaskListener.doTaskInBackground();
		return false;
	}
	
	protected void onProgressUpdate(Void...progress)
	{
		if(mAsyncTaskListener!=null)
			mAsyncTaskListener.onUIProgressUpdate();
	}
	
	protected void onPostExecute(Boolean result)
	{
		if(mAsyncTaskListener!=null)
			mAsyncTaskListener.onUIPostExecute(result);
		
		if(mydialog!=null)
		    mydialog.dismiss();
		
	}

    
}
