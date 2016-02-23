package com.mattmurphy.grinstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Mattori on 2/22/16.
 *
 * Manages the image list items' views.
 */
public class ImageListAdapter extends ArrayAdapter<ImageListItem> {

    public ImageListAdapter(Context context, int resource, List<ImageListItem> objects) {
        super(context, resource, objects);
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
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.imagelistitem, parent, false);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        ImageButton like = (ImageButton) convertView.findViewById(R.id.like);
        ImageButton share = (ImageButton) convertView.findViewById(R.id.share);

        //...

        return convertView;
    }
}
