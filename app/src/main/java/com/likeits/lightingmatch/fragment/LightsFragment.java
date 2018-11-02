package com.likeits.lightingmatch.fragment;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.base.BaseFragment;
import com.likeits.lightingmatch.view.ClearEditText;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightsFragment extends BaseFragment {
    private TagFlowLayout mFlowLayout;
    private String[] mVals = new String[]{"全部分类", "水晶灯", "吸顶灯", "中式灯"};
    private TagAdapter<String> adapter;
    private ClearEditText edSearch;

    @Override
    protected int setContentView() {
        return R.layout.fragment_lights;
    }

    @Override
    protected void lazyLoad() {
        // initStatusBar();
        mFlowLayout = findViewById(R.id.id_flowlayout);
        edSearch = findViewById(R.id.ed_search);
        edSearch.setFocusable(true);
        initLayout();
    }

    private void initLayout() {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        adapter = new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tv,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        mFlowLayout.setAdapter(adapter);
//        mFlowLayout.setAdapter(new TagAdapter<String>(mVals)
//        {
//
//            @Override
//            public View getView(FlowLayout parent, int position, String s)
//            {
//                TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tv,
//                        mFlowLayout, false);
//                tv.setText(s);
//                return tv;
//            }
//        });

        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                // Toast.makeText(getActivity(), mVals[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });


        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
        adapter.setSelectedList(1);
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
