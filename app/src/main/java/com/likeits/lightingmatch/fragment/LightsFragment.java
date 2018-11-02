package com.likeits.lightingmatch.fragment;


import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.base.BaseFragment;

import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightsFragment extends BaseFragment {
    private SearchView searchView;

    @Override
    protected int setContentView() {
        return R.layout.fragment_lights;
    }

    @Override
    protected void lazyLoad() {
       // initStatusBar();
        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                // System.out.println("我收到了" + string);
            }
        });
    }

//    /**
//     * 初始化沉浸式状态栏
//     */
//    private void initStatusBar() {
//        //设置是否沉浸式
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
//        int flag_translucent_status = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        //透明状态栏
//        getActivity().getWindow().setFlags(flag_translucent_status, flag_translucent_status);
//    }


}
