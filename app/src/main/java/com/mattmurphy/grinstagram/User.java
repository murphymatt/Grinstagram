package com.mattmurphy.grinstagram;

/**
 * Created by Mattori on 4/17/16.
 */
public class User {

    private int mUid;
    private String mUsername;

    public User(int uid, String uname) {
        mUid = uid;
        mUsername = uname;
    }

    public int getUid() { return mUid; }
    public String getUsername() { return mUsername; }

    @Override
    public String toString() {
        return "User{uid: " + mUid + ", username: " + mUsername + "}";
    }
}
