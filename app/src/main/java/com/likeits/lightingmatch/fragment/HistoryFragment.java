package com.likeits.lightingmatch.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.adapter.HelpTabAdapter;
import com.likeits.lightingmatch.base.BaseFragment;
import com.likeits.lightingmatch.fragment.history.LightsHistoryFragment;
import com.likeits.lightingmatch.fragment.history.MyPlanFragment;
import com.likeits.lightingmatch.fragment.history.SceneHistoryFragment;
import com.likeits.lightingmatch.view.NoScrollViewPager;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends BaseFragment {
    private ArrayList<Fragment> mList = new ArrayList<Fragment>();
    private RadioGroup mRG;
    private NoScrollViewPager mViewPager;
    private HelpTabAdapter adapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_history;
    }

    @Override
    protected void lazyLoad() {
        mRG = findViewById(R.id.RadioGroup);
        mViewPager = findViewById(R.id.viewpager);
        LightsHistoryFragment lightsHistoryFragment = new LightsHistoryFragment();
        SceneHistoryFragment sceneHistoryFragment = new SceneHistoryFragment();
        MyPlanFragment myPlanFragment = new MyPlanFragment();
        mList.add(lightsHistoryFragment);
        mList.add(sceneHistoryFragment);
        mList.add(myPlanFragment);
        adapter = new HelpTabAdapter(getChildFragmentManager(), mList);
        mViewPager.setAdapter(adapter);
        mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton) mRG.getChildAt(position);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
