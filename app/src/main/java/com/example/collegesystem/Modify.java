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

public class Modify extends Fragment {
    Button btnModify;
    EditText editRollno, editName, editMarks;
    //Spinner spinner;
    SQLiteDatabase sqLiteDatabase;
    Cursor c;
    String s;

    public Modify() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View modify_layout = inflater.inflate(R.layout.fragment_modify, container, false);

        editRollno=modify_layout.findViewById(R.id.editnumber1);
        editName=modify_layout.findViewById(R.id.editname2);
        editMarks=modify_layout.findViewById(R.id.editmarks3);
        btnModify=modify_layout.findViewById(R.id.btnmodify);

        sqLiteDatabase =modify_layout.getContext().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS student(rollno NUMBER,name VARCHAR,marks VARCHAR);");


        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editRollno.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                c=sqLiteDatabase.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
                if(c.moveToFirst())
                {
                    sqLiteDatabase.execSQL("UPDATE student SET name='"+editName.getText()+"',marks='"+editMarks.getText()+
                            "' WHERE rollno='"+editRollno.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();

            }
        });
        return modify_layout;
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
