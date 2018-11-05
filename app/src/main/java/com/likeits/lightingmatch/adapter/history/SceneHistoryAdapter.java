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
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        baseViewHolder.setText(R.id.tv_test, item.getId());
//        ImageView ivPic = baseViewHolder.getView(R.id.iv_pic);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivPic.getLayoutParams();
//        params.width = width / 2 - 20;
//        ivPic.setLayoutParams(params);
    }
}
