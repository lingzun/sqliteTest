package my.com.databasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private MyDatabaseHelper helper;
    Button creatDb,updateDb,deleteDb,queryDb,addData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new MyDatabaseHelper(this,"BookStore.db",null,3);
        creatDb= (Button) findViewById(R.id.creat_db);
        creatDb.setOnClickListener(this);
        addData= (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.creat_db:
                helper.getReadableDatabase();
                break;
            default:
                break;
        }
    }
}
