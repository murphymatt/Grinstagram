package com.mattmurphy.grinstagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toNewActivityButton = (Button)findViewById(R.id.new_activity_button);
        toNewActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(v);
            }
        });
    }

    public void changeActivity(View view) {
        Intent intent = new Intent(this, ButtonActivity.class);
        startActivity(intent);
    }
}
