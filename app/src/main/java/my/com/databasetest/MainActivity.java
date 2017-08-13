package my.com.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private MyDatabaseHelper helper;
    Button creatDb, updateData, deleteData, queryData, addData;
    TextView queryView;
    StringBuilder result;
    private static final String TAG = "MainActivity.this";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        helper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        creatDb = (Button) findViewById(R.id.creat_db);
        creatDb.setOnClickListener(this);
        addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(this);
        updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(this);
        deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(this);
        queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(this);
        queryView = (TextView) findViewById(R.id.query_view);
//      
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.creat_db:
                MyDatabaseHelper.getInstance(MainActivity.this);
                break;
            case R.id.add_data:
//                helper.close();
                SQLiteDatabase db = MyDatabaseHelper.getInstance(MainActivity.this);
                db.beginTransaction();
                try {
                    ContentValues values = new ContentValues();
                    values.put("name", "The Da Vinci Code");
                    values.put("author", "Dan Brown");
                    values.put("pages", 454);
                    values.put("price", 16.96);
                    db.insert("Book", null, values);
                    values.clear();
                    Log.d(TAG, "onClick: insert 1 successful!");
                    values.put("name", "The Lost Symbol");
                    values.put("author", "Dan Brownrrdt");
                    values.put("pages", 510);
                    values.put("price", 19.95);
                    db.insert("Book", null, values);
                    values.clear();
                    Log.d(TAG, "onClick: insert 2 successful!");
                    values.put("name", "Who am i");
                    values.put("author", "Mrs wang");
                    values.put("pages", 110);
                    values.put("price", 11.2);
                    db.insert("Book", null, values);
                    Log.d(TAG, "onClick: insert 3 successful!");
                   /* String sql1 = "insert into Book(name,author,pages,price)values('The Da Vinci Code','Dan Brown','454','16.96')";
                    String sql2 = "insert into Book(name,author,pages,price)values('The Lost Symbol','Dan Brownrrdt','510','19.95')";
                    String sql3 = "insert into Book(name,author,pages,price)values('Who am i','Mrs wang','110','11.2')";
                    db.execSQL(sql1);
                    db.execSQL(sql2);
                    db.execSQL(sql3);*/
                    db.setTransactionSuccessful();
                } catch (SQLiteException e) {
                    Log.d(TAG, "SQLiteException: " + e.toString());

                }finally {
                    db.endTransaction();
                }

                break;
            case R.id.update_data:
                break;
            case R.id.query_data:
                SQLiteDatabase db1 = MyDatabaseHelper.getInstance(MainActivity.this);
                Cursor cursor1 = db1.query("Book", null, null, null, null, null, null);
                if (cursor1.moveToFirst()) {
                    do {
                        String name = cursor1.getString(cursor1.getColumnIndex("name"));
                        String author = cursor1.getString(cursor1.getColumnIndex("author"));
                        double price = cursor1.getDouble(cursor1.getColumnIndex("price"));
                        int pages = cursor1.getInt(cursor1.getColumnIndex("pages"));

                        Log.d(TAG, "Book name is: " + name);
                        Log.d(TAG, "Book author is: " + author);
                        Log.d(TAG, "Book price is: " + price);
                        Log.d(TAG, "Book pages is: " + pages);
                    } while (cursor1.moveToNext());
                }
                cursor1.close();
                break;
            case R.id.delete_data:
                break;

        }
    }
}
