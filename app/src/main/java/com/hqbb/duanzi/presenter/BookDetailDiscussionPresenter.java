/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hqbb.duanzi.presenter;

import com.hqbb.duanzi.api.BookApi;
import com.hqbb.duanzi.base.RxPresenter;
import com.hqbb.duanzi.bean.DiscussionList;
import com.hqbb.duanzi.contract.BookDetailDiscussionContract;
import com.hqbb.duanzi.util.LogUtils;
import com.hqbb.duanzi.util.RxUtil;
import com.hqbb.duanzi.util.StringUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author lfh.
 * @date 16/9/7.
 */
public class BookDetailDiscussionPresenter extends RxPresenter<BookDetailDiscussionContract.View> implements BookDetailDiscussionContract.Presenter<BookDetailDiscussionContract.View> {

    private BookApi bookApi;

    @Inject
    public BookDetailDiscussionPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getBookDetailDiscussionList(String bookId, String sort, final int start, int limit) {
        String key = StringUtils.creatAcacheKey("book-detail-discussion-list", bookId, sort, start, limit);
        Observable<DiscussionList> fromNetWork = bookApi.getBookDetailDisscussionList(bookId, sort, "normal,vote", start + "", limit + "")
                .compose(RxUtil.<DiscussionList>rxCacheListHelper(key));

        //依次检查disk、network
        Subscription rxSubscription = Observable.concat(RxUtil.rxCreateDiskObservable(key, DiscussionList.class), fromNetWork)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DiscussionList>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getBookDetailDiscussionList:" + e.toString());
                        mView.showError();
                    }

                    @Override
                    public void onNext(DiscussionList list) {
                        boolean isRefresh = start == 0 ? true : false;
                        mView.showBookDetailDiscussionList(list.posts, isRefresh);
                    }
                });
        addSubscrebe(rxSubscription);
    }

}
