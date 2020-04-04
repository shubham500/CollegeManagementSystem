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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Delete extends Fragment {
    Button btnDelete;
    EditText  editRollno, editName, editMarks;
    //Spinner spinner;
    SQLiteDatabase sqLiteDatabase;
    Cursor c;


    public Delete() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View delete_layout = inflater.inflate(R.layout.fragment_delete, container, false);

        sqLiteDatabase =delete_layout.getContext().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS student(rollno NUMBER,name VARCHAR,marks VARCHAR);");

        editRollno=delete_layout.findViewById(R.id.editnumber1);


        btnDelete=delete_layout.findViewById(R.id.btndelete);




        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editRollno.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                c = sqLiteDatabase.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
                if (c.moveToFirst()) {
                    sqLiteDatabase.execSQL("DELETE FROM student WHERE rollno='" + editRollno.getText() + "'");
                    showMessage("Success", "Record Deleted");
                } else {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();

            }
        });
        return delete_layout;

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
       /* editName.setText("");
        editMarks.setText("");*/
    }
}
