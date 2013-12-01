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
    private String name;       //课程名字
    private String teacher;    //任课教师
    private List<CourseTimeLocation> ctls;    //上课时间、地点
    private String examtime;   //考试时间
    
    
    private String type;       //专业类别
    private String payment;    //缴费情况
    private String credit;     //学分
    private String electiveType;  //选修类型
    private String examType;      //考试类型
    
}

