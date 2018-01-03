package com.housemanager.domain;

import java.util.List;

/**
 * Created by silion on 2018/1/3.
 */

public class SelectHouse {

    private List<BannerBean> banner;
    private List<BrandBean> brand;
    private List<NewestBean> newest;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public List<NewestBean> getNewest() {
        return newest;
    }

    public void setNewest(List<NewestBean> newest) {
        this.newest = newest;
    }

    public static class BannerBean {
        /**
         * image : image/home01.jpg
         */

        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class BrandBean {
        /**
         * image : image/category_app_8.jpg
         * desc : 床前明月光，疑是地上霜
         */

        private String image;
        private String desc;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class NewestBean {
        /**
         * image : image/recommend_01.jpg
         * room : 301
         * price : 2000
         */

        private String image;
        private String room;
        private String price;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
