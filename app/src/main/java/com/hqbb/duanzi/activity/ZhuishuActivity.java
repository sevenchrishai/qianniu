package com.hqbb.duanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseActivity;
import com.hqbb.duanzi.base.BaseBookActivity;
import com.hqbb.duanzi.base.Constant;
import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.component.DaggerMainComponent;
import com.hqbb.duanzi.contract.MainContract;
import com.hqbb.duanzi.fragment.BookFindFragment;
import com.hqbb.duanzi.fragment.BookFragment;
import com.hqbb.duanzi.manager.EventManager;
import com.hqbb.duanzi.presenter.MainActivityPresenter;
import com.hqbb.duanzi.service.DownloadBookService;
import com.hqbb.duanzi.util.LogUtils;
import com.hqbb.duanzi.util.SharedPreferencesUtil;
import com.hqbb.duanzi.util.ToastUtils;

import javax.inject.Inject;

import cn.jzvd.JZVideoPlayerStandard;

public class ZhuishuActivity extends BaseBookActivity implements MainContract.View{

    private BookFragment bookFragment;
    private BookFindFragment bookFindFragment;
    private FragmentManager fragmentManager;

    @Inject
    MainActivityPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhuishu;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
        mCommonToolbar.setTitle("追书");
    }

    @Override
    public void initDatas() {
        startService(new Intent(this, DownloadBookService.class));
        if (bookFragment == null) {
            bookFragment = new BookFragment();
        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, bookFragment).commitAllowingStateLoss();
    }

    @Override
    public void configViews() {
        mPresenter.attachView(this);
    }

    public void pullSyncBookShelf() {
        mPresenter.syncBookShelf();
    }

    public void gotoBookFindFragment() {
        bookFindFragment = new BookFindFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, bookFindFragment);
        fragmentTransaction.commit();
        bookFragment = null;
    }

    private void onBackFrament() {
        if (bookFragment != null) {
            finish();
        } else {
            bookFragment = new BookFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame, bookFragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bookFragment != null) {
                finish();
                return true;
            } else {
                bookFragment = new BookFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, bookFragment);
                fragmentTransaction.commit();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showError() {
        ToastUtils.showSingleToast("同步异常");
        dismissDialog();
    }

    @Override
    public void complete() {

    }

    @Override
    public void syncBookShelfCompleted() {
        dismissDialog();
        EventManager.refreshCollectionList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownloadBookService.cancel();
        stopService(new Intent(this, DownloadBookService.class));
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                startActivity(new Intent(ZhuishuActivity.this, SearchActivity.class));
                break;
            case R.id.action_sync_bookshelf:
                showDialog();
                mPresenter.syncBookShelf();
                break;
            case R.id.action_scan_local_book:
                ScanLocalBookActivity.startActivity(this);
                break;
            case R.id.action_wifi_book:
                WifiBookActivity.startActivity(this);
                break;
            case R.id.action_night_mode:
                if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
                    SharedPreferencesUtil.getInstance().putBoolean(Constant.ISNIGHT, false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    SharedPreferencesUtil.getInstance().putBoolean(Constant.ISNIGHT, true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
                break;
            case R.id.action_settings:
                BookSettingActivity.startActivity(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
