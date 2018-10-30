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
import com.likeits.lightingmatch.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment {


    @Override
    protected int setContentView() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void lazyLoad() {
        initStatusBar();
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


}
