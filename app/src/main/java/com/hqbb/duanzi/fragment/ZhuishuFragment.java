package com.hqbb.duanzi.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.activity.MainActivity;
import com.hqbb.duanzi.activity.ReadActivity;
import com.hqbb.duanzi.activity.ZhuishuActivity;
import com.hqbb.duanzi.base.BaseFragment;
import com.hqbb.duanzi.base.BaseRVFragment;
import com.hqbb.duanzi.bean.BookMixAToc;
import com.hqbb.duanzi.bean.Recommend;
import com.hqbb.duanzi.bean.support.DownloadMessage;
import com.hqbb.duanzi.bean.support.DownloadProgress;
import com.hqbb.duanzi.bean.support.DownloadQueue;
import com.hqbb.duanzi.bean.support.RefreshCollectionListEvent;
import com.hqbb.duanzi.bean.support.UserSexChooseFinishedEvent;
import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.component.DaggerMainComponent;
import com.hqbb.duanzi.contract.RecommendContract;
import com.hqbb.duanzi.easyadapter.RecommendAdapter;
import com.hqbb.duanzi.manager.CollectionsManager;
import com.hqbb.duanzi.presenter.RecommendPresenter;
import com.hqbb.duanzi.service.DownloadBookService;
import com.hqbb.duanzi.widget.recyclerview.EasyRecyclerView;
import com.hqbb.duanzi.widget.recyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class ZhuishuFragment extends BaseFragment {

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_zhuishu, container, false);

        mView.findViewById(R.id.btnToAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZhuishuActivity.class);
                startActivity(intent);
            }
        });

        return mView;
    }

    @Override
    public void refreshView() {

    }

}
