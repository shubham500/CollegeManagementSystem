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

public class ViewAll extends Fragment {
    Button btnviewall;
    //EditText editId, editName, editContact, editCity;
  //  Spinner spinner;
    SQLiteDatabase sqLiteDatabase;
    Cursor c;
   // String s;

    public ViewAll() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewall_layout = inflater.inflate(R.layout.fragment_viewall, container, false);


        btnviewall = viewall_layout.findViewById(R.id.btnviewall);

        sqLiteDatabase = viewall_layout.getContext().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS student(rollno NUMBER,name VARCHAR,marks VARCHAR);");


        btnviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=sqLiteDatabase.rawQuery("SELECT * FROM student", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Rollno: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Marks: "+c.getString(2)+"\n\n");
                }
                showMessage("Student Details", buffer.toString());


            }
        });

        return viewall_layout;
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
