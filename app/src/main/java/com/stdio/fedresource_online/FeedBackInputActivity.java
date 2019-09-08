package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stdio.fedresource_online.gmailHelper.GMailSender;

import java.io.ByteArrayOutputStream;

public class FeedBackInputActivity extends AppCompatActivity {

    public static String phone, mail;
    EditText etPhone, etMail;
    String message;
    String recipient = "kwork-stdio@mail.ru";
    String senderMail, senderPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_input);
        init();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        getRecipientFromDatabase(database);
        getSenderMailFromDatabase(database);
        getSenderPasswordFromDatabase(database);
    }

    private void getRecipientFromDatabase(FirebaseDatabase database) {
        DatabaseReference refDate = database.getReference("recipient");
        // Read from the database
        refDate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                recipient = dataSnapshot.getValue(String.class);
                Log.d("firebasee", "Value is: " + recipient);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebasee", "Failed to read value.", error.toException());
            }
        });
    }

    private void getSenderMailFromDatabase(FirebaseDatabase database) {
        DatabaseReference refDate = database.getReference("mail");
        // Read from the database
        refDate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                senderMail = dataSnapshot.getValue(String.class);
                Log.d("firebasee", "Value is: " + senderMail);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebasee", "Failed to read value.", error.toException());
            }
        });
    }

    private void getSenderPasswordFromDatabase(FirebaseDatabase database) {
        DatabaseReference refDate = database.getReference("password");
        // Read from the database
        refDate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                senderPassword = dataSnapshot.getValue(String.class);
                Log.d("firebasee", "Value is: " + senderPassword);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebasee", "Failed to read value.", error.toException());
            }
        });
    }

    private void init() {
        etPhone = findViewById(R.id.etPhone);
        etMail = findViewById(R.id.etMail);
    }

    public void toCompleteActivity(View view) {
        phone = etPhone.getText().toString();
        mail = etMail.getText().toString();
        System.out.println(phone + "\n" + mail);
        if (phone.isEmpty()) {
            Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show();
        }
        else if (mail.isEmpty()) {
            Toast.makeText(this, "Введите E-mail", Toast.LENGTH_SHORT).show();
        }
        else {
            sendMessage();
        }
    }

    private void sendMessage() {
        message = "Лицо: " + SelectEntityActivity.entity + "\nИНН: " + INNAndJobInputActivity.INN + "\nДолжность: " + INNAndJobInputActivity.job + "\nТелефон: " + phone + "\nE-mail: " + mail;
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Отправка данных");
        dialog.setMessage("Пожалуйста, подождите...");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteArrayOutputStream bosPassportPhoto = new ByteArrayOutputStream();
                    ByteArrayOutputStream bosRegistrationPhoto = new ByteArrayOutputStream();
                    ByteArrayOutputStream bosSNILSPhoto = new ByteArrayOutputStream();
                    Bitmap registrationPhoto = null;

                    if (UploadPassportPhotoActivity.passportPhoto == null) {
                        UploadPassportPhotoActivity.passportPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.no_image);
                    }
                    if (registrationPhoto == null) {
                        registrationPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.no_image);
                    }
                    if (UploadSNILSPhotoAcivity.snilsPhoto == null) {
                        UploadSNILSPhotoAcivity.snilsPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.no_image);
                    }
                    UploadPassportPhotoActivity.passportPhoto.compress(Bitmap.CompressFormat.JPEG, 100, bosPassportPhoto);
                    registrationPhoto.compress(Bitmap.CompressFormat.JPEG, 100, bosRegistrationPhoto);
                    UploadSNILSPhotoAcivity.snilsPhoto.compress(Bitmap.CompressFormat.JPEG, 100, bosSNILSPhoto);
                    byte[] dataPassportPhoto = bosPassportPhoto.toByteArray();
                    byte[] dataRegistrationPhoto = bosRegistrationPhoto.toByteArray();
                    byte[] dataSNILSPhoto = bosSNILSPhoto.toByteArray();

                    GMailSender sender = new GMailSender(senderMail, senderPassword);
                    sender.sendMail("Федресурс-онлайн",
                            dataPassportPhoto, dataRegistrationPhoto, dataSNILSPhoto, message,
                            senderMail,
                            recipient);
                    dialog.dismiss();
                    startActivity(new Intent(FeedBackInputActivity.this, CompleteActivity.class));
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}
