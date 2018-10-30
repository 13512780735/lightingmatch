package com.likeits.lightingmatch.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends BaseFragment {

    private CallBackValue callBackValue;
    private Button tv;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        //当前fragment从activity重写了回调接口  得到接口的实例化对象
        callBackValue = (CallBackValue) getActivity();
    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_scene;
    }

    @Override
    protected void lazyLoad() {
        tv = findViewById(R.id.tv_test);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();
                callBackValue.SendMessageValue("1");
            }
        });
    }

    //定义一个回调接口
    public interface CallBackValue {
        void SendMessageValue(String strValue);
    }
}
