package com.likeits.lightingmatch.fragment;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.base.BaseFragment;
import com.likeits.lightingmatch.interfac.onDataLightsListener;

import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends BaseFragment {

    private onDataLightsListener listener;
    private SearchView searchView;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        //当前fragment从activity重写了回调接口  得到接口的实例化对象
       listener = (onDataLightsListener) activity;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_scene;
    }

    @Override
    protected void lazyLoad() {
        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
               // System.out.println("我收到了" + string);
            }
        });
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();
//                listener.SendMessageValue("1");
//            }
//        });
    }

}
