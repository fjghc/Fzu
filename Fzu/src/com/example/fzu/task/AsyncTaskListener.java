package com.example.fzu.task;

public interface AsyncTaskListener {
	
	public boolean doTaskInBackground();
	public void onUIProgressUpdate();
	public void onUIPostExecute(boolean isSuccess);
}
