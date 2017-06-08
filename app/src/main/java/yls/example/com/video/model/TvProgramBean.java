package yls.example.com.video.model;

import java.util.List;

/**
 * Created by yhdj on 2017/5/25.
 */

public class TvProgramBean {

    /**
     * result : [{"cName":"CCTV-5 体育","pName":"2016年欧洲足球锦标赛-决赛阶段抽签仪式","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=0","time":"2015-12-13 00:30"},{"cName":"CCTV-5 体育","pName":"2015/2016赛季花滑大奖赛总决赛-女单自由滑等","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=1","time":"2015-12-13 02:15"},{"cName":"CCTV-5 体育","pName":"赛事集锦-2015/2016赛季英格兰足球超级联赛第15轮比赛集锦","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=2","time":"2015-12-13 05:00"},{"cName":"CCTV-5 体育","pName":"健身动起来","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=3","time":"2015-12-13 06:00"},{"cName":"CCTV-5 体育","pName":"体育晨报","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=4","time":"2015-12-13 06:30"},{"cName":"CCTV-5 体育","pName":"实况录像-2015年国际乒联职业巡回赛总决赛","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=5","time":"2015-12-13 08:35"},{"cName":"CCTV-5 体育","pName":"2015/2016赛季NBA常规赛-勇士-雄鹿","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=6","time":"2015-12-13 09:30"},{"cName":"CCTV-5 体育","pName":"体坛快讯","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=7","time":"2015-12-13 12:00"},{"cName":"CCTV-5 体育","pName":"2015年世界职业拳王争霸赛-47","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=8","time":"2015-12-13 12:30"},{"cName":"CCTV-5 体育","pName":"2015年国际足联俱乐部世界杯1/4决赛1-（墨西哥美洲队-中国广州恒大队）","pUrl":"http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=9","time":"2015-12-13 14:00"},{"cName":"CCTV-5 体育","pName":"2015年国际泳联花样游泳大奖赛-集体自由自选决赛等","pUrl":"http://tv.cntv.cn/live/cctv5","time":"2015-12-13 17:00"},{"cName":"CCTV-5 体育","pName":"体育新闻","pUrl":"","time":"2015-12-13 18:00"},{"cName":"CCTV-5 体育","pName":"实况录像-2015/2016赛季短道速滑世界杯上海站（录播）","pUrl":"","time":"2015-12-13 18:30"},{"cName":"CCTV-5 体育","pName":"2015/2016赛季中国男子篮球职业联赛-江苏肯帝亚-新疆天山农商银行","pUrl":"","time":"2015-12-13 19:30"},{"cName":"CCTV-5 体育","pName":"2015年国际乒联职业巡回赛-总决赛-男女单打半决赛","pUrl":"","time":"2015-12-13 21:30"}]
     * error_code : 0
     * reason : Succes
     */

    private int error_code;
    private String reason;
    /**
     * cName : CCTV-5 体育
     * pName : 2016年欧洲足球锦标赛-决赛阶段抽签仪式
     * pUrl : http://tv.cntv.cn/live/cctv5?date=2015-12-13&index=0
     * time : 2015-12-13 00:30
     */

    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String cName;
        private String pName;
        private String pUrl;
        private String time;

        public String getCName() {
            return cName;
        }

        public void setCName(String cName) {
            this.cName = cName;
        }

        public String getPName() {
            return pName;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }

        public String getPUrl() {
            return pUrl;
        }

        public void setPUrl(String pUrl) {
            this.pUrl = pUrl;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
