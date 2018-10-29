package com.likeits.lightingmatch.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class ContainerViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mfragments;

    public ContainerViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentlists) {
        super(fm);
        this.mfragments = fragmentlists;
    }


    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position);
    }

//    //此方法用来显示tab上的名字
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return list_Title.get(position % list_Title.size());
//    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}

