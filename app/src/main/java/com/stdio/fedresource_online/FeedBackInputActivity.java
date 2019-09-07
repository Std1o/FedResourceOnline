package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FeedBackInputActivity extends AppCompatActivity {

    public static String phone, mail;
    EditText etPhone, etMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_input);
        init();
    }

    private void init() {
        etPhone = findViewById(R.id.etPhone);
        etMail = findViewById(R.id.etMail);
    }

    public void toCompleteActivity(View view) {
        phone = etPhone.getText().toString();
        mail = etMail.getText().toString();
        System.out.println(phone + "\n" + mail);
        startActivity(new Intent(this, CompleteActivity.class));
    }
}
