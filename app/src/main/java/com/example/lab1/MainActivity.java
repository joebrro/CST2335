package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);


        Button btn = (Button)findViewById(R.id.button1);
        if(btn != null)
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i( "Message", "You clicked button 1");
                    btn.setText("You got me");

                }
            });

        CheckBox btn2 = (CheckBox)findViewById(R.id.checkBox);
        btn2.setOnCheckedChangeListener((buttonView, isChecked)-> {

                    boolean newvalue = isChecked;

                });
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });

//            Log.i("Message", "You clicked checkBox");
//            btn2.setText("you clicked button 2");
//                });


        // initiate a Switch
        Switch simpleSwitch = (Switch) findViewById(R.id.switchButton);
        // check current state of a Switch (true or false).
        Boolean switchState = simpleSwitch.isChecked();
//        simpleSwitch.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
//        simpleSwitch.setTextOff("Off"); // displayed text of the Switch whenever it is in unchecked i.e. off state

        EditText input = findViewById(R.id.textView2);

        ImageButton bt3 = (ImageButton)findViewById(R.id.imageButton);
        bt3.setOnClickListener(clk -> {

            String answer = input.getText().toString();
            Log.i("Message", "You typed:" + answer);
        });
    }
}
