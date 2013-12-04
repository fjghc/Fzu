/**
 * Course.java
 * com.example.fzu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-11-29 		zdd
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
*/

package com.example.fzu.entity;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:Course
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @version  Ver 1.1
 * @Date	 2013	2013-11-29		下午8:14:48
 */
public class Course {
    private String type;       //专业类别
    private String name;       //课程名字
    private String payment;    //缴费情况
    private String credit;     //学分
    private String electiveType;  //选修类型
    private String examType;      //考试类型
    private String teacher;    //任课教师
    private List<CourseTimeLocation> ctls;    //上课时间、地点
    private String examtime;   //考试时间
    private String remark;     //备注
    private String informationTransferCourses; //调课信息
    private String applyForSlowExam;           //申请缓考
    
    
    
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "专业类别："+type+"\n课程名字："+name+"\n缴费情况："+payment+"\n学分："+credit
				+"\n选修类型："+electiveType+"\n考试类型："+examType+"\n任课教师："+teacher
				+"\n上课时间和地点："+ctls.get(0)+"\n考试时间："+examtime+"\n备注："+remark
				+"\n调课信息："+informationTransferCourses+"\n申请缓考："+applyForSlowExam+"\n";
	}

	public ArrayList<CourseTimeLocation> parserStringToCourseTimeLocation(String timeLocation)
    {
    	ArrayList<CourseTimeLocation> ctls=new ArrayList<CourseTimeLocation>();
    	String [] temp=timeLocation.split(" ");
    	for(int i=0;i<temp.length;i++){
    		CourseTimeLocation ctl=new CourseTimeLocation();
    		String[] tmp=temp[i].split("!");
    		ctl.setWeekrange(tmp[0]);
    		ctl.setWeekdaySessionsOddweek(tmp[1]);
    		ctl.setLocation(tmp[2]);
    		ctls.add(ctl);
    	}
    	return ctls;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getElectiveType() {
		return electiveType;
	}

	public void setElectiveType(String electiveType) {
		this.electiveType = electiveType;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public List<CourseTimeLocation> getCtls() {
		return ctls;
	}

	public void setCtls(List<CourseTimeLocation> ctls) {
		this.ctls = ctls;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInformationTransferCourses() {
		return informationTransferCourses;
	}

	public void setInformationTransferCourses(String informationTransferCourses) {
		this.informationTransferCourses = informationTransferCourses;
	}

	public String getApplyForSlowExam() {
		return applyForSlowExam;
	}

	public void setApplyForSlowExam(String applyForSlowExam) {
		this.applyForSlowExam = applyForSlowExam;
	}

	public String getExamtime() {
		return examtime;
	}

	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}



    
}

