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
    private String name;
    private String teacher;
    private List<CourseTimeLocation> ctls; 
    private String examtime;   //考试时间
}

