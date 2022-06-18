package com.example.deutschea1alla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {

    DbLocation locationDB;
    DbFinishLesson finishDB;
    Db1000Adapter helper1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        locationDB = new DbLocation(this);
        finishDB = new DbFinishLesson(this);
        helper1000 = new Db1000Adapter(this);
    }

    public void showAllData(View view) {
        ArrayList twoDimArray = helper1000.getDataArray();

            Object begermod = twoDimArray.get(0);
        //}
        //String data = helper.getData();
        Message.message(this,begermod.toString());
    }

    public void showAllDataLesson(View view) {
        String[][] twoDimArray = helper1000.getDataArrayLesson("1");
        for (int i = 0; i < twoDimArray[0].length; i++) {
            Message.message(this,twoDimArray[0][i]);
        }
    }

    public void deleteData(View view) {
        helper1000.emptyTable("location");
        helper1000.emptyTable("finish");
        helper1000.emptyTable("words1000");
        Message.message(this,"delete table words1000");
    }

    public void createTable(View view) {
        locationDB.createTable();
        finishDB.createTable();
        helper1000.createTable();
        Message.message(this,"create table words1000");
    }

    public void addData(View view) {
        locationDB.addFirstLocation();
        finishDB.addFirstData("allCurses");
        finishDB.ubdateFinishLesson("allCurses",1,1);
        finishDB.addFirstData("words1000");
        helper1000.addData();
        Message.message(this,"add Data words1000 finish");
    }

    public void showSounds(View view) {
        Intent intentRew = new Intent(this, SoundActivity.class);
        //intentRew.putExtra("cursInfo",pageNumber);
        //intentRew.putExtra("cursTitle",lessonIntent[1]);
        startActivity(intentRew);
    }
}