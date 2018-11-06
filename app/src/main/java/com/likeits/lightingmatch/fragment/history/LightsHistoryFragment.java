package com.likeits.lightingmatch.fragment.history;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.adapter.history.LightHistoryAdapter;
import com.likeits.lightingmatch.base.BaseFragment;
import com.likeits.lightingmatch.interfac.onDataLightsListener;
import com.likeits.lightingmatch.network.model.CaseEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightsHistoryFragment extends BaseFragment {

    private ArrayList<CaseEntity> data;
    private LightHistoryAdapter mAdapter;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
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
        return R.layout.fragment_lights_history;
    }

    @Override
    protected void lazyLoad() {
        initData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        initAdapter();
        mSwipeRefreshLayout.setEnabled(false);
    }

    private void initAdapter() {
        mAdapter = new LightHistoryAdapter(R.layout.layout_light_items, data);
        //mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                listener.SendLightsValue("1");
            }
        });
    }

    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CaseEntity caseEntity = new CaseEntity();
            caseEntity.setUrl(i + "");
            caseEntity.setId(i+"");
            data.add(caseEntity);
        }
    }
}
