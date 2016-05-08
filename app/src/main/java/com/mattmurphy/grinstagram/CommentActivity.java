package com.mattmurphy.grinstagram;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    private CommentListAdapter mAdapter;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // create list and mAdapter from xml
        ListView list = (ListView) findViewById(android.R.id.list);
        mAdapter = new CommentListAdapter(this, R.layout.comment_list_view);
        mAdapter.addAll(getIntent().getStringArrayListExtra(ImageListAdapter.EXTRA_COMMENTS));
        if (mAdapter != null) { list.setAdapter(mAdapter); }

        mIndex = getIntent().getIntExtra("index", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // editText
        ImageButton addComment = (ImageButton) findViewById(R.id.send);
        addComment.setColorFilter(Color.WHITE);

        final EditText commenting = (EditText) findViewById(R.id.edit_comment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toAdd = commenting.getText().toString();
                if (toAdd != null && !toAdd.equals("")) { mAdapter.add(toAdd); }
                commenting.setText("");
            }
        });
    }

    @Override
    protected void onStop() {
        Log.d("comment", "onStop");

        complete();

        super.onStop();
    }

    @Override
    public void onBackPressed() {
        Log.d("comment", "onBackPressed");

        complete();

        super.onBackPressed();
    }

    private void complete() {
        ArrayList<String> comments = new ArrayList<String>();
        for(int i = 0; i < mAdapter.getCount(); i++) {
            comments.add(mAdapter.getItem(i));
        }

        Log.d("comment", "onStop comments: " + comments);

        Intent data = new Intent();
        data.putStringArrayListExtra(ImageListAdapter.EXTRA_COMMENTS, comments);
        data.putExtra(ImageListAdapter.EXTRA_INDEX, mIndex);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
        }
        else Log.d("comment", "actionBar == null");
        return super.onCreateOptionsMenu(menu);
    }
}
