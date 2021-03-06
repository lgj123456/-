package yls.example.com.video.model;

import java.util.List;

/**
 * Created by yhdj on 2017/5/25.
 */

public class CinemaBean {

    /**
     * reason : success
     * result : [{"id":"1190","cityName":"苏州","cinemaName":"苏州幸福蓝海IMAX影城","address":"苏州市广济南路219号新苏天地9楼","telephone":"0512-65899658","latitude":"31.30947","longitude":"120.6003","trafficRoutes":"","distance":0},{"id":"1196","cityName":"苏州","cinemaName":"金逸苏州石路永捷店","address":"苏州市姑苏区广济南路19号西城永捷生活广场6楼","telephone":"0512-65703050","latitude":"31.30411","longitude":"120.6012","trafficRoutes":"游1、7路、88路、204路、34路、","distance":603},{"id":"1201","cityName":"苏州","cinemaName":"苏州橙天嘉禾影城（来客茂店）","address":"苏州金阊区桐泾北路218号来客茂时尚生活广场4F","telephone":"0512-86860919","latitude":"31.31268","longitude":"120.5894","trafficRoutes":"311路、400路上行、游5路、317路、949路、441路、34路，到西园东站下车","distance":1097},{"id":"1210","cityName":"苏州","cinemaName":"苏州星美影城（李公堤店）即将开业","address":"苏州市工业园区李公堤四期一幢","telephone":"","latitude":"31.29889","longitude":"120.5853","trafficRoutes":"178、207、100、168路公交到达","distance":1850}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    /**
     * id : 1190
     * cityName : 苏州
     * cinemaName : 苏州幸福蓝海IMAX影城
     * address : 苏州市广济南路219号新苏天地9楼
     * telephone : 0512-65899658
     * latitude : 31.30947
     * longitude : 120.6003
     * trafficRoutes :
     * distance : 0
     */

    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String id;
        private String cityName;
        private String cinemaName;
        private String address;
        private String telephone;
        private String latitude;
        private String longitude;
        private String trafficRoutes;
        private int distance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getTrafficRoutes() {
            return trafficRoutes;
        }

        public void setTrafficRoutes(String trafficRoutes) {
            this.trafficRoutes = trafficRoutes;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
