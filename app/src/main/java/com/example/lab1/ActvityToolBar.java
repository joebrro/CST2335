/*package com.example.lab1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class ActvityToolBar extends AppCompatActivity {

    private Toolbar testToolbar;
    private String choice1Str = "This is the initial message";
    private String overflowMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_toolbar);

        testToolbar = findViewById(R.id.testToolbar);
        setSupportActionBar(testToolbar);
        overflowMessage = "You clicked on the overflow menu";

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.toolbar_menu, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.choice1:
                Toast.makeText(this, choice1Str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.choice2:
                View alertMiddle = getLayoutInflater().inflate(R.layout.activity_main_dialog, null);
                new AlertDialog.Builder(this)
                        .setView(alertMiddle)
                        .setPositiveButton("Set Message", (dialog, id) -> {
                            EditText alertET = alertMiddle.findViewById(R.id.alertET);
                            choice1Str = alertET.getText().toString();
                            Snackbar.make(testToolbar, "Choice 1 message changed", Snackbar.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Cancel", ((dialog, which) -> dialog.cancel()))
                        .create()
                        .show();
                break;
            case R.id.choice3:
                Snackbar.make(testToolbar, "Go Back?", Snackbar.LENGTH_SHORT)
                        .setAction("Going Back", sbBtn -> finish())
                        .show();
                break;
            case R.id.overflowChoice:
                Toast.makeText(this,
                        overflowMessage, Toast.LENGTH_SHORT).show();

        }
        return true;
    }
}
*/

package com.example.lab1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class ActvityToolBar extends AppCompatActivity {

    Toolbar testToolbar;
    String choice1Str = "This is the initial message";
    private String overflowMessage = "This is the overflow message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_toolbar);
        testToolbar= findViewById(R.id.testToolbar);
        setSupportActionBar(testToolbar);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.choice1:
                Toast.makeText(this, choice1Str , Toast.LENGTH_SHORT).show();
                break;
            case R.id.choice2:
                alertExample();
                break;
            case R.id.choice3:
                Snackbar sb = Snackbar.make(testToolbar, "Go Back?", Snackbar.LENGTH_LONG)
                        .setAction("Action text", e -> finish());
                sb.show();
                break;
            case R.id.overflowChoice:
                Toast.makeText(this, overflowMessage, Toast.LENGTH_SHORT).show();
                break;
        }

        return true;

    }

    public void alertExample(){
        View alertMiddle = this.getLayoutInflater().inflate(R.layout.activity_main_dialog, null);
        EditText alertET = alertMiddle.findViewById(R.id.alertET);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("The Message")
                .setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        choice1Str = alertET.getText().toString();
                    }
                })
                .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // What to do on Cancel
                    }
                }).setView(alertMiddle);

        builder.create().show();
    }
}




