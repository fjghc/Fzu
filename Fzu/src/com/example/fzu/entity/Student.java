package com.example.fzu.entity;

public class Student {
	
	private String mSid;      //Ñ§ºÅ
	private String mName;     //Ãû×Ö
	private String mPasswd;   //ÃÜÂë
	
	public void setSid(String sid)
	{
		this.mSid=sid;
	}

	public String getSid()
	{
		return mSid;
	}
	
	public void setName(String name)
	{
		this.mName=name;
	}
	
	public String getName()
	{
		return this.mName;
	}
	
	public void setPasswd(String passwd)
	{
		this.mPasswd=passwd;
	}
	
	public String getPasswd()
	{
		return this.mPasswd;
	}

}
