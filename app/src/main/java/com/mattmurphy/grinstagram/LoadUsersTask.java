package com.mattmurphy.grinstagram;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mattori on 4/21/16.
 */
public class LoadUsersTask extends AsyncTask<Void, Void, List<User>> {

    private Context mContext;
    private UserLoadResult mULR;
    private Exception mException;
    private ArrayAdapter mAdapter;

    public <U extends Context & UserLoadResult> LoadUsersTask(U parent, ArrayAdapter adapter) {
        mContext = parent;
        mULR = parent;
        mAdapter = adapter;
    }

    @Override
    protected List<User> doInBackground(Void... params) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder()
                    .url("http://www.cs.grinnell.edu/~birnbaum/grinstagram/grinstagram-users.json")
                    .build();
            Response res = client.newCall(req).execute();
            JSONObject root = new JSONObject(res.body().string());

            JSONArray usersJs = root.getJSONArray("users");
            List<User> users = new ArrayList<>();
            for (int i = 0; i < usersJs.length(); i++) {
                JSONObject user = usersJs.getJSONObject(i);
                users.add(new User(user.getInt("uid"), user.getString("uname"), user.getString("pic")));
            }
            return users;
        } catch (Exception e) {
            Log.d("userloading", e.getMessage());
            cancel(true);
            mException = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<User> users) {
        mULR.setUsers(users);
        new LoadPicturesTask(mContext, users).execute(mAdapter);
    }

    @Override
    protected void onCancelled() {
        Toast.makeText(mContext,
                "An error occurred while loading user information:\n" + mException.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}
