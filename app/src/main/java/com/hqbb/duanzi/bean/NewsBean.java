package com.hqbb.duanzi.bean;

import java.util.List;

/**
 * Created by ylh on 2018/1/12 0012.
 */

public class NewsBean {

    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"status":"0","msg":"ok","result":{"channel":"娱乐","num":"1","list":[{"title":"周扬青服饰被控抄袭 罗志祥穿上节目助攻","time":"2018-01-12 08:14:23","src":"新浪娱乐","category":"ent","pic":"http://api.jisuapi.com/news/upload/201801/12100010_63133.jpg","content":"<p class=\"art_p\">新浪娱乐讯 据台湾媒体报道，网红周扬青是许多年轻人的时尚指标，开创自己的品牌，男友罗志祥[微博]（小猪）也常身穿她的衣服拍照，帮忙做活广告，不过，现在却有网友指出周涉嫌抄袭！<\/p><p class=\"art_p\">小猪和周扬青的恋情受到关注，他们虽然不大方放闪，但在网络上的一举一动都不难看出俩人好感情，每次周推出服装新款式，小猪都会穿上打广告，不过，最近其中一款服装遭指抄袭华晨宇[微博]歌迷服，原创设计师凌晨发文\u201c这样不太好。\u201d更有歌迷指出，这次抄袭不是头一遭。<\/p><p class=\"art_p\">周扬青最近发表一款帽T，小猪身穿该款，造型是两边袖子有白色条纹设计成绷带风格，这款衣服巧和华晨宇和设计师金翀宇设计的衣服极为相似。<\/p><p class=\"art_p\">金翀宇设计的衣服于2016年推出，属于限量发售的歌迷应援服，设计师11日凌晨发文\u201c只说衣服\u2026\u2026这样不太好\u201d，随后3人接续删除争议微博，周扬青则是松了一口气再度PO文\u201c差点因为自己的疏忽，犯了一个大错，还好及时发现解决了。\u201d<\/p>","url":"http://ent.sina.cn/star/hk_tw/2018-01-12/detail-ifyqqieu5861046.d.html?cre=tianyi&mod=went&loc=12&r=25&doct=0&rfunc=100&tj=none&tr=25","weburl":"http://ent.sina.com.cn/s/h/2018-01-12/doc-ifyqqieu5861046.shtml"}]}}
     */

    private String code;
    private boolean charge;
    private String msg;
    private ResultBeanX result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBeanX getResult() {
        return result;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    public static class ResultBeanX {
        /**
         * status : 0
         * msg : ok
         * result : {"channel":"娱乐","num":"1","list":[{"title":"周扬青服饰被控抄袭 罗志祥穿上节目助攻","time":"2018-01-12 08:14:23","src":"新浪娱乐","category":"ent","pic":"http://api.jisuapi.com/news/upload/201801/12100010_63133.jpg","content":"<p class=\"art_p\">新浪娱乐讯 据台湾媒体报道，网红周扬青是许多年轻人的时尚指标，开创自己的品牌，男友罗志祥[微博]（小猪）也常身穿她的衣服拍照，帮忙做活广告，不过，现在却有网友指出周涉嫌抄袭！<\/p><p class=\"art_p\">小猪和周扬青的恋情受到关注，他们虽然不大方放闪，但在网络上的一举一动都不难看出俩人好感情，每次周推出服装新款式，小猪都会穿上打广告，不过，最近其中一款服装遭指抄袭华晨宇[微博]歌迷服，原创设计师凌晨发文\u201c这样不太好。\u201d更有歌迷指出，这次抄袭不是头一遭。<\/p><p class=\"art_p\">周扬青最近发表一款帽T，小猪身穿该款，造型是两边袖子有白色条纹设计成绷带风格，这款衣服巧和华晨宇和设计师金翀宇设计的衣服极为相似。<\/p><p class=\"art_p\">金翀宇设计的衣服于2016年推出，属于限量发售的歌迷应援服，设计师11日凌晨发文\u201c只说衣服\u2026\u2026这样不太好\u201d，随后3人接续删除争议微博，周扬青则是松了一口气再度PO文\u201c差点因为自己的疏忽，犯了一个大错，还好及时发现解决了。\u201d<\/p>","url":"http://ent.sina.cn/star/hk_tw/2018-01-12/detail-ifyqqieu5861046.d.html?cre=tianyi&mod=went&loc=12&r=25&doct=0&rfunc=100&tj=none&tr=25","weburl":"http://ent.sina.com.cn/s/h/2018-01-12/doc-ifyqqieu5861046.shtml"}]}
         */

        private String status;
        private String msg;
        private ResultBean result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * channel : 娱乐
             * num : 1
             * list : [{"title":"周扬青服饰被控抄袭 罗志祥穿上节目助攻","time":"2018-01-12 08:14:23","src":"新浪娱乐","category":"ent","pic":"http://api.jisuapi.com/news/upload/201801/12100010_63133.jpg","content":"<p class=\"art_p\">新浪娱乐讯 据台湾媒体报道，网红周扬青是许多年轻人的时尚指标，开创自己的品牌，男友罗志祥[微博]（小猪）也常身穿她的衣服拍照，帮忙做活广告，不过，现在却有网友指出周涉嫌抄袭！<\/p><p class=\"art_p\">小猪和周扬青的恋情受到关注，他们虽然不大方放闪，但在网络上的一举一动都不难看出俩人好感情，每次周推出服装新款式，小猪都会穿上打广告，不过，最近其中一款服装遭指抄袭华晨宇[微博]歌迷服，原创设计师凌晨发文\u201c这样不太好。\u201d更有歌迷指出，这次抄袭不是头一遭。<\/p><p class=\"art_p\">周扬青最近发表一款帽T，小猪身穿该款，造型是两边袖子有白色条纹设计成绷带风格，这款衣服巧和华晨宇和设计师金翀宇设计的衣服极为相似。<\/p><p class=\"art_p\">金翀宇设计的衣服于2016年推出，属于限量发售的歌迷应援服，设计师11日凌晨发文\u201c只说衣服\u2026\u2026这样不太好\u201d，随后3人接续删除争议微博，周扬青则是松了一口气再度PO文\u201c差点因为自己的疏忽，犯了一个大错，还好及时发现解决了。\u201d<\/p>","url":"http://ent.sina.cn/star/hk_tw/2018-01-12/detail-ifyqqieu5861046.d.html?cre=tianyi&mod=went&loc=12&r=25&doct=0&rfunc=100&tj=none&tr=25","weburl":"http://ent.sina.com.cn/s/h/2018-01-12/doc-ifyqqieu5861046.shtml"}]
             */

            private String channel;
            private String num;
            private List<ListBean> list;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * title : 周扬青服饰被控抄袭 罗志祥穿上节目助攻
                 * time : 2018-01-12 08:14:23
                 * src : 新浪娱乐
                 * category : ent
                 * pic : http://api.jisuapi.com/news/upload/201801/12100010_63133.jpg
                 * content : <p class="art_p">新浪娱乐讯 据台湾媒体报道，网红周扬青是许多年轻人的时尚指标，开创自己的品牌，男友罗志祥[微博]（小猪）也常身穿她的衣服拍照，帮忙做活广告，不过，现在却有网友指出周涉嫌抄袭！</p><p class="art_p">小猪和周扬青的恋情受到关注，他们虽然不大方放闪，但在网络上的一举一动都不难看出俩人好感情，每次周推出服装新款式，小猪都会穿上打广告，不过，最近其中一款服装遭指抄袭华晨宇[微博]歌迷服，原创设计师凌晨发文“这样不太好。”更有歌迷指出，这次抄袭不是头一遭。</p><p class="art_p">周扬青最近发表一款帽T，小猪身穿该款，造型是两边袖子有白色条纹设计成绷带风格，这款衣服巧和华晨宇和设计师金翀宇设计的衣服极为相似。</p><p class="art_p">金翀宇设计的衣服于2016年推出，属于限量发售的歌迷应援服，设计师11日凌晨发文“只说衣服……这样不太好”，随后3人接续删除争议微博，周扬青则是松了一口气再度PO文“差点因为自己的疏忽，犯了一个大错，还好及时发现解决了。”</p>
                 * url : http://ent.sina.cn/star/hk_tw/2018-01-12/detail-ifyqqieu5861046.d.html?cre=tianyi&mod=went&loc=12&r=25&doct=0&rfunc=100&tj=none&tr=25
                 * weburl : http://ent.sina.com.cn/s/h/2018-01-12/doc-ifyqqieu5861046.shtml
                 */

                private String title;
                private String time;
                private String src;
                private String category;
                private String pic;
                private String content;
                private String url;
                private String weburl;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getWeburl() {
                    return weburl;
                }

                public void setWeburl(String weburl) {
                    this.weburl = weburl;
                }
            }
        }
    }
}
