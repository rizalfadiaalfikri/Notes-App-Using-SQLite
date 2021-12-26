package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNotesActivity extends AppCompatActivity {

    EditText ed_name,ed_description;
    Button btn_update_notes;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);

        ed_name = findViewById(R.id.ed_title);
        ed_description = findViewById(R.id.ed_description);
        btn_update_notes = findViewById(R.id.btn_update_notes);

        Intent intent = getIntent();
        ed_name.setText(intent.getStringExtra("title"));
        ed_description.setText(intent.getStringExtra("description"));

        id = intent.getStringExtra("id");

        btn_update_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ed_name.getText().toString()) && !TextUtils.isEmpty(ed_description.getText().toString())) {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(UpdateNotesActivity.this);
                    dataBaseHelper.update(id,ed_name.getText().toString(),ed_description.getText().toString());

                    Intent intent1 = new Intent(UpdateNotesActivity.this,MainActivity.class);
                    intent1.setFlags(intent1.FLAG_ACTIVITY_CLEAR_TASK | intent1.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);

                    Toast.makeText(UpdateNotesActivity.this,"Update Successfull",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateNotesActivity.this,"Both Required Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}