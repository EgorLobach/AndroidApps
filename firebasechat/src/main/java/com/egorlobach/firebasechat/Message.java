package com.egorlobach.firebasechat;

import java.util.Date;

public class Message {
    private String message;
    private String author;
    private long time;

    Message(String message, String author){
        this.message = message;
        this.author = author;

        time = new Date().getTime();
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public long getTime() {
        return time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
