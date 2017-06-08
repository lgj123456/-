package yls.example.com.video.model;

/**
 * Created by yhdj on 2017/5/25.
 */

public class MovieDetailBean {

    /**
     * resultcode : 200
     * reason : 成功的返回
     * result : {"movieid":"137742","rating":"7","genres":"动作/冒险/科幻","runtime":"123分钟","language":"英语/日语","title":"哥斯拉","poster":"http://img31.mtime.cn/mg/2014/05/27/090708.36847286_270X405X4.jpg","writers":"麦克斯·鲍任斯坦,达夫·卡拉汉姆","film_locations":"美国|日本","directors":"加里斯·爱德华斯","rating_count":"12934","actors":"亚伦·泰勒-约翰逊 Aaron Taylor-Johnson,伊丽莎白·奥尔森 Elizabeth Olsen,渡边谦 Ken Watanabe,布莱恩·科兰斯顿 Bryan Cranston","plot_simple":"影片围绕一位人类大兵的生活展开，讲述了沉睡的古代巨型怪兽被人们意外唤醒，醒来后的怪兽展现出强大的破坏能力，它的存在也震惊了世界。影片同时强调了原子弹对生物带来的直接影响。","year":"2014","country":"美国|日本","type":null,"release_date":"20140613","also_known_as":""}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    /**
     * movieid : 137742
     * rating : 7
     * genres : 动作/冒险/科幻
     * runtime : 123分钟
     * language : 英语/日语
     * title : 哥斯拉
     * poster : http://img31.mtime.cn/mg/2014/05/27/090708.36847286_270X405X4.jpg
     * writers : 麦克斯·鲍任斯坦,达夫·卡拉汉姆
     * film_locations : 美国|日本
     * directors : 加里斯·爱德华斯
     * rating_count : 12934
     * actors : 亚伦·泰勒-约翰逊 Aaron Taylor-Johnson,伊丽莎白·奥尔森 Elizabeth Olsen,渡边谦 Ken Watanabe,布莱恩·科兰斯顿 Bryan Cranston
     * plot_simple : 影片围绕一位人类大兵的生活展开，讲述了沉睡的古代巨型怪兽被人们意外唤醒，醒来后的怪兽展现出强大的破坏能力，它的存在也震惊了世界。影片同时强调了原子弹对生物带来的直接影响。
     * year : 2014
     * country : 美国|日本
     * type : null
     * release_date : 20140613
     * also_known_as :
     */

    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private String movieid;
        private String rating;
        private String genres;
        private String runtime;
        private String language;
        private String title;
        private String poster;
        private String writers;
        private String film_locations;
        private String directors;
        private String rating_count;
        private String actors;
        private String plot_simple;
        private String year;
        private String country;
        private Object type;
        private String release_date;
        private String also_known_as;

        public String getMovieid() {
            return movieid;
        }

        public void setMovieid(String movieid) {
            this.movieid = movieid;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getGenres() {
            return genres;
        }

        public void setGenres(String genres) {
            this.genres = genres;
        }

        public String getRuntime() {
            return runtime;
        }

        public void setRuntime(String runtime) {
            this.runtime = runtime;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getWriters() {
            return writers;
        }

        public void setWriters(String writers) {
            this.writers = writers;
        }

        public String getFilm_locations() {
            return film_locations;
        }

        public void setFilm_locations(String film_locations) {
            this.film_locations = film_locations;
        }

        public String getDirectors() {
            return directors;
        }

        public void setDirectors(String directors) {
            this.directors = directors;
        }

        public String getRating_count() {
            return rating_count;
        }

        public void setRating_count(String rating_count) {
            this.rating_count = rating_count;
        }

        public String getActors() {
            return actors;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public String getPlot_simple() {
            return plot_simple;
        }

        public void setPlot_simple(String plot_simple) {
            this.plot_simple = plot_simple;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public String getAlso_known_as() {
            return also_known_as;
        }

        public void setAlso_known_as(String also_known_as) {
            this.also_known_as = also_known_as;
        }
    }
}
