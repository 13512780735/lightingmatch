package com.likeits.lightingmatch.fragment;


import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likeits.lightingmatch.R;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShotScreenFragment extends DialogFragment {


    private ImageView ivShotScreen;
    private TextView tvCancel, tvSave;
    private Bitmap bitmap;
    private LinearLayout llBottom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shot_screen, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            bitmap = bundle.getParcelable("bitmap");
        }
        initUI(view);
        return view;
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
        params.gravity = Gravity.CENTER;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(params);
    }

    private void initUI(View view) {
        llBottom = view.findViewById(R.id.ll_bottom);
        ivShotScreen = view.findViewById(R.id.iv_shotScreen);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llBottom.getLayoutParams();
//        params.width = ivShotScreen.getWidth();
//        params.height = 50;
//        llBottom.setLayoutParams(params);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSave = view.findViewById(R.id.tv_save);
        ivShotScreen.setImageBitmap(bitmap);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

}
