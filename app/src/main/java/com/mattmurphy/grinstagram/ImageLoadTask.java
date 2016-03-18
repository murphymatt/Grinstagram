package com.mattmurphy.grinstagram;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mattori on 3/17/16.
 */
public class ImageLoadTask extends AsyncTask<Picture, Void, Void> {

    private Context mContext;

    public ImageLoadTask(Context context) {
        mContext = context;
    }


    @Override
    protected Void doInBackground(Picture... params) {
        for(Picture pic : params) {
            Bitmap b = null;
            try {
                b = Glide.with(mContext).load(pic.getImageUrl()).asBitmap()
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
            } catch (InterruptedException e) {
                Toast.makeText(mContext, "Could not share the image! (interrupted)\n" + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            } catch (ExecutionException e) {
                Toast.makeText(mContext, "Could not share the image! (execution error)\n" + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "title");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            Uri uri = mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    values);


            OutputStream outstream;
            try {
                outstream = mContext.getContentResolver().openOutputStream(uri);
                b.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                outstream.close();
            } catch (Exception e) {
                Toast.makeText(mContext, "Could not share the image! (image compression error)\n" + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }

            share.putExtra(Intent.EXTRA_STREAM, uri);
            mContext.startActivity(Intent.createChooser(share, "Share Image"));
        }
        return null;
    }

}
