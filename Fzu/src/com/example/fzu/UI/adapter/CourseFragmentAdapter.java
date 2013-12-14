package com.example.fzu.UI.adapter;

import java.util.ArrayList;

import com.example.fzu.UI.fragment.CourseFragment;
import com.example.fzu.entity.SingleCourse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class CourseFragmentAdapter extends FragmentPagerAdapter{
    protected static final String[] CONTENT = new String[] { "һ", "��", "��", "��","��","��","��" };
    private ArrayList<SingleCourse> currentWeekCourseList=new ArrayList<SingleCourse>();
    private ArrayList<SingleCourse> todayCourseList=new ArrayList<SingleCourse>();
    private int currentTab;
    private int mCount = CONTENT.length;

    public CourseFragmentAdapter(FragmentManager fm) {
        super(fm);
        
    }

    @Override
    public Fragment getItem(int position) {
    	currentTab=position;
    	getTodayCourseList(currentWeekCourseList);
    	Fragment fragment=new CourseFragment(todayCourseList);
        return fragment;
    }
    
    public void setData(ArrayList<SingleCourse> singleCourseList){
    	currentWeekCourseList=singleCourseList;
    }
    
    

    @Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
    	CourseFragment courseFragment=(CourseFragment)super.instantiateItem(container, position);
    	currentTab=position;
    	getTodayCourseList(currentWeekCourseList);
    	courseFragment.setData(todayCourseList);
		return courseFragment;
		
	}

	@Override
    public int getCount() {
        return mCount;
    }

    public CharSequence getPageTitle(int position) {
      return CourseFragmentAdapter.CONTENT[position % CONTENT.length];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
    
    public void getTodayCourseList(ArrayList<SingleCourse> mCurrentWeekCourseList){
    	todayCourseList.clear();
    	for(SingleCourse singleCourse:mCurrentWeekCourseList){
    		if(singleCourse.getCtl().getWeekday().equals("星期"+(currentTab+1))){
    			todayCourseList.add(singleCourse);
    		}
    		
    	}

    }

}
