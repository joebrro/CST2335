package com.example.lab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ChatRoomActivity extends AppCompatActivity {


    protected static final String ACTIVITY_NAME = "ChatRoomActivity";
    ArrayList<mess> messages = new ArrayList<>( );

    BaseAdapter myAdapter;


    private MyDatabaseActivity dbHelper;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatroom);

        ListView theList = findViewById(R.id.chat_lv);
        Button sendButton = findViewById(R.id.send_btn);
        Button receiveButton = findViewById(R.id.receive_btn);

        dbHelper = new MyDatabaseActivity(this);
        db = dbHelper.getWritableDatabase();

        String [] columns = {MyDatabaseActivity.COL_ID, MyDatabaseActivity.COL_MESSAGE, MyDatabaseActivity.COL_IS_SEND};
        Cursor results = db.query(false, MyDatabaseActivity.TABLE_NAME, columns, null, null, null, null, null, null);

        printCursor(results);

        int messageColumnIndex = results.getColumnIndex(MyDatabaseActivity.COL_MESSAGE);
        int isSendColIndex = results.getColumnIndex(MyDatabaseActivity.COL_IS_SEND);
        int idColIndex = results.getColumnIndex(MyDatabaseActivity.COL_ID);

        while(results.moveToNext())
        {
            String message = results.getString(messageColumnIndex);
            boolean is = Boolean.parseBoolean(results.getString(isSendColIndex));
            long id = results.getLong(idColIndex);

            messages.add(new mess(message, is, id));
        }

        theList.setAdapter( myAdapter = new MyListAdapter() );


        sendButton.setOnClickListener( click -> setMessage(true));
        receiveButton.setOnClickListener( click -> setMessage(false));

    }

    private mess messDB(boolean is){
        EditText editText = findViewById(R.id.chat_et);
        String message = editText.getText().toString();
        String strIsSend = Boolean.toString(is);

        //add to the database and get the new ID
        ContentValues newRowValues = new ContentValues();
        //put string name in the NAME column:
        newRowValues.put(MyDatabaseActivity.COL_MESSAGE, message);
        //put string email in the EMAIL column:
        newRowValues.put(MyDatabaseActivity.COL_IS_SEND, strIsSend);
        //insert in the database:
        long newId = db.insert(MyDatabaseActivity.TABLE_NAME, null, newRowValues);

        return new mess(message, is, newId);
    }
    private void setMessage(boolean is){
        EditText editText = findViewById(R.id.chat_et);
        if(!editText.getText().toString().equals("")){

            messages.add(messDB(is));
            myAdapter.notifyDataSetChanged(); //update yourself
            editText.setText("");
        }
    }
    private class mess{

        long id;
        String message;
        boolean is;

        mess(String message, boolean is, long id){
            this.message=message;
            this.is = is;
            this.id = id;
        }
    }


    private class MyListAdapter extends BaseAdapter {

        public int getCount() { return messages.size();  }

        public mess getItem(int position) { return messages.get(position);  }

        public long getItemId(int p) { return p; }

        public View getView(int p, View recycled, ViewGroup parent)
        {
            View thisRow = recycled;

            if(recycled == null)
                thisRow = getLayoutInflater().inflate(R.layout.table_main_layout, null);

            defineMessage(thisRow, getItem(p).message,getItem(p).is);

            return thisRow;
        }
    }

    private void defineMessage(View thisRow, String s, boolean is) {

        ImageButton image = thisRow.findViewById(R.id.image_chat);
        TextView messageText = thisRow.findViewById(R.id.numberField);
        RelativeLayout.LayoutParams layoutParamsImage = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams layoutParamsMessage = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        //set layout parameters for image
        layoutParamsImage.addRule(is? RelativeLayout.ALIGN_PARENT_LEFT
                : RelativeLayout.ALIGN_PARENT_RIGHT);
        //set layout parameters for message
        layoutParamsMessage.addRule(is?RelativeLayout.RIGHT_OF:RelativeLayout.LEFT_OF
                ,thisRow.findViewById(R.id.image_chat).getId());
        //set image
        image.setImageResource(is? R.drawable.row_send : R.drawable.row_receive);
        image.setLayoutParams(layoutParamsImage);
        //set message
        messageText.setText(s);
        messageText.setLayoutParams(layoutParamsMessage);
    }

    private void printCursor(Cursor c){
        Log.d("Chat Room", "Database Version NUmber: " + MyDatabaseActivity.VERSION_NUM);
        Log.d("Chat Room", "Number of Columns: " + c.getColumnCount());

        String colNames = "";
        for (String colName : c.getColumnNames()) {
            colNames = colNames + colName + ", ";
        }
        //clean the last comma
        Log.d("Chat Room", "Column Names: " + colNames.substring(0,colNames.length() - 2));

        Log.d("Chat Room", "Number of Results in the Cursor: " + c.getCount());

        while(c.moveToNext()){
            String results = "";
            for (String colName : c.getColumnNames()) {
                results = results + c.getString(c.getColumnIndex(colName)) + ", ";
            }
            Log.d("Chat Room", "Column Names: " + results.substring(0,results.length() - 2));
        }

        c.moveToFirst();
        c.moveToPrevious();


    }
}