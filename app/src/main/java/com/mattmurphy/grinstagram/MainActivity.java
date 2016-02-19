package com.mattmurphy.grinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        // create progress bar
        mProgressBar = new ProgressBar(this);
        mProgressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mProgressBar.setIndeterminate(true);
        ((ListView) findViewById(android.R.id.list)).setEmptyView(mProgressBar);

        // add progress bar to root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(mProgressBar);

        

    }

/*
    public void changeActivity(View view) {
        Intent intent = new Intent(this, ButtonActivity.class);
        startActivity(intent);
    }
*/


}
