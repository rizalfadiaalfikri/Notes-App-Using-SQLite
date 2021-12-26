package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotesActivity extends AppCompatActivity {

    EditText ed_title,ed_description;
    Button btn_add_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        ed_title = findViewById(R.id.ed_title);
        ed_description = findViewById(R.id.ed_description);
        btn_add_notes = findViewById(R.id.btn_add_notes);

        btn_add_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ed_title.getText().toString()) && !TextUtils.isEmpty(ed_description.getText().toString())) {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(AddNotesActivity.this);
                    dataBaseHelper.addNotes(ed_title.getText().toString(),ed_description.getText().toString());

                    Intent intent = new Intent(AddNotesActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddNotesActivity.this,"Success",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddNotesActivity.this,"Both Required Failed",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}