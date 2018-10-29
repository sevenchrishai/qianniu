package com.hqbb.duanzi.retrofit2;

import com.hqbb.duanzi.bean.AutoComplete;
import com.hqbb.duanzi.bean.BookDetail;
import com.hqbb.duanzi.bean.BookHelp;
import com.hqbb.duanzi.bean.BookHelpList;
import com.hqbb.duanzi.bean.BookListDetail;
import com.hqbb.duanzi.bean.BookListTags;
import com.hqbb.duanzi.bean.BookLists;
import com.hqbb.duanzi.bean.BookMixAToc;
import com.hqbb.duanzi.bean.BookRead;
import com.hqbb.duanzi.bean.BookReview;
import com.hqbb.duanzi.bean.BookReviewList;
import com.hqbb.duanzi.bean.BookSource;
import com.hqbb.duanzi.bean.BooksByCats;
import com.hqbb.duanzi.bean.BooksByTag;
import com.hqbb.duanzi.bean.CategoryList;
import com.hqbb.duanzi.bean.CategoryListLv2;
import com.hqbb.duanzi.bean.ChapterRead;
import com.hqbb.duanzi.bean.CommentList;
import com.hqbb.duanzi.bean.DiscussionList;
import com.hqbb.duanzi.bean.Disscussion;
import com.hqbb.duanzi.bean.DuanziBean;
import com.hqbb.duanzi.bean.GankBean;
import com.hqbb.duanzi.bean.HotReview;
import com.hqbb.duanzi.bean.HotWord;
import com.hqbb.duanzi.bean.MmBean;
import com.hqbb.duanzi.bean.NewsBean;
import com.hqbb.duanzi.bean.NewsChannelBean;
import com.hqbb.duanzi.bean.NewsSearchBean;
import com.hqbb.duanzi.bean.PostCount;
import com.hqbb.duanzi.bean.RankingList;
import com.hqbb.duanzi.bean.Rankings;
import com.hqbb.duanzi.bean.Recommend;
import com.hqbb.duanzi.bean.RecommendBookList;
import com.hqbb.duanzi.bean.SearchDetail;
import com.hqbb.duanzi.bean.user.Following;
import com.hqbb.duanzi.bean.user.Login;
import com.hqbb.duanzi.bean.user.LoginReq;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.hqbb.duanzi.base.AppApi.DZ_URL;
import static com.hqbb.duanzi.base.AppApi.GANK_URL;
import static com.hqbb.duanzi.base.AppApi.GET_CHANNEL;
import static com.hqbb.duanzi.base.AppApi.GET_NEWS;
import static com.hqbb.duanzi.base.AppApi.GET_NEWS_SEARCH;
import static com.hqbb.duanzi.base.AppApi.JD_NEWS_BASE_URL;
import static com.hqbb.duanzi.base.AppApi.MM_URL;
import static com.hqbb.duanzi.base.AppApi.ZS_BASE_URL;

/**
 * Created by ylh on 2018/1/9 0009.
 */

public interface AppInterface {

    //网友博客
    @GET(DZ_URL)
    Observable<DuanziBean> getDuanzi(
            @Query("type") String type,
            @Query("page") int page
    );

    @GET(MM_URL)
    Observable<MmBean> getMm(
            @Query("page") int page
    );

    //干货集中营
    @GET(GANK_URL+"/{type}/{pagesize}/{page}")
    Observable<GankBean> getGank(
            @Path("type") String type,
            @Path("pagesize") String pagesize,
            @Path("page") int page
    );

    //京东万象
    @GET(JD_NEWS_BASE_URL+GET_NEWS)
    Observable<NewsBean> getNews(
            @Query("channel") String channel,
            @Query("num") String num,
            @Query("start") int start,
            @Query("appkey") String appkey
    );

    @GET(JD_NEWS_BASE_URL+GET_CHANNEL)
    Observable<NewsChannelBean> getNewsChannel(
            @Query("appkey") String appkey
    );

    @GET(JD_NEWS_BASE_URL+GET_NEWS_SEARCH)
    Observable<NewsSearchBean> getNewsSearch(
            @Query("keyword") String keyword,
            @Query("appkey") String appkey
    );

    //追书神器

    /**
     * @param gender
     * @return
     */
    @GET(ZS_BASE_URL + "/book/recommend")
    Observable<Recommend> getRecomend(@Query("gender") String gender);

