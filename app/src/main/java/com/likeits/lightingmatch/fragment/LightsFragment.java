package com.likeits.lightingmatch.fragment;


import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.adapter.history.LightHistoryAdapter;
import com.likeits.lightingmatch.base.BaseFragment;
import com.likeits.lightingmatch.interfac.onDataLightsListener;
import com.likeits.lightingmatch.interfac.onDataSceneListener;
import com.likeits.lightingmatch.network.model.CaseEntity;
import com.likeits.lightingmatch.view.ClearEditText;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightsFragment extends BaseFragment {
    private TagFlowLayout mFlowLayout;
    private String[] mVals = new String[]{"全部分类", "水晶灯", "吸顶灯", "中式灯"};
    private TagAdapter<String> adapter;
    private ClearEditText edSearch;

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
        return R.layout.fragment_lights;
    }

    @Override
    protected void lazyLoad() {
        // initStatusBar();
        mFlowLayout = findViewById(R.id.id_flowlayout);
        edSearch = findViewById(R.id.ed_search);
        edSearch.setFocusable(true);
        initLayout();

        initData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new LightHistoryAdapter(R.layout.layout_light_items, data);
        //mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setEnabled(false);
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
            caseEntity.setId(i + "");
            data.add(caseEntity);
        }
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


}
