package com.mattmurphy.grinstagram;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ListView list = (ListView) findViewById(android.R.id.list);
        CommentListAdapter adapter = new CommentListAdapter(this, R.layout.comment_list_view);
        adapter.addAll(getIntent().getStringArrayListExtra(ImageListAdapter.EXTRA_COMMENTS));
        list.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
