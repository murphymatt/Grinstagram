package com.mattmurphy.grinstagram;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

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

        final Picture pic = ImageListAdapter.super.getItem(pos);
//        TextView tv = (TextView) convertView.findViewById(R.id.tv);
//        tv.setText(pic.getImageUrl());

        if (pic != null) {
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            ImageButton like = (ImageButton) convertView.findViewById(R.id.like);
            ImageButton share = (ImageButton) convertView.findViewById(R.id.share);
            TextView likeNum = (TextView) convertView.findViewById(R.id.likeNum);
            like.setImageResource(pic.isLiked() ? R.drawable.ic_favorite_black_24dp
                    : R.drawable.ic_favorite_border_black_24dp);
            likeNum.setText(Integer.toString(pic.getLikes()));

            // when like button is clicked, increment the likes
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pic.isLiked()) {
                        pic.decrementLikes();
                        pic.setLiked(false);
                        ((ImageButton) v).setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    } else {
                        pic.incrementLikes();
                        pic.setLiked(true);
                        ((ImageButton) v).setImageResource(R.drawable.ic_favorite_black_24dp);
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ImageLoadTask(getContext()).execute(pic);
                }
            });

            Glide.with(getContext()).load(pic.getImageUrl()).into(image);
            //comments.setText(pic.getComments());
        }

        return convertView;
    }
}
