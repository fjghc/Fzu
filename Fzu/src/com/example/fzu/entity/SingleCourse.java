package com.example.fzu.entity;

import java.util.ArrayList;
import java.util.List;

//界面展示课程类
public class SingleCourse {
	
    private String type;       //专业类别
    private String name;       //课程名字
    private String payment;    //缴费情况
    private String credit;     //学分
    private String electiveType;  //选修类型
    private String examType;      //考试类型
    private String teacher;    //任课教师
    private CourseTimeLocation ctl=new CourseTimeLocation();    //上课时间、地点
    private String examtime;   //考试时间
    private String remark;     //备注
    private String informationTransferCourses; //调课信息
    private String applyForSlowExam;           //申请缓考
    
    
    
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "专业类别："+type+"\n课程名字："+name+"\n缴费情况："+payment+"\n学分："+credit
				+"\n选修类型："+electiveType+"\n考试类型："+examType+"\n任课教师："+teacher
				+"\n上课时间和地点："+ctl+"\n考试时间："+examtime+"\n备注："+remark
				+"\n调课信息："+informationTransferCourses+"\n申请缓考："+applyForSlowExam+"\n";
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

	public CourseTimeLocation getCtl() {
		return ctl;
	}

	public void setCtl(CourseTimeLocation ctls) {
		this.ctl = ctl;
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
