package com.likeits.lightingmatch.fragment;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.likeits.lightingmatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends DialogFragment {

    @Override
    public void onResume() {
        if (getActivity().getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scene, container, false);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; //底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        initUI(view);
        return view;
    }


    private void initUI(View view) {
        RelativeLayout rl = view.findViewById(R.id.rl);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rl.getLayoutParams();
        params.width = getActivity().getResources().getDisplayMetrics().widthPixels / 3;
        params.height = getActivity().getResources().getDisplayMetrics().heightPixels;
        rl.setLayoutParams(params);

    }

}
