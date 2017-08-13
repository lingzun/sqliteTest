package my.com.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by wangjf on 2017/7/28.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //    public static final String CREATE_BOOK = "create table Book ("
//      + "id integer primary key autoincrement, "
//      + "author text, "
//      + "price real, "
//      + "pages integer, "
//      + "name text)";
    private static MyDatabaseHelper helper;
    private static SQLiteDatabase db;
    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";
    public static final String CREATE_CATEGORY = "create table Category(" + "id integer primary key autoincrement," + "category_name text," + "category_code integer)";
    private Context mContext;

    private MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "Create databases succeeded", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);

    }

    public static SQLiteDatabase getInstance(Context context) {
        if (helper == null) {
            helper = new MyDatabaseHelper(context, "BookStore.db", null, 1);
        }
        if(db==null){
            db=helper.getWritableDatabase();
        }
        return db;
    }
}
