package com.apps4humanity.funny;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.apps4humanity.funny.fragment.Tab1;
import com.apps4humanity.funny.fragment.Tab2;
import com.apps4humanity.funny.fragment.Tab3;
import com.apps4humanity.funny.fragment.Tab4;

/**
 * Created by vedant on 3/27/2018.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Tab1();
        } else if (position == 1){
            return new Tab2();
        } else  {
            return new Tab3();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.tab1);
            case 1:
                return mContext.getString(R.string.tab2);
            case 2:
                return mContext.getString(R.string.tab3);

            default:
                return null;
        }
    }

}