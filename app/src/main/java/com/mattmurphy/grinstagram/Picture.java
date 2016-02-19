package com.mattmurphy.grinstagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mattmurphy on 2/16/16.
 */

public class Picture {

    // Fields
    private String mImageUrl;
    private List<String> mComments;
    private int mLikes;

    // Constructor
    public Picture(String imageUrl) {
        mImageUrl = imageUrl;
        mComments = new ArrayList<String>();
        mLikes = 0;
    }

    // Methods
    public String getImageUrl() {
        return mImageUrl;
    }

    public List<String> getComments() {
        return mComments;
    }

    public void addComments(String comment) {
        mComments.add(comment);
    }

    public int getLikes() {
        return mLikes;
    }

}