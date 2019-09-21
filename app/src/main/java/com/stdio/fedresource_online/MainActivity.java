package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBoxAgreement;
    MaterialButton materialButtonContinue;
    TextView tv_offer_contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
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

        tv_offer_contract = findViewById(R.id.tv_offer_contract);
        //Экранируем кавычки в атрибуте html тега слэшем:
        String textWithLink = "<a href=\"https://drive.google.com/open?id=1x0bQOwNsPR8QQAob7BeIsEGeYbPNo06-\">договора-оферты</a>";
//Указываем с помощью Html.fromHtml, что у нас не просто текст:
        tv_offer_contract.setText(Html.fromHtml(textWithLink, null, null));
////Указываем что разрешаем ссылки кликать:
        tv_offer_contract.setLinksClickable(true);
        tv_offer_contract.setMovementMethod(LinkMovementMethod.getInstance());
//Научаемся отлавливать клики пропустив текст через наш класс из пред. пункта.
        CharSequence text = tv_offer_contract.getText();
        if (text instanceof Spannable)
        {
            tv_offer_contract.setText(MakeLinksClicable.reformatText(text));
        }
    }

    public void toSecondActivity(View view) {
        startActivity(new Intent(this, SelectEntityActivity.class));
    }
}
