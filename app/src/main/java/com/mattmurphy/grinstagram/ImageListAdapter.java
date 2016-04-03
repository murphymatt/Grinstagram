package com.mattmurphy.grinstagram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

        if (pic != null) {

            // create references to view items
            final ImageView image = (ImageView) convertView.findViewById(R.id.image);
            ImageButton like = (ImageButton) convertView.findViewById(R.id.like);
            ImageButton share = (ImageButton) convertView.findViewById(R.id.share);
            ImageButton download = (ImageButton) convertView.findViewById(R.id.download);
            final TextView likeNum = (TextView) convertView.findViewById(R.id.likeNum);
            like.setImageResource(pic.isLiked() ? R.drawable.ic_favorite_black_24dp
                    : R.drawable.ic_favorite_border_black_24dp);
            ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
            likeNum.setText(Integer.toString(pic.getLikes()));

            // when like button is clicked, increment the likes
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pic.isLiked()) {
                        pic.decrementLikes();
                        pic.setLiked(false);
                        ((ImageButton) v).setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        likeNum.setText(Integer.toString(pic.getLikes()));
                    } else {
                        pic.incrementLikes();
                        pic.setLiked(true);
                        ((ImageButton) v).setImageResource(R.drawable.ic_favorite_black_24dp);
                        likeNum.setText(Integer.toString(pic.getLikes()));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable d = image.getDrawable();
                    Bitmap b = ((GlideBitmapDrawable) d).getBitmap();
                    Log.d("share", "b.tostr: " + b.toString());
                    Intent share = new Intent();
                    String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(),
                            b, "Image desc", null);
                    Log.d("share", "path: " + path);
                    Uri uri = Uri.parse(path);
                    Log.d("share", "uri: " + uri);
                    share.setAction(Intent.ACTION_SEND);
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.setType("image/*");
                    getContext().startActivity(Intent.createChooser(share, "Share Image"));
                }
            });

            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    String fname = URLUtil.guessFileName(pic.getImageUrl(), null, "image/*");
                    File f = new File(dir, fname);
                    Drawable d = image.getDrawable();
                    Bitmap b = ((GlideBitmapDrawable) d).getBitmap();
                    try {
                        dir.mkdirs();

                        OutputStream os = new FileOutputStream(f);
                        b.compress(Bitmap.CompressFormat.PNG, 100, os);
                        os.close();

                        MediaScannerConnection.scanFile(getContext(),
                                new String[]{f.toString()}, null, null);

                        Toast.makeText(getContext(), "Saved " + fname + " successfully!", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(getContext(), "Error saving " + fname + "!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Glide.with(getContext()).load(pic.getImageUrl()).into(image);
            if (image != null) {
                progressBar.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}
