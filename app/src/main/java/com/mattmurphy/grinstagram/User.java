package com.mattmurphy.grinstagram;

/**
 * Created by Mattori on 4/17/16.
 */
public class User {

    private int mUid;
    private String mUsername;
    // private String mProfileUrl;

    public User(int uid, String uname /*, String profileUrl */) {
        mUid = uid;
        mUsername = uname;
        /* mProfileUrl = profileUrl; */
    }

    public int getUid() { return mUid; }
    public String getUsername() { return mUsername; }
    // public String getProfileUrl() { return mProfileUrl; }

    @Override
    public String toString() {
        return "User{uid: " + mUid + ", username: " + mUsername + "}";
    }
}
