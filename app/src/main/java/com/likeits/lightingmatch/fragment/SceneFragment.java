package com.likeits.lightingmatch.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.likeits.lightingmatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scene, container, false);
        //initUI(view);
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

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // 一定要设置Background，如果不设置，window属性设置无效
//         win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        WindowManager.LayoutParams params = win.getAttributes();
//        params.dimAmount = 0f;
//        params.gravity = Gravity.RIGHT;
//        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
//        params.width = 300;
//        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
//        win.setAttributes(params);
//    }

    private void initUI(View view) {

    }

}