    /**
     * 获取正版源(若有) 与 盗版源
     * @param view
     * @param book
     * @return
     */
    @GET(ZS_BASE_URL + "/atoc")
    Observable<List<BookSource>> getABookSource(@Query("view") String view, @Query("book") String book);

    /**
     * 只能获取正版源
     *
     * @param view
     * @param book
     * @return
     */
    @GET(ZS_BASE_URL + "/btoc")
    Observable<List<BookSource>> getBBookSource(@Query("view") String view, @Query("book") String book);

    @GET(ZS_BASE_URL + "/mix-atoc/{bookId}")
    Observable<BookMixAToc> getBookMixAToc(@Path("bookId") String bookId, @Query("view") String view);

    @GET(ZS_BASE_URL + "/mix-toc/{bookId}")
    Observable<BookRead> getBookRead(@Path("bookId") String bookId);

    @GET(ZS_BASE_URL + "/btoc/{bookId}")
    Observable<BookMixAToc> getBookBToc(@Path("bookId") String bookId, @Query("view") String view);

    @GET(ZS_BASE_URL + "http://chapter2.zhuishushenqi.com/chapter/{url}")
    Observable<ChapterRead> getChapterRead(@Path("url") String url);

    @GET(ZS_BASE_URL + "/post/post-count-by-book")
    Observable<PostCount> postCountByBook(@Query("bookId") String bookId);

    @GET(ZS_BASE_URL + "/post/total-count")
    Observable<PostCount> postTotalCount(@Query("books") String bookId);

    @GET(ZS_BASE_URL + "/book/hot-word")
    Observable<HotWord> getHotWord();

    /**
     * 关键字自动补全
     *
     * @param query
     * @return
     */
    @GET(ZS_BASE_URL + "/book/auto-complete")
    Observable<AutoComplete> autoComplete(@Query("query") String query);

    /**
     * 书籍查询
     *
     * @param query
     * @return
     */
    @GET(ZS_BASE_URL + "/book/fuzzy-search")
    Observable<SearchDetail> searchBooks(@Query("query") String query);

    /**
     * 通过作者查询书名
     *
     * @param author
     * @return
     */
    @GET(ZS_BASE_URL + "/book/accurate-search")
    Observable<BooksByTag> searchBooksByAuthor(@Query("author") String author);

    /**
     * 热门评论
     *
     * @param book
     * @return
     */
    @GET(ZS_BASE_URL + "/post/review/best-by-book")
    Observable<HotReview> getHotReview(@Query("book") String book);

    @GET(ZS_BASE_URL + "/book-list/{bookId}/recommend")
    Observable<RecommendBookList> getRecommendBookList(@Path("bookId") String bookId, @Query("limit") String limit);

    @GET(ZS_BASE_URL + "/book/{bookId}")
    Observable<BookDetail> getBookDetail(@Path("bookId") String bookId);

