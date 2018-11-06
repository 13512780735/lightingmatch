package com.likeits.lightingmatch.fragment.help;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.base.BaseFragment;
import com.likeits.lightingmatch.interfac.onDataLightsListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperationFragment extends BaseFragment {

    private onDataLightsListener listener;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        //当前fragment从activity重写了回调接口  得到接口的实例化对象
        listener = (onDataLightsListener) activity;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_operation;
    }

    @Override
    protected void lazyLoad() {
        findView(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  listener.SendMessageValue("1");
            }
        });
    }

}
