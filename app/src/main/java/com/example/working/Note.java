package com.example.working;

public class Note {

    public String title;
    public String content;

    Note(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public String toString() { return title; }
}
