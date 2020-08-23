package com.cs349.a4.notepad;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // floating action button
        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchEditActivity();
            }
        });

        // Lookup the recyclerview in activity layout
        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        int orientation = getResources().getConfiguration().orientation;
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myRecyclerView.getContext(),
                (orientation == 1) ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL);
        myRecyclerView.addItemDecoration(dividerItemDecoration);

        // Initialize contacts
        Context context = getApplicationContext();
        notes = Note.createNotesList(context);
        // Create adapter passing in the sample user data
        NoteAdapter adapter = new NoteAdapter(notes, context, this);
        // Attach the adapter to the recyclerview to populate items
        myRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void launchEditActivity(){
        launchEditActivity("");
    }

    public void launchEditActivity(String filename){
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("title", filename);
        startActivity(intent);
    }
}

