package com.stdio.fedresource_online;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class UploadSNILSPhotoAcivity extends AppCompatActivity {

    ImageView ivSNILSPhoto;
    public static Bitmap snilsPhoto;
    MaterialButton btnSelectImage, btnDeleteImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_snilsphoto_acivity);
        init();
        btnDeleteImage.setEnabled(false);
    }

    private void init() {
        ivSNILSPhoto = findViewById(R.id.ivSNILSPhoto);
        btnSelectImage = findViewById(R.id.btnSelect);
        btnDeleteImage = findViewById(R.id.btnDelete);
    }

    public void selectImage(View view) {
        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        startActivityForResult(intent, 1213);
    }

    public void deleteImage(View view) {
        snilsPhoto = null;
        ivSNILSPhoto.setImageDrawable(getResources().getDrawable(R.drawable.snils_exmaple));
        btnDeleteImage.setEnabled(false);
        btnSelectImage.setEnabled(true);
    }

    public void toINNAndJobInputActivity(View view) {
        if (snilsPhoto != null) {
            startActivity(new Intent(this, INNAndJobInputActivity.class));
        }
        else {
            Toast.makeText(this, "Загрузите фото СНИЛС", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            snilsPhoto = selectedImage;
            ivSNILSPhoto.setImageBitmap(selectedImage);
            btnDeleteImage.setEnabled(true);
            btnSelectImage.setEnabled(false);
        }
    }
}
