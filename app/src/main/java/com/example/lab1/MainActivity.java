package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {


//    TextView TextView = findViewById(R.id.textLogin1);
//    TextView TextView2 = findViewById(R.id.textLogin2);
//    EditText editText = findViewById(R.id.editView);
//    EditText editText2 = findViewById(R.id.editView2);
//    Button saveButton =  findViewById(R.id.buttonLogin);
//    SharedPreferences prefs;

    private TextView textView;
    private TextView textView2;
    private EditText editText;
    private EditText editText2;
    private Button saveButton;

    public static final String SHARED_PREFS = "sPrefs";
    public static final String TEXT = "text";
    private String text;
    private String text2;




    @Override
    public SharedPreferences getPreferences(int mode) {
        return super.getPreferences(mode);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        textView = (TextView) findViewById(R.id.textLogin1);
        textView2 = (TextView) findViewById(R.id.textLogin2);
        editText = (EditText) findViewById(R.id.editView);
        editText2 = (EditText) findViewById(R.id.editView2);
        saveButton = (Button) findViewById(R.id.buttonLogin);



        SharedPreferences prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
        String previous = prefs.getString("useremail", "Default Value");

        editText.setText(previous);
        editText2.setText(previous);

        saveButton.setOnClickListener(clk -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("useremail", editText.getText().toString());
            editor.putBoolean("logged_in", true);

            editor.commit();


        });





//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText(editText.getText().toString());
//
//                saveData();
//            }
//        });
//
//        textView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText(editText2.getText().toString());
//                saveData();
//            }
//        });


    }

//    public void saveData() {
//
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(TEXT, textView.getText().toString());
//      //  editor.putString(TEXT, textView2.getText().toString());
//
//        editor.apply();
//        editor.commit();
//
//        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
//    }
//
//    public void loadData(){
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        text = sharedPreferences.getString(TEXT,"");
//
//    }




}









//        textLogin1 = (TextView) findViewById(R.id.textLogin1);
//        textLogin2 = (TextView) findViewById(R.id.textLogin2);
//        editView = (EditText) findViewById(R.id.editView);
//        editView2 = (EditText) findViewById(R.id.editView2);


//    //Print out the saved data
//    public  void displayData(View view){
//        SharedPreferences prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
//
//        String name = prefs.getString("username", "");
//        String pw = prefs.getString("password", "");
//        textLogin1.setText(name + " " + pw);
//    }
//





//
//      //find button 1 from what was loaded, add a click listener
//        Button page1Button = findViewById(R.id.buttonLogin);
//        if(page1Button != null)
//            page1Button.setOnClickListener(v -> {
//                Intent goToPage2 = new Intent(MainActivity.this, ProfileActivity.class);
//                startActivity(goToPage2);
//            });
