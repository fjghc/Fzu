/**
 * CourseTimeLocation.java
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
/**
 * ClassName:CourseTimeLocation
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @since    Ver 1.1
 * @Date	 2013-11-29		下午8:24:10
 */
public class CourseTimeLocation {
	private String id;
    private String time;          //08-14 星期5:3-4节(双)
    private String weekrange;     //08-14
    private String weekday;       //星期5
    private String sessions;      //3-4   -->节数
    private int oddweek;          //单双周  0--无  1--单周  2--双周
    private String location;      //地点
    
    
    public String toString(){
    	
    	return "weekrange->"+weekrange+"   weekday->"+weekday+"   sessions->"+sessions+"   oddweek->"+oddweek+"   location->"+location;
    };
    
    
	public String getWeekrange() {
		return weekrange;
	}
	public void setWeekrange(String weekrange) {
		this.weekrange = weekrange;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekdaySessionsOddweek(String weekdaySessionOddweek) {
		String [] temp=weekdaySessionOddweek.split(":");
		weekday=temp[0];
		if(temp[1].contains("(")&&temp[1].contains(")")){
			String[] tmp=temp[1].split("\\(");
			sessions=tmp[0].substring(0, tmp[0].length()-1);
			if(tmp[1].equals("单)"))    //split-->()需要转义<由于内部还有正则所以是双重转义>   equals不需要
				oddweek=1;
			else if(tmp[1].equals("双)"))
				oddweek=2;
			else
				oddweek=0;
		}else{
			oddweek=0;
			sessions=temp[1].substring(0, temp[1].length()-1);
		}
			
		
	}
	

	
	public String getSessions() {
		return sessions;
	}
	public String setSessions(String mSessions) {
		return sessions=mSessions;
	}
	
	public int getOddweek() {
		return oddweek;
	}
	public void setOddweek(int oddweek) {
		this.oddweek = oddweek;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}

