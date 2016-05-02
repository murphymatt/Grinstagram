package com.mattmurphy.grinstagram;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mattori on 3/7/16.
 */
public class LoadPicturesTask extends AsyncTask<ArrayAdapter, Void, List<Picture>> {

    private ArrayAdapter mAdapter;
    private Context mContext;
    private Exception mException;
    private List<User> mUsers;
    private ProgressBar mProgressBar;

    public LoadPicturesTask( Context context, List<User> users) {
        mContext = context;
        mUsers = users;
        mProgressBar = (ProgressBar)((Activity)context).findViewById(R.id.progress);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<Picture> doInBackground(ArrayAdapter... params) {
        try {
            mAdapter = params[0];

            List<Picture> pics = new ArrayList();
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder()
                    .url("http://www.cs.grinnell.edu/~birnbaum/grinstagram/grinstagram.json")
                    .build();
            Response res = client.newCall(req).execute();
            JSONObject root = new JSONObject(res.body().string());

            if (root.getString("status").equals("ok")) {
                JSONArray images = root.getJSONArray("images");
                for (int i = 0; i < images.length(); i++) {
                    JSONObject obj = images.getJSONObject(i);

                    ArrayList<String> comments = new ArrayList<>();
                    JSONArray arr = obj.getJSONArray("comments");
                    for (int n = 0; n < arr.length(); n++) {
                        comments.add(arr.getString(n));
                    }

                    User user = null;
                    int uid = obj.getInt("uid");
                    for (User u : mUsers) {
                        if (u.getUid() == uid) user = u;
                    }
                    if (user == null) {
                        Toast.makeText(mContext, "Error while parsing image " + obj.getInt("id"), Toast.LENGTH_LONG).show();
                    }

                    Picture p = new Picture(obj.getInt("id"), user, obj.getString("url"),
                            obj.getString("caption"), obj.getBoolean("liked"), obj.getInt("likes"), comments);
                    pics.add(p);
                }
            }

            return pics;
        } catch (Exception e) {
            Log.d("picloading", e.getMessage());
            cancel(true);
            mException = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Picture> pictures) {
        mAdapter.clear();
        mAdapter.addAll(pictures);
        mAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onCancelled() {
        Toast.makeText(mContext,
                "An error occurred while loading picture information:\n" + mException.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}
