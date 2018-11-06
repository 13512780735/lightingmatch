package com.likeits.lightingmatch.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.LinearLayout;

import com.likeits.lightingmatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightsDetailsFragment extends DialogFragment {


    private LinearLayout llDialog;

    public LightsDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lights_details, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
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
        params.gravity = Gravity.LEFT;
        params.horizontalMargin=0.1f;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        win.setAttributes(params);
    }
}
