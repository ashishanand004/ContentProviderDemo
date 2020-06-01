package com.example.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(StudentProvider.name,
            ((EditText)findViewById(R.id.editText_name)).getText().toString());

        values.put(StudentProvider.grade,
            ((EditText)findViewById(R.id.editText_grade)).getText().toString());

        Uri uri = getContentResolver().insert(
            StudentProvider.content_uri, values);

        Toast.makeText(getBaseContext(),
            uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickFetchName(View view) {
        String URL = "content://com.example.MyApplication.StudentsProvider";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                    c.getString(c.getColumnIndex(StudentProvider.studentId)) +
                        ", " +  c.getString(c.getColumnIndex( StudentProvider.name)) +
                        ", " + c.getString(c.getColumnIndex( StudentProvider.grade)),
                    Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

}
