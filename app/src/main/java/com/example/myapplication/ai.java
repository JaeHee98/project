
package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;


public class ai extends AppCompatActivity {
    /*데이터베이스 저장 구역
     * foodsearch화면에서 리스트 클릭시 그 내용들이 데이터베이스에 저장되어 이 화면에
     * 리스트로 나타나게 하는 영역*/
    EditText editText, editText2;
    ListView listView, listView2;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items1 = new ArrayList<String>();

    DBHelper dbHelper;
    SQLiteDatabase db = null;

    Cursor cursor;
    ArrayAdapter adapter;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.ai);

        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(this, 3);
        db = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈

    }

    public void insert(View v) {//데이터베이스 칼로리 정보삽입구역

        Intent intent = getIntent();
        String name = " ";
        String kcal =" ";


        try {
            name = intent.getStringExtra("name");
            kcal = intent.getStringExtra("kcal");

            db.execSQL("INSERT INTO tableName VALUES ('" + name + "', '" + kcal + "');");
        }catch (NullPointerException e) {
            Toast.makeText(ai.this, "오류남", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_SHORT).show();

        cursor = db.rawQuery("SELECT * FROM tableName", null);
        startManagingCursor(cursor);    //엑티비티의 생명주기와 커서의 생명주기를 같게 한다.

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        while (cursor.moveToNext()) {
            String name1 = cursor.getString(0);
            String kcal1 = cursor.getString(1);
            String result = name1 + "\n" + kcal1;
            adapter.add(result);
        }

        /*
        cursor.moveToFirst();
        cursor.moveToPrevious();
        cursor.moveToPosition(2);
        */

        listView.setAdapter(adapter);
    }

    public void delete(View v) {//데이터베이스 칼로리 정보 삭제구역
        String sqlDelete = "DELETE FROM tableName";
        db.execSQL(sqlDelete);
        adapter.clear();
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    //날짜 기준으로 초기화 하기
/*   public static void delete1(SQLiteDatabase database){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);//하루 전 날짜
        SimpleFormatter sdf = new SimpleFormatter("yyyy-mm-dd");
        String pDate = sdf.format(cal.getTime());
        database.execSQL("DELETE FROM TB_CHARGE_HISTORYWHERE_start_data <"+""+pDate + "00:00:00");
    }*/

}
