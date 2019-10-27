package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

public class Message  {

    private String message;
    private int msgType;

    public static final int SEND = 0;
    public static final int RECEIVE = 1;

    public Message(String message, int msgType) {
        setMessage(message);
        setMsgType(msgType);
    }

    public Message(String message) {

        this(message,SEND);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}