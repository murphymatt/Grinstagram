package com.mattmurphy.grinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class CommentActivity extends AppCompatActivity {

    Button addComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // create list and adapter from xml
        ListView list = (ListView) findViewById(android.R.id.list);
        final CommentListAdapter adapter = new CommentListAdapter(this, R.layout.comment_list_view);
        adapter.addAll(getIntent().getStringArrayListExtra(ImageListAdapter.EXTRA_COMMENTS));
        if (adapter != null) {
            list.setAdapter(adapter);
        }

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addComment = (Button) findViewById(R.id.add_comment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("New comment");
            }
        });
    }
}
