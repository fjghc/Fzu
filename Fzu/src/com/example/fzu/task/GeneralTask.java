package com.example.fzu.task;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

public class GeneralTask extends AsyncTask<Void,Void,Boolean>{
	private String mshowText;
	private Context mctx;
	private AsyncTaskListener mAsyncTaskListener;
	
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
		new AlertDialog.Builder(mctx).setMessage(mshowText);
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
	}

    
}
