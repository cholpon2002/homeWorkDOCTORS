package com.example.doctors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText room;
    private EditText specialty;
    private Button addBtn;
    private ArrayList<Doctor> doctors;
    private Doctor doctor;
    private Button backBtn;
    private MainActivity mainActivity;
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_doctor);
        init();
        clickListener();
    }

    public void clickListener(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameD = name.getText().toString();
                String roomD = room.getText().toString();
                String specD = specialty.getText().toString();

                doctor = new Doctor(nameD, roomD, specD, R.drawable.doc_default);
//                mainActivity = new MainActivity();
                MainActivity.docs.add(doctor);

                Log.d("checkList", "List is "+MainActivity.docs.get(MainActivity.docs.size()-1).getName());


            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
//    public void back(View view){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }

    void init(){
        name = findViewById(R.id.nameEd);
        room = findViewById(R.id.roomEd);
        specialty = findViewById(R.id.specialityEd);
        addBtn = findViewById(R.id.addButton);
        backBtn = findViewById(R.id.backButton);
    }
}
