package com.example.lab1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    public static final String ACTIVITY_NAME = "ProfileActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    //    @Override
//    public SharedPreferences getPreferences(int mode) {
//        return super.getPreferences(mode);
//    }
    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilelogin);
        Log.i(ACTIVITY_NAME, "onCreate()");


        editText = (EditText) findViewById(R.id.editViewp1);
        editText2 = (EditText) findViewById(R.id.editViewp2);

//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"email@example.com"});
//        intent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
//        intent.putExtra(Intent.EXTRA_TEXT, "body text");
//        startActivity(intent);


        //find button 1 from what was loaded, add a click listener
        ImageButton page2Button = (ImageButton) findViewById(R.id.picButton);
        if (page2Button != null)
            page2Button.setOnClickListener(v -> {
                Intent goToPage2 = new Intent(ProfileActivity.this, MainActivity.class);

                goToPage2.setType("text/plain");
                goToPage2.putExtra(Intent.EXTRA_EMAIL, new String[]{"email@example.com"});
                goToPage2.putExtra(Intent.EXTRA_SUBJECT, "subject here");
                goToPage2.putExtra(Intent.EXTRA_TEXT, "body text");
                startActivity(goToPage2);

                startActivityForResult(goToPage2, 30);
                dispatchTakePictureIntent();

            });


////        Button save = findViewById(R.id.saveButton);
////        save.setOnClickListener( bt -> {
////            startActivity(new Intent(ProfileActivity.this, .class));
////        });
//
//        Button picButt = findViewById(R.id.picButton);
//        picButt.setOnClickListener(clk -> {
//
//
//            finish();
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 30) {
            Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
        }
        if (resultCode == Activity.RESULT_OK) {
            String messagePassed = data.getStringExtra("Response");
            Toast.makeText(getApplicationContext(), getString(R.string.enterYourEmail) + messagePassed, Toast.LENGTH_LONG).show();
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageButton = null;
            mImageButton.setImageBitmap(imageBitmap);
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "onResume()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(ACTIVITY_NAME, "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME, "onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "onDestroy()");
    }

}