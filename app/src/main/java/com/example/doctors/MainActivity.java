package com.example.doctors;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    static ArrayList<Doctor> docs = new ArrayList<Doctor>();
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private Button addBtn;
    private ImageView menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initList();
        recyclerView = findViewById(R.id.rv_doctors);
        adapter = new MyAdapter(this, docs);
        recyclerView.setAdapter(adapter);
        int img = docs.get(0).getImageView();
        Log.d("CheckImg", "Image = " + img);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        onClick();

        Log.d("checkList", "create");
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    void init(){
        addBtn = findViewById(R.id.btnAdd);
        menu = findViewById(R.id.menuIcon);
    }

    void initList(){
        docs.add(new Doctor("Бакыт Нурадилович", "+996706828105","Стоматолог", R.drawable.doc_default));
        docs.add(new Doctor("Искендер", "+996706828105","Хирург", R.drawable.doc_default));
        docs.add(new Doctor("Бакыт Нурадилович", "+996706828105","Стоматолог", R.drawable.doc_default));
        docs.add(new Doctor("Искендер", "+996706828105","Хирург", R.drawable.doc_default));
        docs.add(new Doctor("Бакыт Нурадилович", "+996706828105","Стоматолог", R.drawable.doc_default));
        docs.add(new Doctor("Искендер", "+996706828105","Хирург", R.drawable.doc_default));
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showMenu(v);
        }
    };






    void showMenu(View v) {
        PopupMenu menu = new PopupMenu(this, v);
        menu.inflate(R.menu.popup_menu);

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.visit:
                            Log.d("checkMenuClick", "Visit is clicked");
                            return true;
                        case R.id.chat:
                            Log.d("checkMenuClick", "Chat is clicked");
                            return true;
                        case R.id.call:
                            Log.d("checkMenuClick", "Call is clicked");
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel: "+R.id.txtNumber));
                            try {
                                startActivity(Intent.createChooser(callIntent, "a Call"));
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(MainActivity.this, "There is no call",
                                        Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        default:
                            return false;
                    }
                }
            });
        menu.show();
    }



    public void onClick(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                startActivity(new Intent(MainActivity.this, AddActivity.class));
                startActivity(intent);
            }
        });
    }

    public ArrayList<Doctor> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<Doctor> docs) {
        this.docs = docs;
    }
}

