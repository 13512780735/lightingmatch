package com.likeits.lightingmatch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.guoqi.actionsheet.ActionSheet;
import com.likeits.lightingmatch.fragment.CartFragment;
import com.likeits.lightingmatch.fragment.HelpFragment;
import com.likeits.lightingmatch.fragment.HistoryFragment;
import com.likeits.lightingmatch.fragment.LightsFragment;
import com.likeits.lightingmatch.fragment.SceneFragment;
import com.likeits.lightingmatch.fragment.ShotScreenFragment;
import com.likeits.lightingmatch.utils.photo.PhotoUtils;
import com.likeits.lightingmatch.view.uilib.CenterView;
import com.likeits.lightingmatch.view.uilib.CloseImageView;
import com.likeits.lightingmatch.view.uilib.DragDynamicView;
import com.likeits.lightingmatch.view.uilib.DragImageView;
import com.likeits.lightingmatch.view.uilib.base.ICenterView;
import com.likeits.lightingmatch.view.uilib.base.IDragView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements SceneFragment.CallBackValue, View.OnClickListener, DragDynamicView.OnOutSideClickListener, ActionSheet.OnActionSheetSelected, EasyPermissions.PermissionCallbacks {
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
    TextView tv_back;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.fr_screen)
    FrameLayout fr_screen;

    private SceneFragment sceneFragment;
    private Bundle bundle;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private LightsFragment lightsFragment;
    private CartFragment cartFragment;
    private HistoryFragment historyFragment;
    private HelpFragment helpFragment;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PhotoUtils.getInstance().init(this, true, new PhotoUtils.OnSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
                Log.d("TAG11",outputFile.getAbsolutePath());
                //  Bitmap img = BitmapFactory.decodeFile(outputFile.getAbsolutePath());
                // mTvPath.setText(outputFile.getAbsolutePath());
                //fr_screen.setBackground(Drawable.createFromPath(outputFile.getAbsolutePath()));
                Toast.makeText(MainActivity.this,outputFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
            }
        });
        mDragControlView.setOnOutSideClickListener(this);
        tv_back.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        ivCamera.setOnClickListener(this);
        createCustomAnimation();
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) fl_content.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        WindowManager wManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        linearParams.width = wManager.getDefaultDisplay().getWidth() / 3;// 控件的宽强制设成30
        fl_content.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        fabOnclick();
    }

    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
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
        tv_back.performClick();
        fr_screen.setBackground(getResources().getDrawable(R.mipmap.icon_ceiling_lamp01));
    }

    @Override
    public void onClick(View v) {
        if (v == tv_back) {
            addDynamicView();
            fl_content.setVisibility(View.GONE);
        } else {
            setDragViewAllEnEdit();
            fl_content.setVisibility(View.GONE);
        }
        if (v == ivSave) {
            getWindow().getDecorView().setDrawingCacheEnabled(false);
            findViewById(R.id.frameLayout_bottom).setVisibility(View.GONE);
            findViewById(R.id.rl_fab).setVisibility(View.GONE);
            showDialog(captureScreenWindow());
            findViewById(R.id.frameLayout_bottom).setVisibility(View.VISIBLE);
            findViewById(R.id.rl_fab).setVisibility(View.VISIBLE);
        }
        if (v == ivCamera) {
            ActionSheet.showSheet(this, this, null);
        }

    }

    /**
     * 截取全屏
     *
     * @return
     */
    public Bitmap captureScreenWindow() {
        getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = getWindow().getDecorView().getDrawingCache();
        return bmp;
    }

    private void showDialog(Bitmap bitmap) {
        ShotScreenFragment shotScreenFragment = new ShotScreenFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bitmap", bitmap);
        shotScreenFragment.setArguments(bundle);
        shotScreenFragment.show(getSupportFragmentManager(), "shot");
    }

    String[] takePhotoPerms = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA};
    String[] selectPhotoPerms = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};

    @Override
    public void onClick(int whichButton) {
        switch (whichButton) {
            case ActionSheet.CHOOSE_PICTURE:
                //相册
                checkPermission(selectPhotoPerms, 2);
                break;
            case ActionSheet.TAKE_PICTURE:
                //拍照
                checkPermission(takePhotoPerms, 1);
                break;
            case ActionSheet.CANCEL:
                //取消
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtils.getInstance().bindForResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void checkPermission(String[] perms, int requestCode) {
        if (EasyPermissions.hasPermissions(this, perms)) {//已经有权限了
            switch (requestCode) {
                case 1:
                    PhotoUtils.getInstance().takePhoto();
                    break;
                case 2:
                    PhotoUtils.getInstance().selectPhoto();
                    break;
            }
        } else {//没有权限去请求
            EasyPermissions.requestPermissions(this, "权限", requestCode, perms);
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {//设置成功
        switch (requestCode) {
            case 1:
                PhotoUtils.getInstance().takePhoto();
                break;
            case 2:
                PhotoUtils.getInstance().selectPhoto();
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("权限设置")
                    .setPositiveButton("设置")
                    .setRationale("当前应用缺少必要权限,可能会造成部分功能受影响！请点击\"设置\"-\"权限\"-打开所需权限。最后点击两次后退按钮，即可返回")
                    .build()
                    .show();
        }
    }
}
