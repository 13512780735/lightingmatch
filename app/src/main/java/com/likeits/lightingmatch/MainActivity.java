package com.likeits.lightingmatch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.likeits.lightingmatch.fragment.CartFragment;
import com.likeits.lightingmatch.fragment.HelpFragment;
import com.likeits.lightingmatch.fragment.HistoryFragment;
import com.likeits.lightingmatch.fragment.LightsFragment;
import com.likeits.lightingmatch.fragment.SceneFragment;
import com.likeits.lightingmatch.fragment.ShotScreenFragment;
import com.likeits.lightingmatch.view.uilib.CenterView;
import com.likeits.lightingmatch.view.uilib.CloseImageView;
import com.likeits.lightingmatch.view.uilib.DragDynamicView;
import com.likeits.lightingmatch.view.uilib.DragImageView;
import com.likeits.lightingmatch.view.uilib.base.ICenterView;
import com.likeits.lightingmatch.view.uilib.base.IDragView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

public class MainActivity extends XActivity implements SceneFragment.CallBackValue, DragDynamicView.OnOutSideClickListener, View.OnClickListener {
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
    @BindView(R.id.fl_content)
    RelativeLayout fl_content;
    @BindView(R.id.rl_right)
    RelativeLayout rl_right;
    @BindView(R.id.drag_control_layout)
    DragDynamicView mDragControlView;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.frameLayout_bottom)
    RelativeLayout flBottom;

    private SceneFragment sceneFragment;
    private Bundle bundle;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private LightsFragment lightsFragment;
    private CartFragment cartFragment;
    private HistoryFragment historyFragment;
    private HelpFragment helpFragment;


    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ivSave.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        ivCamera.setOnClickListener(this);
        mDragControlView.setOnOutSideClickListener(this);
        createCustomAnimation();
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) fl_content.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        WindowManager wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        linearParams.width = wManager.getDefaultDisplay().getWidth() / 3;// 控件的宽强制设成30
        fl_content.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        fabOnclick();
    }


    private void fabOnclick() {
        /**
         * 拿到事务管理器并开启事务
         */

        fab5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fl_content.setVisibility(View.VISIBLE);
                if (fl_content.getVisibility() == View.VISIBLE) {
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    hideFragment(transaction);
                    sceneFragment = new SceneFragment();
                    transaction.replace(R.id.fl_content, sceneFragment);
                    transaction.commit();
                }

            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fl_content.setVisibility(View.VISIBLE);
                if (fl_content.getVisibility() == View.VISIBLE) {
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    hideFragment(transaction);
                    lightsFragment = new LightsFragment();
                    transaction.replace(R.id.fl_content, lightsFragment);
                    transaction.commit();

                }
            }
        });
        fab3.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                fl_content.setVisibility(View.VISIBLE);
                if (fl_content.getVisibility() == View.VISIBLE) {
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    hideFragment(transaction);
                    cartFragment = new CartFragment();
                    transaction.replace(R.id.fl_content, cartFragment);
                    transaction.commit();

                }
            }
        });
        fab2.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                fl_content.setVisibility(View.VISIBLE);
                if (fl_content.getVisibility() == View.VISIBLE) {
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    hideFragment(transaction);
                    historyFragment = new HistoryFragment();
                    transaction.replace(R.id.fl_content, historyFragment);
                    transaction.commit();

                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                fl_content.setVisibility(View.VISIBLE);
                if (fl_content.getVisibility() == View.VISIBLE) {
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    hideFragment(transaction);
                    helpFragment = new HelpFragment();
                    transaction.replace(R.id.fl_content, helpFragment);
                    transaction.commit();

                }
            }
        });

    }

    /*
     * 去除（隐藏）所有的Fragment
     * */
    private void hideFragment(FragmentTransaction transaction) {
        if (sceneFragment != null) {
            //transaction.hide(f1);隐藏方法也可以实现同样的效果，不过我一般使用去除
            transaction.remove(sceneFragment);
        }
        if (lightsFragment != null) {
            //transaction.hide(f2);
            transaction.remove(lightsFragment);
        }
        if (cartFragment != null) {
            //transaction.hide(f2);
            transaction.remove(cartFragment);
        }
        if (historyFragment != null) {
            //transaction.hide(f2);
            transaction.remove(historyFragment);
        }
        if (helpFragment != null) {
            //transaction.hide(f2);
            transaction.remove(helpFragment);
        }

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
                    fl_content.setVisibility(View.GONE);
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


    private void addDynamicView() {
        CloseImageView closeView = (CloseImageView) getLayoutInflater().inflate(R.layout.story_close_view, null);
        closeView.setImageResource(R.mipmap.icon_close);
        closeView.setIndex(String.valueOf(mDragControlView.LEVELS));
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CloseImageView && mDragControlView != null) {
                    mDragControlView.removeViewByIndex(((CloseImageView) v).getIndex());
                }

            }
        });
        DragImageView dragView = (DragImageView) getLayoutInflater().inflate(R.layout.story_drag_view, null);
        dragView.setImageResource(R.mipmap.icon_zoom);
        dragView.setIndex(String.valueOf(mDragControlView.LEVELS));
        CenterView centerView = (CenterView) getLayoutInflater().inflate(R.layout.story_center_view, null);
        ImageView centerImageView = (ImageView) centerView.findViewById(R.id.center_pic);
        centerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewParent parent = v.getParent();
                if (parent instanceof CenterView) {
                    CenterView parentView = (CenterView) parent;
                    parentView.setIsEdit(true);
                    if (mDragControlView != null) {
                        mDragControlView.refreshView();
                    }
                }

            }
        });
        centerView.setIsEdit(true);
        centerView.setBitmap(null);
        centerImageView.setImageResource(R.mipmap.icon_ceiling_lamp01);
        centerView.setIndex(String.valueOf(mDragControlView.LEVELS));
        mDragControlView.LEVELS++;
        mDragControlView.addView(centerView);
        mDragControlView.addView(closeView);
        mDragControlView.addView(dragView);
    }

    private void setDragViewAllEnEdit() {
        if (mDragControlView != null && mDragControlView.getAllViews() != null) {
            for (int i = 0; i < mDragControlView.getAllViews().size(); i++) {
                IDragView view = mDragControlView.getAllViews().get(i);
                if (view instanceof ICenterView) {
                    ((ICenterView) view).setIsEdit(false);
                }
            }
            mDragControlView.refreshView();
        }
    }

    /**
     * 灯饰回调
     *
     * @param strValue
     */
    @Override
    public void SendMessageValue(String strValue) {
        // ToastUtils.showToast(context,"点击了");
        tvBack.performClick();

    }

    @Override
    public void onClick(View view) {
        if (view == tvBack) {
            addDynamicView();
            fl_content.setVisibility(View.GONE);
        } else {
            setDragViewAllEnEdit();
            fl_content.setVisibility(View.GONE);
        }
        if (view == ivSave) {
            getWindow().getDecorView().setDrawingCacheEnabled(false);
            flBottom.setVisibility(View.GONE);
            rlFab.setVisibility(View.GONE);
            showDialog(captureScreen());
            flBottom.setVisibility(View.VISIBLE);
            rlFab.setVisibility(View.VISIBLE);
        }
        if(view==ivCamera){

        }
    }

    private void showDialog(Bitmap bitmap) {
        ShotScreenFragment shotScreenFragment = new ShotScreenFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bitmap", bitmap);
        shotScreenFragment.setArguments(bundle);
        shotScreenFragment.show(getSupportFragmentManager(), "shot");
    }

    public Bitmap captureScreen() {
        getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = getWindow().getDecorView().getDrawingCache();
        return bmp;
    }
}