    @GET(ZS_BASE_URL + "/book/by-tags")
    Observable<BooksByTag> getBooksByTag(@Query("tags") String tags, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取所有排行榜
     *
     * @return
     */
    @GET(ZS_BASE_URL + "/ranking/gender")
    Observable<RankingList> getRanking();

    /**
     * 获取单一排行榜
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     *
     * @return
     */
    @GET(ZS_BASE_URL + "/ranking/{rankingId}")
    Observable<Rankings> getRanking(@Path("rankingId") String rankingId);

    /**
     * 获取主题书单列表
     * 本周最热：duration=last-seven-days&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collectorCount
     *
     * @param tag    都市、古代、架空、重生、玄幻、网游
     * @param gender male、female
     * @param limit  20
     * @return
     */
    @GET(ZS_BASE_URL + "/book-list")
    Observable<BookLists> getBookLists(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("tag") String tag, @Query("gender") String gender);

    /**
     * 获取主题书单标签列表
     *
     * @return
     */
    @GET(ZS_BASE_URL + "/book-list/tagType")
    Observable<BookListTags> getBookListTags();

    /**
     * 获取书单详情
     *
     * @return
     */
    @GET(ZS_BASE_URL + "/book-list/{bookListId}")
    Observable<BookListDetail> getBookListDetail(@Path("bookListId") String bookListId);

    /**
     * 获取分类
     *
     * @return
     */
    @GET(ZS_BASE_URL + "/cats/lv2/statistics")
    Observable<CategoryList> getCategoryList();

    /**
     * 获取二级分类
     *
     * @return
     */
    @GET(ZS_BASE_URL + "/cats/lv2")
    Observable<CategoryListLv2> getCategoryListLv2();

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param limit  50
     * @return
     */
    @GET(ZS_BASE_URL + "/book/by-categories")
    Observable<BooksByCats> getBooksByCats(@Query("gender") String gender, @Query("type") String type, @Query("major") String major, @Query("minor") String minor, @Query("start") int start, @Query("limit") int limit);


    /**
     * 获取综合讨论区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=true
     *
     * @param block      ramble:综合讨论区
     *                   original：原创区
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   comment-count(最多评论)
     * @param type       all
     * @param start      0
     * @param limit      20
     * @param distillate true(精品)
     * @return
     */
    @GET(ZS_BASE_URL + "/post/by-block")
    Observable<DiscussionList> getBookDisscussionList(@Query("block") String block, @Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取综合讨论区帖子详情
     *
     * @param disscussionId->_id
     * @return
     */
    @GET(ZS_BASE_URL + "/post/{disscussionId}")
    Observable<Disscussion> getBookDisscussionDetail(@Path("disscussionId") String disscussionId);

    /**
     * 获取神评论列表(综合讨论区、书评区、书荒区皆为同一接口)
     *
     * @param disscussionId->_id
     * @return
     */
    @GET(ZS_BASE_URL + "/post/{disscussionId}/comment/best")
    Observable<CommentList> getBestComments(@Path("disscussionId") String disscussionId);

    /**
     * 获取综合讨论区帖子详情内的评论列表
     *
     * @param disscussionId->_id
     * @param start              0
     * @param limit              30
     * @return
     */
    @GET(ZS_BASE_URL + "/post/{disscussionId}/comment")
    Observable<CommentList> getBookDisscussionComments(@Path("disscussionId") String disscussionId, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取书评区帖子列表
     * 全部、全部类型、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、玄幻奇幻、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=xhqh&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   helpful(最有用的)
     *                   comment-count(最多评论)
     * @param type       all(全部类型)、xhqh(玄幻奇幻)、dsyn(都市异能)...
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET(ZS_BASE_URL + "/post/review")
    Observable<BookReviewList> getBookReviewList(@Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取书评区帖子详情
     *
     * @param bookReviewId->_id
     * @return
     */
    @GET(ZS_BASE_URL + "/post/review/{bookReviewId}")
    Observable<BookReview> getBookReviewDetail(@Path("bookReviewId") String bookReviewId);

    /**
     * 获取书评区、书荒区帖子详情内的评论列表
     *
     * @param bookReviewId->_id
     * @param start             0
     * @param limit             30
     * @return
     */
    @GET(ZS_BASE_URL + "/post/review/{bookReviewId}/comment")
    Observable<CommentList> getBookReviewComments(@Path("bookReviewId") String bookReviewId, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取书荒区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   comment-count(最多评论)
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET(ZS_BASE_URL + "/post/help")
    Observable<BookHelpList> getBookHelpList(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取书荒区帖子详情
     *
     * @param helpId->_id
     * @return
     */
    @GET(ZS_BASE_URL + "/post/help/{helpId}")
    Observable<BookHelp> getBookHelpDetail(@Path("helpId") String helpId);

    /**
     * 第三方登陆
     */
    @POST(ZS_BASE_URL + "/user/login")
    Observable<Login> login(@Body LoginReq loginReq);

    @GET(ZS_BASE_URL + "/user/followings/{userid}")
    Observable<Following> getFollowings(@Path("userid") String userId);

    /**
     * 获取书籍详情讨论列表
     *
     * @param book  bookId
     * @param sort  updated(默认排序)
     *              created(最新发布)
     *              comment-count(最多评论)
     * @param type  normal
     *              vote
     * @param start 0
     * @param limit 20
     * @return
     */
    @GET(ZS_BASE_URL + "/post/by-book")
    Observable<DiscussionList> getBookDetailDisscussionList(@Query("book") String book, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取书籍详情书评列表
     *
     * @param book  bookId
     * @param sort  updated(默认排序)
     *              created(最新发布)
     *              helpful(最有用的)
     *              comment-count(最多评论)
     * @param start 0
     * @param limit 20
     * @return
     */
    @GET(ZS_BASE_URL + "/post/review/by-book")
    Observable<HotReview> getBookDetailReviewList(@Query("book") String book, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit);

    @GET(ZS_BASE_URL + "/post/original")
    Observable<DiscussionList> getBookOriginalList(@Query("block") String block, @Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);


}
