package com.mattmurphy.grinstagram;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

import com.parse.*;

/**
 * Created by mattmurphy on 2/16/16.
 */

@ParseClassName("Picture")
public class Picture extends ParseObject {

    private Image mImage;
    private List<String> mComments;
    private int mLikes;

    public Picture() {
        mComments = new ArrayList<String>();
        mLikes = 0;
    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
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

    public void setLikes(int likes) {
        mLikes = likes;
    }

}
