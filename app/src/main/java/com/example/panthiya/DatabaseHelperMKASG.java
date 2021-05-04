package com.example.panthiya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelperMKASG extends SQLiteOpenHelper {


    public DatabaseHelperMKASG(@Nullable Context context) {
        super(context, ConstantsMKASG.DB_NAME,null, ConstantsMKASG.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ConstantsMKASG.CREATE_TABLE);
        db.execSQL(ConstantsMKASG.CREATE_TABLE_2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsMKASG.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsMKASG.TABLE_NAME_2);

        onCreate(db);
    }

    //insert information

    public long insertInfo(String number, String subject, String deadLine, String description, String image, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.A_NUMBER, number);
        values.put(ConstantsMKASG.A_SUBJECT, subject);
        values.put(ConstantsMKASG.A_DEADLINE, deadLine);
        values.put(ConstantsMKASG.A_DESCRIPTION, description);
        values.put(ConstantsMKASG.A_IMAGE, image);
        values.put(ConstantsMKASG.A_ADD_TIMESTAMP, addTimeStamp);
        values.put(ConstantsMKASG.A_UPDATE_TIMESTAMP, updateTimeStamp);

        long id =  db.insert(ConstantsMKASG.TABLE_NAME, null, values);
        db.close();
        return id;

    }


    //update information techers table

    public void updateInfo(String id, String number, String subject, String deadLine, String description, String image, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.A_NUMBER, number);
        values.put(ConstantsMKASG.A_SUBJECT, subject);
        values.put(ConstantsMKASG.A_DEADLINE, deadLine);
        values.put(ConstantsMKASG.A_DESCRIPTION, description);
        values.put(ConstantsMKASG.A_IMAGE, image);
        values.put(ConstantsMKASG.A_ADD_TIMESTAMP, addTimeStamp);
        values.put(ConstantsMKASG.A_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(ConstantsMKASG.TABLE_NAME, values, ConstantsMKASG.A_ID + " = ?", new String[]{id});
        db.close();

    }


    //delete informatin techers table

    public void deleteInfo(String id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantsMKASG.TABLE_NAME, ConstantsMKASG.A_ID + " = ? ", new String[]{id});
        db.close();

    }

    //preview information techers table
    public void perviewInfo(String id, String number, String subject, String deadLine, String description, String image, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.A_NUMBER, number);
        values.put(ConstantsMKASG.A_SUBJECT, subject);
        values.put(ConstantsMKASG.A_DEADLINE, deadLine);
        values.put(ConstantsMKASG.A_DESCRIPTION, description);
        values.put(ConstantsMKASG.A_IMAGE, image);
        values.put(ConstantsMKASG.A_ADD_TIMESTAMP, addTimeStamp);
        values.put(ConstantsMKASG.A_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(ConstantsMKASG.TABLE_NAME, values, ConstantsMKASG.A_ID + " = ?", new String[]{id});
        db.close();

    }



    //arryList table 1
    public ArrayList<Model_mkasg> getAllData(String orderBy){

        ArrayList<Model_mkasg> arrayList = new ArrayList<>();

        //query for select all info in databse
        String selectQuery = "SELECT * FROM " + ConstantsMKASG.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //select all info from databes new gte the data from column
        if(cursor.moveToNext()){

            do{

                Model_mkasg model = new Model_mkasg(

                        ""+cursor.getInt(cursor.getColumnIndex(ConstantsMKASG.A_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.A_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.A_NUMBER)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.A_SUBJECT)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.A_DEADLINE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.A_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.A_ADD_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.A_UPDATE_TIMESTAMP))
                );

                arrayList.add(model);
            } while(cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }






    //table2 arryList

    public ArrayList<Model_mkasg_s2> getAllDataTable2(String orderBy){

        ArrayList<Model_mkasg_s2> arrayList = new ArrayList<>();

        //query for select all info in databse
        String selectQuery = "SELECT * FROM " + ConstantsMKASG.TABLE_NAME_2 + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //select all info from databes new gte the data from column
        if(cursor.moveToNext()){

            do{

                Model_mkasg_s2 model = new Model_mkasg_s2(

                        ""+cursor.getInt(cursor.getColumnIndex(ConstantsMKASG.S_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.S_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.S_NUMBER)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.S_SUBJECT)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.S_DEADLINE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.S_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.S_ADD_DATEnTIME)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.S_UPDATE_DATEnTIME))
                );

                arrayList.add(model);
            } while(cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }


    //insert information table_2

    public long insertInfo_2(String s_number, String s_subject, String s_deadLine, String s_description, String s_image, String s_addTimeStamp, String s_updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.S_NUMBER, s_number);
        values.put(ConstantsMKASG.S_SUBJECT, s_subject);
        values.put(ConstantsMKASG.S_DEADLINE, s_deadLine);
        values.put(ConstantsMKASG.S_DESCRIPTION, s_description);
        values.put(ConstantsMKASG.S_IMAGE, s_image);
        values.put(ConstantsMKASG.S_ADD_DATEnTIME, s_addTimeStamp);
        values.put(ConstantsMKASG.S_UPDATE_DATEnTIME, s_updateTimeStamp);

        long id = db.insert(ConstantsMKASG.TABLE_NAME_2, null, values);
        db.close();
        return id;

    }



    //update information student table

    public void updateInfo_table2(String s_id, String s_number, String s_subject, String s_deadLine, String s_description, String s_image, String s_addTimeStamp, String s_updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.S_NUMBER, s_number);
        values.put(ConstantsMKASG.S_SUBJECT, s_subject);
        values.put(ConstantsMKASG.S_DEADLINE, s_deadLine);
        values.put(ConstantsMKASG.S_DESCRIPTION, s_description);
        values.put(ConstantsMKASG.S_IMAGE, s_image);
        values.put(ConstantsMKASG.S_ADD_DATEnTIME, s_addTimeStamp);
        values.put(ConstantsMKASG.S_UPDATE_DATEnTIME, s_updateTimeStamp);

        db.update(ConstantsMKASG.TABLE_NAME_2, values, ConstantsMKASG.S_ID + " = ?", new String[]{s_id});
        db.close();

    }


    //delete informatin student table

    public void deleteInfo_table_2(String s_id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantsMKASG.TABLE_NAME_2, ConstantsMKASG.S_ID + " = ? ", new String[]{s_id});
        db.close();

    }



}
