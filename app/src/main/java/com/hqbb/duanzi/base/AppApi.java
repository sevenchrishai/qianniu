package com.hqbb.duanzi.base;

/**
 * Created by ylh on 2018/1/11 0011.
 */

public class AppApi {

    /**
     * 一、随机推荐段子（包含文字、图片、GIF、视频）
     http://www.apiopen.top/satinApi?type=1&page=1
     接口说明：
     type=1 : 全部
     type=41 : 视频		case 4:
     type=10 : 图片		case 3:
     type=29 : 段子		case 2:
     type=31 : 声音（不要这个）
     page=1:页码
     备注:
     当page=0时，会随机返回一页数据，page>=1时会返回相应页码的数据，图片包含GIF，is_gif 字段判断。

     二、聚合API：
     1.最新笑话
     http://japi.juhe.cn/joke/content/text.from?page=1&pagesize=10&key=5717fd14e8aa4ab4bc5e30180ce9372d
     2.随机笑话
     http://v.juhe.cn/joke/randJoke.php?type=&key=5717fd14e8aa4ab4bc5e30180ce9372d
     3.最新图片
     http://japi.juhe.cn/joke/img/text.from?page=1&pagesize=10&key=5717fd14e8aa4ab4bc5e30180ce9372d
     4.随机图片
     http://v.juhe.cn/joke/randJoke.php?type=pic&key=5717fd14e8aa4ab4bc5e30180ce9372d
     5.按更新时间查询笑话
     接口地址：http://japi.juhe.cn/joke/content/list.from	（图片为img）
     返回格式：json
     请求方式：http get
     请求示例：http://japi.juhe.cn/joke/content/list.from?key=您申请的KEY&page=2&pagesize=10&sort=asc&time=1418745237
     接口备注：根据时间戳返回该时间点前或后的笑话列表
     调用样例及调试工具：API测试工具
     请求参数说明：
     名称	类型	必填	说明
     sort	string	是	类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     page	int	否	当前页数,默认1
     pagesize	int	否	每次返回条数,默认1,最大20
     time	string	是	时间戳（10位），如：1418816972
     key	string	是	您申请的key
     */

    //csdn博客API http://blog.csdn.net/c__chao/article/details/78573737
//    public static final String BASE_URL = "http://www.apiopen.top:88/";
    // 2018.10.29更新
    public static final String BASE_URL = "https://www.apiopen.top/";
    public static final String DZ_URL = "satinApi";
    public static final String MM_URL = "meituApi";

    //干货集中营API  http://gank.io/api  10为pagesize  1为page  福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all | 瞎推荐 | App
    public static final String GANK_URL = "http://gank.io/api/data";

    //新闻API（京东万象）
    public static final String JD_NEWS_BASE_URL = "https://way.jd.com/jisuapi/";
    public static final String GET_NEWS = "get";
    public static final String GET_CHANNEL = "channel";
    public static final String GET_NEWS_SEARCH = "newSearch";

    //追书神器API
    public static final String ZS_BASE_URL = "http://api.zhuishushenqi.com";
    public static final String ZS_IMG_BASE_URL = "http://statics.zhuishushenqi.com";

}
