package com.hqbb.duanzi.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.module.AppModule;
import com.hqbb.duanzi.module.BookApiModule;
import com.hqbb.duanzi.util.HandleBackInterface;
import com.hqbb.duanzi.util.HandleBackUtil;
import com.hqbb.duanzi.widget.loadding.CustomDialog;

import butterknife.ButterKnife;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public abstract class BaseFragment extends Fragment implements HandleBackInterface{

    protected View mView = null;
    protected Context mContext = null;

    protected FragmentActivity activity;
    protected LayoutInflater inflater;

    private CustomDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContext == null) {
            mContext = getActivity();
        }
        if (mView == null) {
            mView = initView(inflater, container, savedInstanceState);
        }
        activity = getSupportActivity();
        this.inflater = inflater;
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    //切换fragment的时候刷新界面数据
    public abstract void refreshView();

    // 页面被移除时 解除之前addView建立的关系
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 指定布局位置 removeView
        if (mView != null) {
            ViewGroup viewgorup = (ViewGroup) mView.getParent();
            if (viewgorup != null) {
                viewgorup.removeView(mView);
            }
        }
    }

    @Override
    public boolean onBackPressed() {
        return HandleBackUtil.handleBackPress(this);
    }


    public CustomDialog getDialog() {
        if (dialog == null) {
            dialog = CustomDialog.instance(getActivity());
            dialog.setCancelable(false);
        }
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void showDialog() {
        getDialog().show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }
}
