package com.example.collegesystem;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Add extends Fragment{
    Button btnAdd;
    EditText editRollno, editName, editMarks;
   //Spinner spinner;
    SQLiteDatabase sqLiteDatabase;


    public Add() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View add_layout = inflater.inflate(R.layout.fragment_add, container, false);

       // sqLiteDatabase = add_layout.getContext().openOrCreateDatabase("StudentDB2", Context.MODE_PRIVATE, null);

        editRollno=add_layout.findViewById(R.id.editrollno1);
        editName=add_layout.findViewById(R.id.editname1);
        editMarks=add_layout.findViewById(R.id.editmarks1);
        btnAdd=add_layout.findViewById(R.id.btnAdd1);
        //btnAdd.setOnClickListener(this);

        sqLiteDatabase =add_layout.getContext().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS student(rollno NUMBER,name VARCHAR,marks VARCHAR);");

        //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS student(id NUMBER,name VARCHAR,bg VARCHAR,contact NUMBER,city VARCHAR);");
        
      /* spinner .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s= String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editRollno.getText().toString().trim().length()==0||
                        editName.getText().toString().trim().length()==0||
                        editMarks.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                    showMessage("Error", "Please enter all values");
                    return;
                }
                sqLiteDatabase.execSQL("INSERT INTO student VALUES('" + editRollno.getText() + "','" + editName.getText() +
                        "','" + editMarks.getText() + "');");
                showMessage("Success", "Record added");
                clearText();
            }
        });

        return add_layout;

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
