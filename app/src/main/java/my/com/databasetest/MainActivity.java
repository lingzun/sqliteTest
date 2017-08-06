package my.com.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper helper;
    Button creatDb, updateDb, deleteDb, queryDb, addData;
    private static final String TAG = "MainActivity.this";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        creatDb = (Button) findViewById(R.id.creat_db);
        creatDb.setOnClickListener(this);
        addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.creat_db:
                helper.getWritableDatabase();
                break;
            case R.id.add_data:
                SQLiteDatabase db = helper.getWritableDatabase();
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
                    values.put("prices", 19.95);
                    db.insert("Book", null, values);
                    values.clear();
                    Log.d(TAG, "onClick: insert 2 successful!");
                    values.put("name", "Who am i");
                    values.put("author", "Mrs wang");
                    values.put("pages", 110);
                    values.put("prices", 11.2);
                    db.insert("Book", null, values);
                    Log.d(TAG, "onClick: insert 3 successful!");
                    Cursor cursor = db.query("Book", null, null, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        do {
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            String author = cursor.getString(cursor.getColumnIndex("author"));
                            double price = cursor.getDouble(cursor.getColumnIndex("price"));
                            int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                            Log.d(TAG, "Book name is: " + name);
                            Log.d(TAG, "Book author is: " + author);
                            Log.d(TAG, "Book price is: " + price);
                            Log.d(TAG, "Book pages is: " + pages);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    db.setTransactionSuccessful();
                }

                break;
            default:
                break;
        }
    }
}
