package com.mattmurphy.grinstagram;

import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // create list and adapter from xml
        ListView list = (ListView) findViewById(android.R.id.list);
        final CommentListAdapter adapter = new CommentListAdapter(this, R.layout.comment_list_view);
        adapter.addAll(getIntent().getStringArrayListExtra(ImageListAdapter.EXTRA_COMMENTS));
        if (adapter != null) { list.setAdapter(adapter); }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // editText
        ImageButton addComment = (ImageButton) findViewById(R.id.send);
        addComment.setColorFilter(Color.WHITE);

        final EditText commenting = (EditText) findViewById(R.id.edit_comment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toAdd = commenting.getText().toString();
                if (toAdd != null && !toAdd.equals("")) { adapter.add(toAdd); }
                commenting.setText("");
            }
        });
    }
}
