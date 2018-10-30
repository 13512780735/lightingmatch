package com.likeits.lightingmatch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.likeits.lightingmatch.fragment.CartFragment;
import com.likeits.lightingmatch.fragment.HelpFragment;
import com.likeits.lightingmatch.fragment.HistoryFragment;
import com.likeits.lightingmatch.fragment.LightsFragment;
import com.likeits.lightingmatch.fragment.SceneFragment;
import com.likeits.lightingmatch.uilib.CenterView;
import com.likeits.lightingmatch.uilib.CloseImageView;
import com.likeits.lightingmatch.uilib.DragDynamicView;
import com.likeits.lightingmatch.uilib.DragImageView;
import com.likeits.lightingmatch.uilib.base.ICenterView;
import com.likeits.lightingmatch.uilib.base.IDragView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

public class MainActivity extends XActivity implements SceneFragment.CallBackValue {
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
    @BindView(R.id.drag_control_layout)
    DragDynamicView mDragControlView;

    private SceneFragment sceneFragment;
    private Bundle bundle;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private LightsFragment lightsFragment;
    private CartFragment cartFragment;
    private HistoryFragment historyFragment;
    private HelpFragment helpFragment;
    private CloseImageView closeView;
    private DragImageView dragView;


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
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) fl_content.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        WindowManager wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        linearParams.width = wManager.getDefaultDisplay().getWidth() / 3;// 控件的宽强制设成30
        fl_content.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        fabOnclick();
//        mDragControlView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closeView.setVisibility(View.VISIBLE);
//                dragView.setVisibility(View.VISIBLE);
//            }
//        });
//        mDragControlView.setOnOutSideClickListener(new DragDynamicView.OnOutSideClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                closeView.setVisibility(View.GONE);
//                dragView.setVisibility(View.GONE);
//            }
//        });
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

    @Override
    public void SendMessageValue(String strValue) {
        // ToastUtils.showToast(context,"点击了");
        if ("1".equals(strValue)) {
            addDynamicView();
        } else {
            setDragViewAllEnEdit();
        }
    }


    private void addDynamicView() {
        closeView = (CloseImageView) getLayoutInflater().inflate(R.layout.story_close_view, null);
        closeView.setImageResource(R.mipmap.icon_close);
        closeView.setIndex(String.valueOf(mDragControlView.LEVELS));
        closeView.setVisibility(View.INVISIBLE);
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CloseImageView && mDragControlView != null) {
                    mDragControlView.removeViewByIndex(((CloseImageView) v).getIndex());
                }

            }
        });
        dragView = (DragImageView) getLayoutInflater().inflate(R.layout.story_drag_view, null);
        dragView.setImageResource(R.mipmap.icon_zoom);
        dragView.setIndex(String.valueOf(mDragControlView.LEVELS));
        dragView.setVisibility(View.INVISIBLE);
        final CenterView centerView = (CenterView) getLayoutInflater().inflate(R.layout.story_center_view, null);
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

    //
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View view = getCurrentFocus();
            if (view != fl_content) {
                fl_content.setVisibility(View.GONE);
//                if (closeView != null || dragView != null) {
//                    closeView.setVisibility(View.INVISIBLE);
//                    dragView.setVisibility(View.INVISIBLE);
//                }
            }
        }

        return super.dispatchTouchEvent(ev);
    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        View view = getCurrentFocus();
//        fl_content.setVisibility(View.GONE);
//        return true;
//    }
}
