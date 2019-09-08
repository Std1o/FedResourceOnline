package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinishActivity extends AppCompatActivity {

    String phone = "tel:88005553535";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        getPhoneFromDatabase(database);
    }

    private void getPhoneFromDatabase(FirebaseDatabase database) {
        DatabaseReference refDate = database.getReference("phone");
        // Read from the database
        refDate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                phone = "tel:" + dataSnapshot.getValue(Long.class);
                Log.d("firebasee", "Value is: " + phone);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebasee", "Failed to read value.", error.toException());
            }
        });
    }

    public void finishActivity(View view) {
        finishAffinity();
    }

    public void callIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse(phone));
        startActivity(intent);
    }
}
