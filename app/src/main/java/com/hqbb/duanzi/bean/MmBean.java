package com.hqbb.duanzi.bean;

import java.util.List;

/**
 * Created by ylh on 2018/1/11 0011.
 */

public class MmBean {

    /**
     * code : 200
     * msg : 成功!
     * data : [{"createdAt":"2016-08-09T11:12:51.255Z","publishedAt":"2016-08-09T11:30:16.672Z","type":"美图","url":"http://ww1.sinaimg.cn/large/610dc034jw1f6nbm78pplj20dw0i2djy.jpg"},{"createdAt":"2017-07-24T09:32:49.583Z","publishedAt":"2017-07-24T12:13:11.280Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034gy1fhupzs0awwj20u00u0tcf.jpg"},{"createdAt":"2016-10-23T09:51:16.503Z","publishedAt":"2016-10-24T11:25:22.197Z","type":"美图","url":"http://ww2.sinaimg.cn/large/610dc034jw1f91ypzqaivj20u00k0jui.jpg"},{"createdAt":"2017-07-07T08:35:50.172Z","publishedAt":"2017-07-07T12:14:57.685Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhb0t7ob2mj20u011itd9.jpg"},{"createdAt":"2017-06-07T11:37:11.749Z","publishedAt":"2017-06-07T11:43:31.396Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg"},{"createdAt":"2017-10-10T12:38:25.180Z","publishedAt":"2017-10-10T12:41:34.882Z","type":"美图","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-10-10-sakura.gun_10_10_2017_12_33_34_751.jpg"},{"createdAt":"2017-03-01T07:32:17.106Z","publishedAt":"2017-03-01T12:03:57.461Z","type":"美图","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-28-15057157_446684252387131_4267811446148038656_n.jpg"},{"createdAt":"2015-06-30T02:09:26.186Z","publishedAt":"2015-07-02T03:50:48.82Z","type":"美图","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1etlw75so1hj20qo0hsgpk.jpg"},{"createdAt":"2016-08-15T23:47:41.110Z","publishedAt":"2016-08-16T11:22:38.139Z","type":"美图","url":"http://ww4.sinaimg.cn/large/610dc034jw1f6uv5gbsa9j20u00qxjt6.jpg"},{"createdAt":"2015-08-06T01:33:55.463Z","publishedAt":"2015-08-06T04:16:55.601Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1eusn3niy2bj20hs0qo0wb.jpg"},{"createdAt":"2017-09-20T08:18:40.702Z","publishedAt":"2017-09-20T13:17:38.709Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjppsiclufj20u011igo5.jpg"},{"createdAt":"2015-12-20T04:04:25.322Z","publishedAt":"2015-12-21T03:58:24.498Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1ez5zq5g685j20hj0qo0w1.jpg"},{"createdAt":"2016-12-05T08:46:21.259Z","publishedAt":"2016-12-05T11:40:51.351Z","type":"美图","url":"http://ww4.sinaimg.cn/large/610dc034gw1fafmi73pomj20u00u0abr.jpg"},{"createdAt":"2016-11-01T08:29:46.640Z","publishedAt":"2016-11-01T11:46:01.617Z","type":"美图","url":"http://ww1.sinaimg.cn/large/610dc034jw1f9cayjaa96j20u011hqbs.jpg"},{"createdAt":"2017-07-19T08:21:52.67Z","publishedAt":"2017-07-19T13:23:20.375Z","type":"美图","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhovjwwphfj20u00u04qp.jpg"},{"createdAt":"2015-06-16T01:02:36.279Z","publishedAt":"2015-06-16T05:56:27.410Z","type":"美图","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1et5nl9mno8j20hs0qoacj.jpg"},{"createdAt":"2017-02-19T18:36:27.16Z","publishedAt":"2017-02-20T11:56:22.616Z","type":"美图","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-19-16789884_666922016823652_4719569931841044480_n.jpg"},{"createdAt":"2015-11-20T02:08:21.327Z","publishedAt":"2015-11-20T03:54:49.822Z","type":"美图","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ey77s2wab8j20zk0nmdm2.jpg"},{"createdAt":"2016-04-22T12:17:38.977Z","publishedAt":"2016-04-22T12:18:52.507Z","type":"美图","url":"http://ww2.sinaimg.cn/large/610dc034gw1f35cxyferej20dw0i2789.jpg"},{"createdAt":"2016-11-04T08:25:04.30Z","publishedAt":"2016-11-04T11:48:56.654Z","type":"美图","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9frojtu31j20u00u0go9.jpg"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createdAt : 2016-08-09T11:12:51.255Z
         * publishedAt : 2016-08-09T11:30:16.672Z
         * type : 美图
         * url : http://ww1.sinaimg.cn/large/610dc034jw1f6nbm78pplj20dw0i2djy.jpg
         */

        private String createdAt;
        private String publishedAt;
        private String type;
        private String url;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
