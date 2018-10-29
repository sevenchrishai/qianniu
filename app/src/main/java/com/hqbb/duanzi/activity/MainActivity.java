package com.hqbb.duanzi.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseActivity;
import com.hqbb.duanzi.base.BaseFragment;
import com.hqbb.duanzi.base.Constant;
import com.hqbb.duanzi.fragment.DuanziFragment;
import com.hqbb.duanzi.fragment.GanhuoFragment;
import com.hqbb.duanzi.fragment.NewsFragment;
import com.hqbb.duanzi.fragment.ZhuishuFragment;
import com.hqbb.duanzi.presenter.MainActivityPresenter;
import com.hqbb.duanzi.util.ToastUtils;

import javax.inject.Inject;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class MainActivity extends BaseActivity{

    private BaseFragment currentFragment;
    private DuanziFragment mDuanziFragment;
    private NewsFragment mNewsFragment;
    private GanhuoFragment mGanhuoFragment;
    private ZhuishuFragment mZhuishuFragment;
    private RelativeLayout layoutDuanzi, layoutNews, layoutGanhuo, layoutZhuishu;
    private TextView tvDuanzi, tvNews, tvGanhuo, tvZhuishu;
    private ImageView imgDuanzi, imgNews, imgGanhuo, imgZhuishu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        requestExternalPermission();
    }

    //主动获取权限
    private void requestExternalPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(mContext,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mContext,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mContext,Manifest.permission.WRITE_SETTINGS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_SETTINGS},
                        Constant.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If make is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    ToastUtils.showToast("请打开写入SD卡权限");
                    return;
                }
                break;
            }
            case Constant.PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                // If make is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    ToastUtils.showToast("请打开读取设备权限");
                    return;
                }
                break;
            }
            case Constant.PERMISSIONS_WRITE_SETTINGS: {
                // If make is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    ToastUtils.showToast("请打开系统设置权限");
                    return;
                }
                break;
            }
        }
    }

    private void initView() {
        layoutDuanzi = findViewById(R.id.layout_duanzi);
        tvDuanzi = findViewById(R.id.tv_duanzi);
        imgDuanzi = findViewById(R.id.img_duanzi);

        layoutNews = findViewById(R.id.layout_news);
        tvNews = findViewById(R.id.tv_news);
        imgNews = findViewById(R.id.img_news);

        layoutGanhuo = findViewById(R.id.layout_ganhuo);
        tvGanhuo = findViewById(R.id.tv_ganhuo);
        imgGanhuo = findViewById(R.id.img_ganhuo);

        layoutZhuishu = findViewById(R.id.layout_zhuishu);
        tvZhuishu = findViewById(R.id.tv_zhuishu);
        imgZhuishu = findViewById(R.id.img_zhuishu);

        if (mZhuishuFragment == null) {
            mZhuishuFragment = new ZhuishuFragment();
        }
        initShowFirstFragment();

    }

    /**
     * 初始化第一个fragment
     */
    private void initShowFirstFragment() {
        this.currentFragment = mZhuishuFragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main, this.currentFragment).commitAllowingStateLoss();
    }

    public void bottomItemOnClick(View v) {
        BaseFragment fragment = null;
        switch (v.getId()) {
            case R.id.layout_duanzi:
                if (mDuanziFragment == null) {
                    mDuanziFragment = new DuanziFragment();
                }
                fragment = mDuanziFragment;
                break;
            case R.id.layout_news:
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                }
                fragment = mNewsFragment;
                break;
            case R.id.layout_ganhuo:
                if (mGanhuoFragment == null) {
                    mGanhuoFragment = new GanhuoFragment();
                }
                fragment = mGanhuoFragment;
                break;
            case R.id.layout_zhuishu:
                if (mZhuishuFragment == null) {
                    mZhuishuFragment = new ZhuishuFragment();
                }
                fragment = mZhuishuFragment;
                break;
            default:
                break;
        }
        disableAndEnable(v.getId());

        hideAndAddOtherFragment(currentFragment, fragment);
    }

    private void hideAndAddOtherFragment(BaseFragment currentFragment,
                                         BaseFragment targetfragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!targetfragment.isAdded()) {
                if (currentFragment != null) {
                    ft.hide(currentFragment);
                }
                ft.add(R.id.main, targetfragment).commitAllowingStateLoss();
            } else {
                if (currentFragment == null) {
                    ft.show(targetfragment).commitAllowingStateLoss();
                } else {
                    ft.hide(currentFragment).show(targetfragment).commitAllowingStateLoss();
                }
                targetfragment.refreshView();
            }
            this.currentFragment = targetfragment;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 屏蔽相应的点击事件，以免引起不必要的资源消耗
     *
     * @param id
     */
    private void disableAndEnable(int id) {
        switch (id) {
            case R.id.layout_duanzi:
                layoutDuanzi.setEnabled(false);
                layoutNews.setEnabled(true);
                layoutGanhuo.setEnabled(true);
                layoutZhuishu.setEnabled(true);

                imgDuanzi.setImageDrawable(getResources().getDrawable(R.mipmap.ic_duanzi_p));
                tvDuanzi.setTextColor(getResources().getColor(R.color.yellow_txt));
                imgNews.setImageDrawable(getResources().getDrawable(R.mipmap.ic_news));
                tvNews.setTextColor(getResources().getColor(R.color.text_gray));
                imgGanhuo.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ganhuo));
                tvGanhuo.setTextColor(getResources().getColor(R.color.text_gray));
                imgZhuishu.setImageDrawable(getResources().getDrawable(R.mipmap.ic_zhuishu));
                tvZhuishu.setTextColor(getResources().getColor(R.color.text_gray));
                break;

            case R.id.layout_news:
                layoutDuanzi.setEnabled(true);
                layoutNews.setEnabled(false);
                layoutGanhuo.setEnabled(true);
                layoutZhuishu.setEnabled(true);

                imgDuanzi.setImageDrawable(getResources().getDrawable(R.mipmap.ic_duanzi));
                tvDuanzi.setTextColor(getResources().getColor(R.color.text_gray));
                imgNews.setImageDrawable(getResources().getDrawable(R.mipmap.ic_news_p));
                tvNews.setTextColor(getResources().getColor(R.color.yellow_txt));
                imgGanhuo.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ganhuo));
                tvGanhuo.setTextColor(getResources().getColor(R.color.text_gray));
                imgZhuishu.setImageDrawable(getResources().getDrawable(R.mipmap.ic_zhuishu));
                tvZhuishu.setTextColor(getResources().getColor(R.color.text_gray));
                break;

            case R.id.layout_ganhuo:
                layoutDuanzi.setEnabled(true);
                layoutNews.setEnabled(true);
                layoutGanhuo.setEnabled(false);
                layoutZhuishu.setEnabled(true);

                imgDuanzi.setImageDrawable(getResources().getDrawable(R.mipmap.ic_duanzi));
                tvDuanzi.setTextColor(getResources().getColor(R.color.text_gray));
                imgNews.setImageDrawable(getResources().getDrawable(R.mipmap.ic_news));
                tvNews.setTextColor(getResources().getColor(R.color.text_gray));
                imgGanhuo.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ganhuo_p));
                tvGanhuo.setTextColor(getResources().getColor(R.color.yellow_txt));
                imgZhuishu.setImageDrawable(getResources().getDrawable(R.mipmap.ic_zhuishu));
                tvZhuishu.setTextColor(getResources().getColor(R.color.text_gray));
                break;
            case R.id.layout_zhuishu:
                layoutDuanzi.setEnabled(true);
                layoutNews.setEnabled(true);
                layoutGanhuo.setEnabled(true);
                layoutZhuishu.setEnabled(false);

                imgDuanzi.setImageDrawable(getResources().getDrawable(R.mipmap.ic_duanzi));
                tvDuanzi.setTextColor(getResources().getColor(R.color.text_gray));
                imgNews.setImageDrawable(getResources().getDrawable(R.mipmap.ic_news));
                tvNews.setTextColor(getResources().getColor(R.color.text_gray));
                imgGanhuo.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ganhuo));
                tvGanhuo.setTextColor(getResources().getColor(R.color.text_gray));
                imgZhuishu.setImageDrawable(getResources().getDrawable(R.mipmap.ic_zhuishu_p));
                tvZhuishu.setTextColor(getResources().getColor(R.color.yellow_txt));
                break;
            default:
                break;
        }


    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (JZVideoPlayerStandard.backPress()){
                return false;
            } else {
                if ((System.currentTimeMillis()-mExitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
