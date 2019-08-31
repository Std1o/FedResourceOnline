package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBoxAgreement;
    MaterialButton materialButtonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        materialButtonContinue.setEnabled(false);
    }

    private void initViews() {
        checkBoxAgreement = findViewById(R.id.checkBoxAgreement);
        materialButtonContinue = findViewById(R.id.btn_continue);

        checkBoxAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxAgreement.isChecked()) {
                    materialButtonContinue.setEnabled(true);
                }
                else {
                    materialButtonContinue.setEnabled(false);
                }
            }
        });
    }

    public void toSecondActivity(View view) {
        startActivity(new Intent(this, SelectEntity.class));
    }
}
