package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("Post_Id")
    @Expose
    private Integer postId;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("Image")
    @Expose
    private Object image;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;
    @SerializedName("LikeNo")
    @Expose
    private Integer likeNo;
    @SerializedName("CommentNo")
    @Expose
    private Integer commentNo;
    @SerializedName("LikeState")
    @Expose
    private Boolean likeState;
    @SerializedName("User_Id")
    @Expose
    private String userId;
    private String diff = "";
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Integer getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(Integer likeNo) {
        this.likeNo = likeNo;
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public Boolean getLikeState() {
        return likeState;
    }

    public void setLikeState(Boolean likeState) {
        this.likeState = likeState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return get_W_M_Y(diff);
//            return diff / DAY_MILLIS + " days ago";
        }
    }

    public String get_W_M_Y(long diff) {
        long days = diff / DAY_MILLIS;
        String dayResult = "";
        if (days > 365) {
            dayResult = String.valueOf((int) days / 365 + " Year ago");
        } else if (days > 30) {
            dayResult = String.valueOf((int) days / 30 + " Mounth ago");
        } else if (days > 7) {
            dayResult = String.valueOf((int) days / 7 + " Weak ago");
        } else {
            dayResult = String.valueOf(days + " Day ago");
        }
        return dayResult;
    }


}

