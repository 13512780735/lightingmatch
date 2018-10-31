package com.likeits.lightingmatch.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.adapter.ContainerViewPagerAdapter;
import com.likeits.lightingmatch.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerFragment extends DialogFragment {


    private NoScrollViewPager mViewPager;
    private int data01;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        initStatusBar();
        Bundle bundle = getArguments();
        if (bundle != null) {
            data01 = bundle.getInt("flag");
        }
       initUI(view);

        return view;
    }

    /**
     * 初始化沉浸式状态栏
     */
    private void initStatusBar() {
        //设置是否沉浸式
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        int flag_translucent_status = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        //透明状态栏
        getActivity().getWindow().setFlags(flag_translucent_status, flag_translucent_status);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();

        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams params = win.getAttributes();
        params.dimAmount = 0f;
        params.gravity = Gravity.RIGHT;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = 300;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        win.setAttributes(params);
    }

    private void initUI(View view) {
      mViewPager = view.findViewById(R.id.viewpager);
        List<Fragment> mfragments = new ArrayList<Fragment>();
        mfragments.add(new SceneFragment());
        mfragments.add(new LightsFragment());
        mViewPager.setAdapter(new ContainerViewPagerAdapter(getChildFragmentManager(), mfragments));
        if (data01 == 1) {
            mViewPager.setCurrentItem(0);
        } else if (data01 == 2) {
            mViewPager.setCurrentItem(1);
        }
    }

}
