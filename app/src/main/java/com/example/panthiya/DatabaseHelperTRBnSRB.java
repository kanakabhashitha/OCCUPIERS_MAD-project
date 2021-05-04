package com.example.panthiya;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperTRBnSRB extends SQLiteOpenHelper {
    public DatabaseHelperTRBnSRB(@Nullable Context context) {
        super(context, ConstantsTRBnSRB.TSRB_DB, null, ConstantsTRBnSRB.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantsTRBnSRB.CREATE_TSRB_TABLE);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsTRBnSRB.TRB_TABLE);
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
}
