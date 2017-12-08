package cn.xufucun.udacity.gnews;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.xufucun.udacity.gnews.fragment.LocationFragment;
import cn.xufucun.udacity.gnews.fragment.PersonageFragment;
import cn.xufucun.udacity.gnews.fragment.ThemeFragment;

/**
 * Created by xufuc on 2017/11/22.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private static int PAGE_COUNT = 3;


    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ThemeFragment();
        } else if (position == 1) {
            return new PersonageFragment();
        } else {
            return new LocationFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_theme);
        } else if (position == 1) {
            return mContext.getString(R.string.category_personage);
        } else{
            return mContext.getString(R.string.category_location);
        }
    }
}
