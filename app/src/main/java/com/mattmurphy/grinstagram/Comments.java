package com.mattmurphy.grinstagram;

import java.util.List;

/**
 * Created by mattmurphy on 3/10/16.
 */
public class Comments {
    private List<String> mComments;

    public Comments(List<String> comments) {
        mComments = comments;
    }

    public List<String> getComments() { return mComments; }

    public void addComment(String str) { mComments.add(str); }
}
