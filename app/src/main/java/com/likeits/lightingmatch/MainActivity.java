package com.likeits.lightingmatch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.likeits.lightingmatch.fragment.SceneFragment;
import com.likeits.lightingmatch.utils.ToastUtils;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

public class MainActivity extends XActivity {
    @BindView(R.id.rl_fab)
    RelativeLayout rlFab;
    @BindView(R.id.menuFab)
    FloatingActionMenu menuFab;
    @BindView(R.id.fab_1)
    FloatingActionButton fab1;
    @BindView(R.id.fab_2)
    FloatingActionButton fab2;
    @BindView(R.id.fab_3)
    FloatingActionButton fab3;
    @BindView(R.id.fab_4)
    FloatingActionButton fab4;
    @BindView(R.id.fab_5)
    FloatingActionButton fab5;


    @Override
    protected void onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        createCustomAnimation();
        fabOnclick();
    }

    private void fabOnclick() {
        fab5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                ToastUtils.showToast(MainActivity.this, "场景");
//                SceneFragment sceneFragment = new SceneFragment();
//                sceneFragment.show(getSupportFragmentManager(), "场景");
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                ToastUtils.showToast(MainActivity.this, "灯饰");
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                ToastUtils.showToast(MainActivity.this, "购物车");
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                ToastUtils.showToast(MainActivity.this, "历史");
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                ToastUtils.showToast(MainActivity.this, "帮助");
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(menuFab.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(menuFab.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(menuFab.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(menuFab.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                menuFab.getMenuIconView().setImageResource(menuFab.isOpened()
                        ? R.mipmap.icon_tools : R.mipmap.icon_up);
                if (menuFab.isOpened()) {
                    rlFab.setTranslationX(0);
                } else {
                    rlFab.setTranslationX(-190);
                }
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        menuFab.setIconToggleAnimatorSet(set);

    }

    @Override
    public Object newP() {
        return null;
    }

}
