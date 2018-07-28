package com.apps4humanity.funny.prototype;

/**
 * Created by vedant on 3/27/2018.
 */

public class VideosRecyclerViewPrototype  {

    public String video_id;

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String title,thumb,vid,time;
    long view;


    public VideosRecyclerViewPrototype(){}

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public VideosRecyclerViewPrototype(String video_id, String title, String thumb, String vid, String time, long view) {
        this.title = title;
        this.thumb = thumb;
        this.vid = vid;
        this.time = time;
        this.view = view;
        this.video_id = video_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
