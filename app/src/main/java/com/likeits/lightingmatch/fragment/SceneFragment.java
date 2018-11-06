package com.likeits.lightingmatch.fragment;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.adapter.history.SceneHistoryAdapter;
import com.likeits.lightingmatch.base.BaseFragment;
import com.likeits.lightingmatch.interfac.onDataLightsListener;
import com.likeits.lightingmatch.interfac.onDataSceneListener;
import com.likeits.lightingmatch.network.model.CaseEntity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends BaseFragment {
    private ArrayList<CaseEntity> data;
    private SceneHistoryAdapter mAdapter;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private onDataSceneListener listener;

    private TagFlowLayout mFlowLayout;
    private String[] mVals = new String[]{"全部风格", "古典", "简约现代", "中式风格"};
    private TagAdapter<String> adapter;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        //当前fragment从activity重写了回调接口  得到接口的实例化对象
        listener = (onDataSceneListener) activity;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_scene;
    }

    @Override
    protected void lazyLoad() {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        mFlowLayout = findViewById(R.id.id_flowlayout);
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
        initData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        initAdapter();
        mSwipeRefreshLayout.setEnabled(false);
    }

    private void initAdapter() {
        mAdapter = new SceneHistoryAdapter(R.layout.layout_scene_items, data);
        //mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                listener.SendScencValue("1");
            }
        });
    }

    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CaseEntity caseEntity = new CaseEntity();
            caseEntity.setUrl(i + "");
            caseEntity.setId(i + "");
            data.add(caseEntity);
        }
    }
}
