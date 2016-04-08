package com.mattmurphy.grinstagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Mattori on 4/7/16.
 */
public class CommentListAdapter extends ArrayAdapter<String> {
    public CommentListAdapter(Context context, int resource) { super(context, resource); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.comment_list_view, parent, false);
        }

        String comment = CommentListAdapter.super.getItem(position);
        Log.d("comment", comment);

        if(comment != null) {
            Log.d("comment", "comment is not null");
            TextView content = (TextView) convertView.findViewById(android.R.id.content);
            content.setText(comment);
        }

        return convertView;
    }
}
