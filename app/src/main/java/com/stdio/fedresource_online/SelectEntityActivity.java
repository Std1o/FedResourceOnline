package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectEntityActivity extends AppCompatActivity {

    public static String entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_entity);
    }

    public void toMandatoryDocumentsActivity(View view) {
        switch (view.getId()) {
            case R.id.btn_legal_entity:
                entity = getResources().getString(R.string.legal_entity);
                break;
            case R.id.btn_individual_entrepreneur:
                entity = getResources().getString(R.string.individual_entrepreneur);
                break;
            case R.id.btn_natural_person:
                entity = getResources().getString(R.string.natural_person);
                break;
        }
        System.out.println(entity);
        startActivity(new Intent(this, MandatoryDocumentsActivity.class));
    }
}
