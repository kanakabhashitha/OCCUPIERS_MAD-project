package com.example.panthiya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelperTRBnSRB extends SQLiteOpenHelper {
    public DatabaseHelperTRBnSRB(@Nullable Context context) {
        super(context, ConstantsTRBnSRB.DB_NAME, null, ConstantsTRBnSRB.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantsTRBnSRB.CREATE_TRB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsTRBnSRB.TRB_TABLE);
        onCreate(db);
    }

    //insert information

    public long insertInfo_TRB( String tr_date, String tr_subject, String tr_titel, String tr_outcomes, String tr_donePoints, String tr_exceptionPoints, String tr_addTimes, String tr_updateTimes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsTRBnSRB.T_DATE, tr_date);
        values.put(ConstantsTRBnSRB.T_SUBJECT, tr_subject);
        values.put(ConstantsTRBnSRB.T_TITEL, tr_titel);
        values.put(ConstantsTRBnSRB.T_LERNING_OUTCOMES, tr_outcomes);
        values.put(ConstantsTRBnSRB.T_DONE_PONTS, tr_donePoints);
        values.put(ConstantsTRBnSRB.T_EXCEPTED_PONTS, tr_exceptionPoints);
        values.put(ConstantsTRBnSRB.T_ADD_TIMESTAMP, tr_addTimes);
        values.put(ConstantsTRBnSRB.T_UPDATE_TIMESTAMP, tr_updateTimes);

        long id =  db.insert(ConstantsTRBnSRB.TRB_TABLE, null, values);
        db.close();
        return id;

    }

    //arryList table 1
    public ArrayList<Model_trb> getAllData(String orderBy){

        ArrayList<Model_trb> arrayList = new ArrayList<>();

        //query for select all info in databse
        String selectQuery = "SELECT * FROM " + ConstantsTRBnSRB.TRB_TABLE + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //select all info from databes new gte the data from column
        if(cursor.moveToNext()){

            do{

                Model_trb model = new Model_trb(

                        ""+cursor.getInt(cursor.getColumnIndex(ConstantsTRBnSRB.T_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsTRBnSRB.T_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsTRBnSRB.T_SUBJECT)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsTRBnSRB.T_TITEL)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsTRBnSRB.T_LERNING_OUTCOMES)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsTRBnSRB.T_DONE_PONTS)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsTRBnSRB.T_EXCEPTED_PONTS)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsTRBnSRB.T_ADD_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsTRBnSRB.T_UPDATE_TIMESTAMP))
                );

                arrayList.add(model);
            } while(cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }


    //update information trb table

    public void updateInfo(String id, String date, String subject, String titel, String outComes, String donePoints, String exceptionPoints, String addTimes, String updateTimes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsTRBnSRB.T_DATE, date);
        values.put(ConstantsTRBnSRB.T_SUBJECT, subject);
        values.put(ConstantsTRBnSRB.T_TITEL, titel);
        values.put(ConstantsTRBnSRB.T_LERNING_OUTCOMES, outComes);
        values.put(ConstantsTRBnSRB.T_DONE_PONTS, donePoints);
        values.put(ConstantsTRBnSRB.T_EXCEPTED_PONTS, exceptionPoints);
        values.put(ConstantsTRBnSRB.T_ADD_TIMESTAMP, addTimes);
        values.put(ConstantsTRBnSRB.T_UPDATE_TIMESTAMP, updateTimes);

        db.update(ConstantsTRBnSRB.TRB_TABLE, values, ConstantsTRBnSRB.T_ID + " = ?", new String[]{id});
        db.close();

    }


    //delete
    public void deleteInfo(String id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantsTRBnSRB.TRB_TABLE, ConstantsTRBnSRB.T_ID + " = ? ", new String[]{id});
        db.close();

    }
}
