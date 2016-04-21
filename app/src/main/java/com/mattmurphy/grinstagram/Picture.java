package com.mattmurphy.grinstagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mattmurphy on 2/16/16.
 */

public class Picture {

    // Fields
    private int mId;
    private String mImageUrl;
    private ArrayList<String> mComments;
    private int mLikes;
    private boolean mLiked;
    private User mUser;

    // Constructor
    public Picture(int id, User user, String imageUrl) {
        mId = id;
        mImageUrl = imageUrl;
        mComments = new ArrayList<String>();
        mLikes = 0;
        mUser = user;
    }

    public Picture(int id, User user, String imageUrl, boolean liked, int likes, ArrayList<String> comments) {
        mId = id;
        mUser = user;
        mImageUrl = imageUrl;
        mLiked = liked;
        mLikes = likes;
        mComments = comments;
    }

    // Methods
    public String getImageUrl() {
        return mImageUrl;
    }

    public ArrayList<String> getComments() {
        return mComments;
    }

    public void addComments(String comment) {
        mComments.add(comment);
    }

    public int getLikes() {
        return mLikes;
    }

    public void incrementLikes() {
        mLikes++;
    }

    public void decrementLikes() {
        mLikes--;
    }

    public boolean isLiked() { return mLiked; }

    public void setLiked(boolean liked) { mLiked = liked; }

    public User getUser() { return mUser; }

}