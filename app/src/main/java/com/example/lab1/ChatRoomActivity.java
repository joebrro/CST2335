package com.example.lab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ChatRoomActivity extends AppCompatActivity {


    protected static final String ACTIVITY_NAME = "ChatRoomActivity";

    private ArrayList<Message> chatMessage = new ArrayList<>();
    protected EditText chatEditText;
    private MyDatabaseActivity db;
    private SQLiteDatabase dbHelper;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatroom);


        Button sendButton = findViewById(R.id.send_btn);
        Button recieveButton = findViewById(R.id.receive_btn);
        ListView listView = findViewById(R.id.chat_lv);
        chatEditText = findViewById(R.id.chat_et); //used in inner class

        dbHelper = new MyDatabaseActivity(this);
        db = dbHelper.getWritabeleDtabase();

        String [] columns = {MyDatabaseActivity.COL_ID, MyDatabaseActivity.COL_MESSAGE, MyDatabaseActivity.COL_IS_SEND};
        Cursor results = db.query(false, MyDatabaseActivity.TABLE_NAME, columns, null, null, null, null, null, null);

        final ChatAdapter chatAdapter = new ChatAdapter(this);
        listView.setAdapter(chatAdapter);
        printCursor(results);

        Button btnSend = findViewById(R.id.send_btn);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatMessage.add(new Message(chatEditText.getText().toString(), Message.SEND));
                chatAdapter.notifyDataSetChanged();
                chatEditText.setText("");
            }
        });

        int messageColIndx = results.getColumnIndex(MyDatabaseActivity.COL_MESSAGE);
        int idColIndx = results.getColumnIndex(MyDatabaseActivity.COL_ID);
        int sendColIndx = results.getColumnIndex(MyDatabaseActivity.COL_IS_SEND);
        Button btnReceive = findViewById(R.id.receive_btn);
        btnReceive.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                chatMessage.add(new Message(chatEditText.getText().toString(), Message.RECEIVE));
                chatAdapter.notifyDataSetChanged();
                chatEditText.setText("");
            }
        });

        while (results.moveToNext()){
            String msg = results.getString(messageColIndx);
            long sin = results.getLong(idColIndx);
            boolean sendMsg = Boolean.parseBoolean(results.getString(sendColIndx));
            double sins = results.getLong(idColIndx);
            chatMessage.add(new Message(msg, sin, sendMsg));
        }

        listView.setAdapter(arrayAdapter = new ChatAdapter());

        sendButton.setOnClickListener(click -> setMessage(true));

        recieveButton.setOnClickListener(click -> setMessage(false));

    }

//Delegate(Delegation) pattern
//inner class ChatAdapter to provide data for the List View

    private void setMessage(boolean sendMsg){
        EditText editText = findViewById(R.id.chat_et);
        if(!editText.getText().toString().equals("")){

            chatMessage.add(addDBMessage(sendMsg));
            myAdapter.notifyDataSetChanged(); //update yourself
            editText.setText("");

        }
    }

    private Message addDBMessage(boolean sendMsg){
        EditText editText = findViewById(R.id.chat_et);
        String message = editText.getText().toString();
        String strIsSend = Boolean.toString(sendMsg);

        //add to the database and get the new ID
        ContentValues newRowValues = new ContentValues();
        //put string name in the NAME column:
        newRowValues.put(MyDatabaseActivity.COL_MESSAGE, message);
        //put string email in the EMAIL column:
        newRowValues.put(MyDatabaseActivity.COL_IS_SEND, strIsSend);
        //insert in the database:
        long newId = db.insert(MyDatabaseActivity.TABLE_NAME, null, newRowValues);

        return new Message(sendMsg, sendMsg, newId);
    }
    public class ChatAdapter extends ArrayAdapter<Message>{

        public ChatAdapter(ChatRoomActivity context){
            super(context,0);
        }

        @Override
        public Message getItem(int position) {
            return chatMessage.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return chatMessage.size();
        }

        public View getView(int position,View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatRoomActivity.this.getLayoutInflater();
            View view = null;

            switch(getItem(position).getMsgType()){
                case Message.SEND:
                    view = inflater.inflate(R.layout.layout_main_send, null);
                    break;
                case Message.RECEIVE:
                    view = inflater.inflate(R.layout.layout_main_recieve, null);
                    break;
            }

            TextView message = (TextView) view.findViewById(R.id.message_tv_chat);

            message.setText(getItem(position).getMessage());

            return view;
        }
    }
}


