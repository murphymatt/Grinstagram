package com.mattmurphy.grinstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mattori on 2/22/16.
 *
 * Manages the image list items' views.
 */
public class ImageListAdapter extends ArrayAdapter<Picture> {

    public ImageListAdapter(Context context, int resource) {
        super(context, resource);

    }
    /**
     * TODO: set the contents of image, like, and share based on Firebase
     * @param pos Position in array
     * @param convertView The old view, if non-null
     * @param parent The parent ViewGroup
     * @return The image list item's View
     */
    public View getView(int pos, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.image_list_item, parent, false);
        }

        Picture pic = ImageListAdapter.super.getItem(pos);
//        TextView tv = (TextView) convertView.findViewById(R.id.tv);
//        tv.setText(pic.getImageUrl());

        if (pic != null) {
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            ImageButton like = (ImageButton) convertView.findViewById(R.id.like);
            /* Create comments button */

            Picasso.with(getContext()).load(pic.getImageUrl()).into(image);
        }

        return convertView;
    }
}
