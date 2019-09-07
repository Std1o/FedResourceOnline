package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class INNAndJobInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innand_job_input);
    }
    
    public void showJobInfo(View view) {
        Toast.makeText(this, "Пример: генеральный директор", Toast.LENGTH_SHORT).show();
    }

    public void toFeedBackInputActivity(View view) {
        startActivity(new Intent(this, FeedBackInputActivity.class));
    }
}
