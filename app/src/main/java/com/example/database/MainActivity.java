package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etText;
    Button btAdd;
    ListView listView;

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.et_text);
        btAdd = findViewById(R.id.bt_add);
        listView = findViewById(R.id.List_View);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        arrayList = databaseHelper.getAllText();
        arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String text = etText.getText().toString();
              if(!text.isEmpty())
              {
                  if(databaseHelper.addText(text))
                  {
                      etText.setText("");
                      Toast.makeText(getApplicationContext(),"Data inserted !",Toast.LENGTH_SHORT).show();

                      arrayList.clear();
                      arrayList.addAll(databaseHelper.getAllText());
                      arrayAdapter.notifyDataSetChanged();
                      listView.invalidateViews();
                      listView.refreshDrawableState();

                  }
              }

            }
        });
    }
}