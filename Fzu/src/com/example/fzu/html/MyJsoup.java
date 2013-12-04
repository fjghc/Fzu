/**
 * MyJsoup.java
 * com.example.fzu.html
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-12-4 		zdd
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
*/

package com.example.fzu.html;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.util.Log;

import com.example.fzu.entity.Course;

/**
 * ClassName:MyJsoup
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @since    Ver 1.1
 * @Date	 2013-12-4		下午4:26:38
 */
public class MyJsoup {
	private static final String LOG_TAG="MyJsoup";
	
	
	public static ArrayList<Course> parserCourse(String filePath,Context ctx){
		ArrayList<Course> courses=new ArrayList<Course>();
		try {
		    InputStream in=ctx.openFileInput(filePath);

			Document doc=Jsoup.parse(in,"UTF-8","");
			Elements trs=doc.getElementsByTag("tr").select("[bgcolor~=[e]{6}|[f]{6}]");
			for(Element tr:trs){
				String temp=tr.text();
				System.out.println(temp);
				Course cs=new Course();
                cs.setType(tr.children().get(0).text());
                cs.setName(tr.children().get(1).text());
                cs.setPayment(tr.children().get(2).text());
                cs.setCredit(tr.children().get(3).text());
                cs.setElectiveType(tr.children().get(4).text());
                cs.setExamType(tr.children().get(5).text());
				cs.setTeacher(tr.children().get(6).text());
				cs.setCtls(cs.parserStringToCourseTimeLocation(tr.children().get(7).text().replace(Jsoup.parse("&nbsp").text(), "!")));
                cs.setExamtime(tr.children().get(8).text());
                cs.setRemark(tr.children().get(9).text());
                cs.setInformationTransferCourses(tr.children().get(10).text());
                cs.setApplyForSlowExam(tr.children().get(11).text());
                Log.d(LOG_TAG,cs.toString());
                courses.add(cs);
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
		
	}

}

