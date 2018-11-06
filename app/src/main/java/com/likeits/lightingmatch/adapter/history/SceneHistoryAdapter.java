package com.likeits.lightingmatch.adapter.history;


import android.content.Context;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeits.lightingmatch.R;
import com.likeits.lightingmatch.network.model.CaseEntity;

import java.util.List;


public class SceneHistoryAdapter extends BaseQuickAdapter<CaseEntity, BaseViewHolder> {

    public SceneHistoryAdapter(int layoutResId, List<CaseEntity> data) {
        super(R.layout.layout_scene_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CaseEntity item) {
        baseViewHolder.setText(R.id.tv_test, item.getId());
//        ivPic.setLayoutParams(params);
    }
}
