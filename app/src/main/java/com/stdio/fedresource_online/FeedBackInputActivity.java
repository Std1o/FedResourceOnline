package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FeedBackInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_input);
    }

    public void toCompleteActivity(View view) {
        startActivity(new Intent(this, CompleteActivity.class));
    }
}
