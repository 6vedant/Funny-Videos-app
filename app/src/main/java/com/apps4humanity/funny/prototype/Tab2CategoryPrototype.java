package com.apps4humanity.funny.prototype;

/**
 * Created by vedant on 4/12/2018.
 */

public class Tab2CategoryPrototype {
    public String image_url, title_category, num_videos, desc;
    String key ;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Tab2CategoryPrototype(){}

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Tab2CategoryPrototype(String image_url, String title_category, String num_videos, String key, String desc) {
        this.image_url = image_url;
        this.title_category = title_category;
        this.num_videos = num_videos;
        this.key = key;
        this.desc = desc;

    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle_category() {
        return title_category;
    }

    public void setTitle_category(String title_category) {
        this.title_category = title_category;
    }

    public String getNum_videos() {
        return num_videos;
    }

    public void setNum_videos(String num_videos) {
        this.num_videos = num_videos;
    }
}
