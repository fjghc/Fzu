package com.example.fzu.UI.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.fzu.R;
import com.example.fzu.UI.adapter.CourseAdapter;
import com.example.fzu.entity.SingleCourse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * ClassName:CourseFragment
 * Function: 每日课程界面
 * Reason:	 TODO ADD REASON
 *
 * @author   zdd
 * @version  Ver 1.1
 * @Date	 2013	2013-12-13		下午3:29:32
 */
public class CourseFragment extends Fragment{

    private View view;
    private ListView courseListView;
    private ArrayList<SingleCourse> mSingleCourseList;
    private boolean hasCourse=false;    //某一节是否有课程
    
    //传入本日含有的课程
    public CourseFragment(ArrayList<SingleCourse> singleCourseList) {
    	mSingleCourseList=new ArrayList<SingleCourse>();
        for(int i=1;i<=12;i++){
        	hasCourse=false;
        	if(singleCourseList!=null){
        	  for(SingleCourse singleCourse:singleCourseList){
        		String[] sessions=singleCourse.getCtl().getSessions().split("-");
        		int start=Integer.parseInt(sessions[0]);
        		int end=Integer.parseInt(sessions[1]);
        		if(i>=start&&i<=end){
        			hasCourse=true;
        		    mSingleCourseList.add(singleCourse);
        		    i=end;
        		    break;
        		}
        		
        	  }
        	}
        	if(!hasCourse){
        		SingleCourse emptyCourse=new SingleCourse();
        		emptyCourse.getCtl().setSessions(""+i);
        		mSingleCourseList.add(emptyCourse);
        	}
        }
        	
        

        
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	view=inflater.inflate(R.layout.course_fragment, container, false);
    	courseListView=(ListView)view.findViewById(R.id.course_content_list);
        CourseAdapter mcourseadapter=new CourseAdapter(mSingleCourseList,getActivity());
        courseListView.setAdapter(mcourseadapter);
        return view;
    }

 
}
