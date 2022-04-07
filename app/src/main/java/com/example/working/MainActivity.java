package com.example.working;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Note> adapter; // note array
    ListView listView;
    Intent intent;
    Note note;
    int sel, pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1); // Create note array

        listView = findViewById(R.id.list_Note); // get list and attach note array to it
        listView.setAdapter(adapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (data != null) //user pressed "save" button
        {
            pos = data.getIntExtra("my-note-index", -1);
            String title = data.getStringExtra("my-note-title");
            String content = data.getStringExtra("my-note-content");

            note = adapter.getItem(pos);
            note.title = title;
            note.content = content;

            adapter.notifyDataSetChanged(); // update list box
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void OnNew(View v)   // when new note button clicked
    {
        note = new Note("", ""); // create new note

        adapter.add(note);
        pos = adapter.getPosition(note); // get it's position (array element index)


        intent = new Intent(this, MainActivity2.class);
        intent.putExtra("my-note-index", pos); // share note data with new activity
        intent.putExtra("my-note-title", note.title);
        intent.putExtra("my-note-content", note.content);
        startActivityForResult(intent, 1); // show note editing activity
    }
}