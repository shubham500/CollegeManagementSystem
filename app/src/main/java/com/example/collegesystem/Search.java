package com.example.collegesystem;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class Search extends Fragment {
    Button btnsearch;
    EditText editRollno, editName, editMarks;
    // Spinner spinner;
    SQLiteDatabase sqLiteDatabase;
    Cursor c;
    String s;

    public Search() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View search_layout = inflater.inflate(R.layout.fragment_search, container, false);

        editRollno = search_layout.findViewById(R.id.editnumber1);
        editName = search_layout.findViewById(R.id.editname2);
        editMarks = search_layout.findViewById(R.id.editmarks3);
        btnsearch = search_layout.findViewById(R.id.btnsearch);
        //btnAdd.setOnClickListener(this);

        sqLiteDatabase = search_layout.getContext().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS student(rollno NUMBER,name VARCHAR,marks VARCHAR);");

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editRollno.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Rollno");

                    return;
                }
                c = sqLiteDatabase.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
                if (c.moveToFirst()) {
                    editName.setText(c.getString(1));
                    editMarks.setText(c.getString(2));
                } else {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }
            }
        });

        return search_layout;
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        editRollno.setText("");
        editName.setText("");
        editMarks.setText("");
    }
}
