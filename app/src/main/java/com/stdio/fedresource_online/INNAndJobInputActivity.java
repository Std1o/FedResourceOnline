package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class INNAndJobInputActivity extends AppCompatActivity {

    public static String INN, job;
    EditText etINN, etJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innand_job_input);
        init();
    }

    private void init() {
        etINN = findViewById(R.id.etINN);
        etJob = findViewById(R.id.etJob);
    }
    
    public void showJobInfo(View view) {
        Toast.makeText(this, "Пример: генеральный директор", Toast.LENGTH_SHORT).show();
    }

    public void toFeedBackInputActivity(View view) {
        INN = etINN.getText().toString();
        job = etJob.getText().toString();
        System.out.println(INN + "\n" + job);
        if (INN.isEmpty()) {
            Toast.makeText(this, "Введите ИНН", Toast.LENGTH_SHORT).show();
        }
        else if (job.isEmpty()) {
            Toast.makeText(this, "Введите должность", Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(this, FeedBackInputActivity.class));
        }
    }
}
