package com.likeits.lightingmatch.fragment.history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.adapter.history.LightHistoryAdapter;
import com.likeits.lightingmatch.adapter.history.SceneHistoryAdapter;
import com.likeits.lightingmatch.base.BaseFragment;
import com.likeits.lightingmatch.network.model.CaseEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneHistoryFragment extends BaseFragment {
    private ArrayList<CaseEntity> data;
    private SceneHistoryAdapter mAdapter;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected int setContentView() {
        return R.layout.fragment_scene_history;
    }

    @Override
    protected void lazyLoad() {
        initData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        initAdapter();
        mSwipeRefreshLayout.setEnabled(false);
    }

    private void initAdapter() {
        mAdapter = new SceneHistoryAdapter(R.layout.layout_scene_items, data);
        //mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
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
