package com.example.anew.sqldbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    EditText et1,et2,et3,et4;
    Button b1;
    Button b2; Button b3;
    Button b4;
    SQLiteDatabase sqlb;
    ContentValues cv;
    ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1=findViewById(R.id.lv1);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        sqlb =openOrCreateDatabase("satya", Context.MODE_PRIVATE,null);
        cv=new ContentValues();
        sqlb.execSQL("create table if not exists employee(id number(2),name varchar(20),salary number(6),company varchar(20))");

    }
    public void insert(View v)
    {
        cv.put("id",Integer.parseInt(et1.getText().toString()));
        cv.put("name",et2.getText().toString());
        cv.put("salary",Integer.parseInt(et3.getText().toString()));
        cv.put("company",et4.getText().toString());
       long status=sqlb.insert("employee",null,cv);
       int status1=(int)(long)status;
       if(status1==-1)
        {
            Toast.makeText(this,"failed to insert ",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"inserted succesfully",Toast.LENGTH_LONG).show();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }

    }
    public void delete(View v)
    {
        String i=et1.getText().toString();
        int status=sqlb.delete("employee", "id="+i+"",null);
        if(status==0)
        {
            Toast.makeText(this,"failed to delete",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"deleted"+i+"succesfully",Toast.LENGTH_LONG).show();
        }

    }
    public void update(View v)
    {
        String i=et1.getText().toString();
        cv.put("name",et2.getText().toString());
        cv.put("company",et4.getText().toString());
        int status=sqlb.update("employee",cv,"id="+i+"",null);
        if(status==0)
        {
            Toast.makeText(this,"failed to update",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"updated"+i+"succesfully",Toast.LENGTH_LONG).show();
        }

    }
    public void display(View v)
    {
        Cursor c=sqlb.query("employee",null,null,null,null,null,null);
        ArrayList<String> str=new ArrayList<String>();
        while (c.moveToNext())
        {
            String s = c.getString(0);
            String s1=c.getString(1);
            String s2=c.getString(2);
            String s3=c.getString(3);
            str.add(s+"/"+s1+"/"+s2+"/"+s3);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,str);
        lv1.setAdapter(adapter);
    }

}
