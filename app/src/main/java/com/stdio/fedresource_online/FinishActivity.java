package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class FinishActivity extends AppCompatActivity {

    String phone = "tel:88005553535";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
    }

    public void finishActivity(View view) {
        finish();
    }

    public void callIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse(phone));
        startActivity(intent);
    }
}
