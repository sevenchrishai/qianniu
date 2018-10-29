package com.hqbb.duanzi.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hqbb.duanzi.R;
import com.hqbb.duanzi.adapter.HotReviewAdapter;
import com.hqbb.duanzi.adapter.RecommendBookListAdapter;
import com.hqbb.duanzi.base.BaseBookActivity;
import com.hqbb.duanzi.base.Constant;
import com.hqbb.duanzi.base.OnRvItemClickListener;
import com.hqbb.duanzi.bean.BookDetail;
import com.hqbb.duanzi.bean.BookLists;
import com.hqbb.duanzi.bean.HotReview;
import com.hqbb.duanzi.bean.Recommend;
import com.hqbb.duanzi.bean.RecommendBookList;
import com.hqbb.duanzi.bean.support.RefreshCollectionIconEvent;
import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.component.DaggerBookComponent;
import com.hqbb.duanzi.contract.BookDetailContract;
import com.hqbb.duanzi.manager.CollectionsManager;
import com.hqbb.duanzi.presenter.BookDetailPresenter;
import com.hqbb.duanzi.util.FormatUtils;
import com.hqbb.duanzi.util.ToastUtils;
import com.hqbb.duanzi.widget.DrawableCenterButton;
import com.hqbb.duanzi.widget.TagColor;
import com.hqbb.duanzi.widget.TagGroup;
import com.hqbb.easyadapterlibrary.glide.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class BookDetailActivity extends BaseBookActivity implements BookDetailContract.View, OnRvItemClickListener<Object> {

    public static String INTENT_BOOK_ID = "bookId";

    public static void startActivity(Context context, String bookId) {
        context.startActivity(new Intent(context, BookDetailActivity.class)
                .putExtra(INTENT_BOOK_ID, bookId));
    }

    @BindView(R.id.ivBookCover)
    ImageView mIvBookCover;
    @BindView(R.id.tvBookListTitle)
    TextView mTvBookTitle;
    @BindView(R.id.tvBookListAuthor)
    TextView mTvAuthor;
    @BindView(R.id.tvCatgory)
    TextView mTvCatgory;
    @BindView(R.id.tvWordCount)
    TextView mTvWordCount;
    @BindView(R.id.tvLatelyUpdate)
    TextView mTvLatelyUpdate;
    @BindView(R.id.btnRead)
    DrawableCenterButton mBtnRead;
    @BindView(R.id.btnJoinCollection)
    DrawableCenterButton mBtnJoinCollection;
    @BindView(R.id.tvLatelyFollower)
    TextView mTvLatelyFollower;
    @BindView(R.id.tvRetentionRatio)
    TextView mTvRetentionRatio;
    @BindView(R.id.tvSerializeWordCount)
    TextView mTvSerializeWordCount;
    @BindView(R.id.tag_group)
    TagGroup mTagGroup;
    @BindView(R.id.tvlongIntro)
    TextView mTvlongIntro;
    @BindView(R.id.tvMoreReview)
    TextView mTvMoreReview;
    @BindView(R.id.rvHotReview)
    RecyclerView mRvHotReview;
    @BindView(R.id.rlCommunity)
    RelativeLayout mRlCommunity;
    @BindView(R.id.tvCommunity)
    TextView mTvCommunity;
    @BindView(R.id.tvHelpfulYes)
    TextView mTvPostCount;
    @BindView(R.id.tvRecommendBookList)
    TextView mTvRecommendBookList;

    @BindView(R.id.rvRecommendBoookList)
    RecyclerView mRvRecommendBoookList;

    @Inject
    BookDetailPresenter mPresenter;

    private List<String> tagList = new ArrayList<>();
    private int times = 0;

    private HotReviewAdapter mHotReviewAdapter;
    private List<HotReview.Reviews> mHotReviewList = new ArrayList<>();
    private RecommendBookListAdapter mRecommendBookListAdapter;
    private List<RecommendBookList.RecommendBook> mRecommendBookList = new ArrayList<>();
    private String bookId;

    private boolean collapseLongIntro = true;
    private Recommend.RecommendBooks recommendBooks;
    private boolean isJoinedCollections = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle(R.string.book_detail);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        bookId = getIntent().getStringExtra(INTENT_BOOK_ID);
        EventBus.getDefault().register(this);
    }

    @Override
    public void configViews() {
        mRvHotReview.setHasFixedSize(true);
        mRvHotReview.setLayoutManager(new LinearLayoutManager(this));
        mHotReviewAdapter = new HotReviewAdapter(mContext, mHotReviewList, this);
        mRvHotReview.setAdapter(mHotReviewAdapter);

        mRvRecommendBoookList.setHasFixedSize(true);
        mRvRecommendBoookList.setLayoutManager(new LinearLayoutManager(this));
        mRecommendBookListAdapter = new RecommendBookListAdapter(mContext, mRecommendBookList, this);
        mRvRecommendBoookList.setAdapter(mRecommendBookListAdapter);

        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                startActivity(new Intent(BookDetailActivity.this, BooksByTagActivity.class)
                        .putExtra("tag", tag));
            }
        });

        mPresenter.attachView(this);
        mPresenter.getBookDetail(bookId);
        mPresenter.getHotReview(bookId);
        mPresenter.getRecommendBookList(bookId, "3");
    }

    @Override
    public void showBookDetail(BookDetail data) {
        Glide.with(mContext)
                .load(Constant.IMG_BASE_URL + data.cover)
                .placeholder(R.mipmap.cover_default)
                .transform(new GlideRoundTransform(mContext))
                .into(mIvBookCover);

        mTvBookTitle.setText(data.title);
        mTvAuthor.setText(String.format(getString(R.string.book_detail_author), data.author));
        mTvCatgory.setText(String.format(getString(R.string.book_detail_category), data.cat));
        mTvWordCount.setText(FormatUtils.formatWordCount(data.wordCount));
        mTvLatelyUpdate.setText(FormatUtils.getDescriptionTimeFromDateString(data.updated));
        mTvLatelyFollower.setText(String.valueOf(data.latelyFollower));
        mTvRetentionRatio.setText(TextUtils.isEmpty(data.retentionRatio) ?
                "-" : String.format(getString(R.string.book_detail_retention_ratio),
                data.retentionRatio));
        mTvSerializeWordCount.setText(data.serializeWordCount < 0 ? "-" :
                String.valueOf(data.serializeWordCount));

        tagList.clear();
        tagList.addAll(data.tags);
        times = 0;
        showHotWord();

        mTvlongIntro.setText(data.longIntro);
        mTvCommunity.setText(String.format(getString(R.string.book_detail_community), data.title));
        mTvPostCount.setText(String.format(getString(R.string.book_detail_post_count), data.postCount));

        recommendBooks = new Recommend.RecommendBooks();
        recommendBooks.title = data.title;
        recommendBooks._id = data._id;
        recommendBooks.cover = data.cover;
        recommendBooks.lastChapter = data.lastChapter;
        recommendBooks.updated = data.updated;

        refreshCollectionIcon();
    }

    /**
     * 刷新收藏图标
     */
    private void refreshCollectionIcon() {
        if (CollectionsManager.getInstance().isCollected(recommendBooks._id)) {
            initCollection(false);
        } else {
            initCollection(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void RefreshCollectionIcon(RefreshCollectionIconEvent event) {
        refreshCollectionIcon();
    }

    /**
     * 每次显示8个
     */
    private void showHotWord() {
        int start, end;
        if (times < tagList.size() && times + 8 <= tagList.size()) {
            start = times;
            end = times + 8;
        } else if (times < tagList.size() - 1 && times + 8 > tagList.size()) {
            start = times;
            end = tagList.size() - 1;
        } else {
            start = 0;
            end = tagList.size() > 8 ? 8 : tagList.size();
        }
        times = end;
        if (end - start > 0) {
            List<String> batch = tagList.subList(start, end);
            List<TagColor> colors = TagColor.getRandomColors(batch.size());
            mTagGroup.setTags(colors, (String[]) batch.toArray(new String[batch.size()]));
        }
    }

    @Override
    public void showHotReview(List<HotReview.Reviews> list) {
        mHotReviewList.clear();
        mHotReviewList.addAll(list);
        mHotReviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRecommendBookList(List<RecommendBookList.RecommendBook> list) {
        if (!list.isEmpty()) {
            mTvRecommendBookList.setVisibility(View.VISIBLE);
            mRecommendBookList.clear();
            mRecommendBookList.addAll(list);
            mRecommendBookListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(View view, int position, Object data) {
        if (data instanceof HotReview.Reviews) {
            BookDiscussionDetailActivity.startActivity(this, ((HotReview.Reviews) data)._id);
        } else if (data instanceof RecommendBookList.RecommendBook) {
            RecommendBookList.RecommendBook recommendBook = (RecommendBookList.RecommendBook) data;

            BookLists bookLists = new BookLists();
            BookLists.BookListsBean bookListsBean = bookLists.new BookListsBean();
            bookListsBean._id = recommendBook.id;
            bookListsBean.author = recommendBook.author;
            bookListsBean.bookCount = recommendBook.bookCount;
            bookListsBean.collectorCount = recommendBook.collectorCount;
            bookListsBean.cover = recommendBook.cover;
            bookListsBean.desc = recommendBook.desc;
            bookListsBean.title = recommendBook.title;

            SubjectBookListDetailActivity.startActivity(this, bookListsBean);
        }
    }

    @OnClick(R.id.btnJoinCollection)
    public void onClickJoinCollection() {
        if (!isJoinedCollections) {
            if (recommendBooks != null) {
                CollectionsManager.getInstance().add(recommendBooks);
                ToastUtils.showToast(String.format(getString(
                        R.string.book_detail_has_joined_the_book_shelf), recommendBooks.title));
                initCollection(false);
            }
        } else {
            CollectionsManager.getInstance().remove(recommendBooks._id);
            ToastUtils.showToast(String.format(getString(
                    R.string.book_detail_has_remove_the_book_shelf), recommendBooks.title));
            initCollection(true);
        }
    }

    private void initCollection(boolean coll) {
        if (coll) {
            mBtnJoinCollection.setText(R.string.book_detail_join_collection);
            Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.book_detail_info_add_img);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mBtnJoinCollection.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.shape_common_btn_solid_normal));
            mBtnJoinCollection.setCompoundDrawables(drawable, null, null, null);
            mBtnJoinCollection.postInvalidate();
            isJoinedCollections = false;
        } else {
            mBtnJoinCollection.setText(R.string.book_detail_remove_collection);
            Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.book_detail_info_del_img);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mBtnJoinCollection.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_join_collection_pressed));
            mBtnJoinCollection.setCompoundDrawables(drawable, null, null, null);
            mBtnJoinCollection.postInvalidate();
            isJoinedCollections = true;
        }
    }

    @OnClick(R.id.btnRead)
    public void onClickRead() {
        if (recommendBooks == null) return;
        ReadActivity.startActivity(this, recommendBooks);
    }

    @OnClick(R.id.tvBookListAuthor)
    public void searchByAuthor() {
        String author = mTvAuthor.getText().toString().replaceAll(" ", "");
        SearchByAuthorActivity.startActivity(this, author);
    }

    @OnClick(R.id.tvlongIntro)
    public void collapseLongIntro() {
        if (collapseLongIntro) {
            mTvlongIntro.setMaxLines(20);
            collapseLongIntro = false;
        } else {
            mTvlongIntro.setMaxLines(4);
            collapseLongIntro = true;
        }
    }

    @OnClick(R.id.tvMoreReview)
    public void onClickMoreReview() {
        BookDetailCommunityActivity.startActivity(this, bookId, mTvBookTitle.getText().toString(), 1);
    }

    @OnClick(R.id.rlCommunity)
    public void onClickCommunity() {
        BookDetailCommunityActivity.startActivity(this, bookId, mTvBookTitle.getText().toString(), 0);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
