package com.example.myapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;


import java.util.ArrayList;


import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.ai;
import com.example.myapplication.listAdapter;

import java.util.ArrayList;

public class nutrient extends AppCompatActivity {


    TextView edtan,eddan,edgi,eddang,edna;
    ListView listView, listView2;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items1 = new ArrayList<String>();

    DBHelper dbHelper;
    SQLiteDatabase db2 = null;

    Cursor cursor;
    ArrayAdapter adapter;

    @SuppressWarnings("unchecked")
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrient);
        listView = findViewById(R.id.listView);

        edtan = (TextView)  findViewById(R.id.calctan);
        //eddan = (TextView) findViewById(R.id.calcdan);
        //edgi = (TextView) findViewById(R.id.calcgi);
        //eddang = (TextView) findViewById(R.id.calcdang);
        //edna = (TextView) findViewById(R.id.calcna);

        Button btn6 = (Button) findViewById(R.id.btn6);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ai.class);
                startActivity(intent);
                finish();
            }

        });

        dbHelper = new DBHelper(this, 3);
        db2 = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈
    }

    public void insert(View v) {
        Intent intent = getIntent();
        String tan = " ";
        String dan = " ";
        String gi = " ";
        String na = " ";
        String dang = " ";

        try {
            tan = intent.getStringExtra("tan");
            dan = intent.getStringExtra("dan");
            gi  = intent.getStringExtra("gi");
            na = intent.getStringExtra("na");
            dang = intent.getStringExtra("dang");

            db2.execSQL("INSERT INTO tableName VALUES ('" + tan + "', '" + dan + "','" + gi +"','" + na + "','" + dang + "');");
        } catch (NullPointerException e) {
            Toast.makeText(nutrient.this, "오류남", Toast.LENGTH_LONG).show();
        }

        cursor = db2.rawQuery("SELECT * FROM tableName", null);
        startManagingCursor(cursor);    //엑티비티의 생명주기와 커서의 생명주기를 같게 한다.

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        while (cursor.moveToNext()) {
            String tan1 = cursor.getString(0);
            String dan1 = cursor.getString(1);
            String gi1 = cursor.getString(2);
            String na1 = cursor.getString(3);
            String dang1 = cursor.getString(4);
            String result = tan1 + "\n" + dan1 + "\n" + gi1+ "\n" + na1+ "\n" + dang1;
            adapter.add(result);
        }

       String step1=cursor.getString(0);
       int step2=0;
       step2=parseInt(step1);
      edtan.setText(step2);
    }




    /*String hi=cursor.getString(0);
        int retan=0;
        try{
            retan= parseInt(hi);
        }catch (NumberFormatException nfe)
        {
            System.out.println(("없어요! 숫자가!"+nfe));
        }

        String a=adapter.getItem(0).toString();
        int retan=0;
        retan= parseInt(a);
        int resultant =0;
        int count = adapter.getCount();
        for (int i = 0; i < count; i++)
            resultant = resultant + retan;
        edtan.setText(resultant);
        }*/
    }

