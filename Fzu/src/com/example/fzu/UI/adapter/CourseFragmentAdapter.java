package com.example.fzu.UI.adapter;

import com.example.fzu.UI.fragment.CourseFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CourseFragmentAdapter extends FragmentPagerAdapter{
    protected static final String[] CONTENT = new String[] { "һ", "��", "��", "��","��","��","��" };
    

    private int mCount = CONTENT.length;

    public CourseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
    	Fragment fragment=new CourseFragment(null);
        return fragment;
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

}
