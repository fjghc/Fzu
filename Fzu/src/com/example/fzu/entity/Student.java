package com.example.fzu.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private String mSid;      //学号
	private String mName;     //姓名
	private String mPasswd;   //密码
	private List<Course> mcourses;
	
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
	
	public void setCourses(ArrayList<Course> courses)
	{
		mcourses=courses;
	}
	
	public List<Course> getCourses()
	{
		return mcourses;
	}

}
