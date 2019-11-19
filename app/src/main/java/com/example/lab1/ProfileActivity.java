


package com.example.lab1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ProfileActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText editText;
    private EditText editText2;
    private ImageView mImageButton;
    private Button chatButton;
    private Button wForcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilelogin);

        String email = getIntent().getStringExtra("emailAddress");

        mImageButton = (ImageView)findViewById(R.id.picButton);
        editText = (EditText) findViewById(R.id.editViewp1);
        editText2 = (EditText) findViewById(R.id.editViewp2);
        chatButton = (Button) findViewById(R.id.chatBtn);
        wForcast = (Button) findViewById(R.id.weatherforc);

        //        editText.setText(email);

        editText2.setText(email);

        mImageButton = findViewById(R.id.picButton);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            final int REQUEST_IMAGE_CAPTURE = 1;
            @Override
            public void onClick(View view) {

                dispatchTakePictureIntent();
            }

            private void dispatchTakePictureIntent(){
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }

        });

        Button chat = findViewById(R.id.chatBtn);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        Log.e(ACTIVITY_NAME, "In onCreate()");

        Button weatherForcast = findViewById(R.id.weatherforc);
        weatherForcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent weatherActivity = new Intent(ProfileActivity.this, WeatherForecast.class);
                startActivity(weatherActivity);
            }
        });

        Button goToToolbarTest = findViewById(R.id.toolbarTestButton);
        goToToolbarTest.setOnClickListener(btn -> {
            Intent goToToolbar = new Intent(ProfileActivity.this, ActvityToolBar.class);
            startActivity(goToToolbar);
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
        Log.e(ACTIVITY_NAME, "In onActivityResult()");
    }

}


