package com.example.panthiya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperMKASG extends SQLiteOpenHelper {


    public DatabaseHelperMKASG(@Nullable Context context) {
        super(context, ConstantsMKASG.DB_NAME,null, ConstantsMKASG.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ConstantsMKASG.CREATE_TABLE_3);
        db.execSQL(ConstantsMKASG.CREATE_TABLE_4);
        db.execSQL(ConstantsMKASG.CREATE_TABLE);
        db.execSQL(ConstantsMKASG.CREATE_TABLE_2);
        db.execSQL(ConstantsMKASG.CREATE_TABLE_5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsMKASG.TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsMKASG.TABLE_NAME_4);
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsMKASG.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsMKASG.TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsMKASG.TABLE_NAME_5);

        onCreate(db);
    }

    //insert information

    public long insertInfo(String number, String atfk, String subject, String deadLine, String description, String image, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.ATFK_ID, atfk);
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

    public void updateInfo(String id, String atfk, String number, String subject, String deadLine, String description, String image, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.ATFK_ID, atfk);
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
                        ""+cursor.getInt(cursor.getColumnIndex(ConstantsMKASG.ATFK_ID)),
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



    //table 3 work area

    //table3 arryList

   //insert information table 3

    public long insertInfoTable_3(String fName, String lName, String email, String password, String image, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.T_F_NAME, fName);
        values.put(ConstantsMKASG.T_L_NAME, lName);
        values.put(ConstantsMKASG.T_EMAIL, email);
        values.put(ConstantsMKASG.T_PASSWORD, password);
        values.put(ConstantsMKASG.T_IMAGE, image);
        values.put(ConstantsMKASG.T_ADD_TIMESTAMP, addTimeStamp);
        values.put(ConstantsMKASG.T_UPDATE_TIMESTAMP, updateTimeStamp);

        long id =  db.insert(ConstantsMKASG.TABLE_NAME_3, null, values);
        db.close();
        return id;

    }



    public List<Model_TR> getAlldata() {
        // array of columns to fetch
        String[] columns = {
                ConstantsMKASG.T_ID,
                ConstantsMKASG.T_F_NAME,
                ConstantsMKASG.T_L_NAME,
                ConstantsMKASG.T_EMAIL,
                ConstantsMKASG.T_PASSWORD,
                ConstantsMKASG.T_ADD_TIMESTAMP,
                ConstantsMKASG.T_UPDATE_TIMESTAMP

        };
        // sorting orders
        String sortOrder =
                ConstantsMKASG.T_EMAIL + " ASC";
        List<Model_TR> userList = new ArrayList<Model_TR>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(ConstantsMKASG.TABLE_NAME_3, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
               Model_TR model = new Model_TR(

                        ""+cursor.getInt(cursor.getColumnIndex(ConstantsMKASG.T_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_F_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_L_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_EMAIL)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_PASSWORD)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_ADD_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_UPDATE_TIMESTAMP))
               );
                // Adding user record to list
                userList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }


    public Boolean getPassword(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        Cursor cursor = db.rawQuery("Select * from TABLE_NAME_3 where EMAIL = ?",new String[] {email});
        db.close();

        if (cursor.getCount()> 0){
            return false;
        }else {
            return true;
        }
    }


    //get emailpassword
    public boolean checkemailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsMKASG.TABLE_NAME_3 + " WHERE EMAIL= ? AND PASSWORD= ?",
                new String[]{email,password});

      /*  Cursor cursor = db.rawQuery("select * from TABLE_NAME_3 where EMAIL= ? and PASSWORD= ?",new String[]{email,password});*/

        if (cursor.getCount()> 0){
            return true;
        }else {
            return false;
        }
    }


    //getusername
    public boolean checkUserName(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsMKASG.TABLE_NAME_3 + " WHERE EMAIL = ?",
                new String[]{email});

        if (cursor.getCount()> 0){
            return true;
        }else {
            return false;
        }
    }

    public Cursor getUserData(String email){
        System.out.println("tE__"+email);
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + ConstantsMKASG.TABLE_NAME_3 + " WHERE EMAIL = ?",
                new String[]{email});

    }


    //update information TABLE3

    public void updateInfoTable_3(String fName, String lName, String email, String password, String image, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.T_F_NAME, fName);
        values.put(ConstantsMKASG.T_L_NAME, lName);
        values.put(ConstantsMKASG.T_EMAIL, email);
        values.put(ConstantsMKASG.T_PASSWORD, password);
        values.put(ConstantsMKASG.T_IMAGE, image);
        values.put(ConstantsMKASG.T_ADD_TIMESTAMP, addTimeStamp);
        values.put(ConstantsMKASG.T_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(ConstantsMKASG.TABLE_NAME_3, values, ConstantsMKASG.T_EMAIL + " = ?", new String[]{email});
        db.close();

    }

    //delete informatin  table3

    public void deleteInfo_table_3(String email){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantsMKASG.TABLE_NAME_3, ConstantsMKASG.T_EMAIL + " = ? ", new String[]{email});
        db.close();

    }


    //table4 work area

    //insert information table4

    public long insertInfoTable_4_from_teacher(String sid, String fkid, String image, String fname, String lname, String grade, String age, String gender, String phone, String email, String password, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.SR_ID, sid);
        values.put(ConstantsMKASG.TFK_ID, fkid);
        values.put(ConstantsMKASG.SR_IMAGE, image);
        values.put(ConstantsMKASG.SR_F_NAME, fname);
        values.put(ConstantsMKASG.SR_L_NAME, lname);
        values.put(ConstantsMKASG.SR_GRADE, grade);
        values.put(ConstantsMKASG.SR_AGE, age);
        values.put(ConstantsMKASG.SR_GENDER, gender);
        values.put(ConstantsMKASG.SR_PONE, phone);
        values.put(ConstantsMKASG.SR_EMAIL, email);
        values.put(ConstantsMKASG.SR_PASSWORD, password);
        values.put(ConstantsMKASG.SR_ADD_TIMESTAMP, addTimeStamp);
        values.put(ConstantsMKASG.SR_UPDATE_TIMESTAMP, updateTimeStamp);

        long id =  db.insert(ConstantsMKASG.TABLE_NAME_4, null, values);
        db.close();
        return id;

    }


    //table4 arryList

    public ArrayList<Model_SR> getAllDataTable4(String fkid){

        System.out.println("fk__"+fkid);
        ArrayList<Model_SR> arrayList = new ArrayList<>();

        //query for select all info in databse
       // String selectQuery = "SELECT * FROM " + ConstantsMKASG.TABLE_NAME_2 + " WHERE TFK_ID = ?",new String[] {fkid};

        SQLiteDatabase db = this.getReadableDatabase();
       // Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsMKASG.TABLE_NAME_4 + " WHERE TFK_ID = ? ",
                new String[]{fkid});

        //select all info from databes new gte the data from column
        if(cursor.moveToNext()){

            do{

                Model_SR model = new Model_SR(

                        ""+cursor.getInt(cursor.getColumnIndex(ConstantsMKASG.SR_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.TFK_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_F_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_L_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_GRADE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_AGE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_GENDER)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_PONE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_EMAIL)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_PASSWORD)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_ADD_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_UPDATE_TIMESTAMP))
                );

                arrayList.add(model);
            } while(cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }


    //update information TABLE4

    public void updateInfoTable_4_techer(String sid, String fkid, String image, String fname, String lname, String grade, String age, String gender, String phone, String email, String password, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.SR_ID, sid);
        values.put(ConstantsMKASG.TFK_ID, fkid);
        values.put(ConstantsMKASG.SR_IMAGE, image);
        values.put(ConstantsMKASG.SR_F_NAME, fname);
        values.put(ConstantsMKASG.SR_L_NAME, lname);
        values.put(ConstantsMKASG.SR_GRADE, grade);
        values.put(ConstantsMKASG.SR_AGE, age);
        values.put(ConstantsMKASG.SR_GENDER, gender);
        values.put(ConstantsMKASG.SR_PONE, phone);
        values.put(ConstantsMKASG.SR_EMAIL, email);
        values.put(ConstantsMKASG.SR_PASSWORD, password);
        values.put(ConstantsMKASG.SR_ADD_TIMESTAMP, addTimeStamp);
        values.put(ConstantsMKASG.SR_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(ConstantsMKASG.TABLE_NAME_4, values, ConstantsMKASG.SR_ID + " = ?", new String[]{sid});
        db.close();

    }

    //delete informatin  table4

    public void deleteInfo_table_4_teacher(String sid){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantsMKASG.TABLE_NAME_4, ConstantsMKASG.SR_ID + " = ? ", new String[]{sid});
        db.close();

    }

    //student side table4

    //get emailpassword
    public boolean checkSudentemailpassword(String ui, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsMKASG.TABLE_NAME_4 + " WHERE ID= ? AND PASSWORD= ?",
                new String[]{ui,password});

        /*  Cursor cursor = db.rawQuery("select * from TABLE_NAME_3 where EMAIL= ? and PASSWORD= ?",new String[]{email,password});*/

        if (cursor.getCount()> 0){
            return true;
        }else {
            return false;
        }
    }

    //student data
    public Cursor getStudentData(String sid){
        System.out.println("sE__"+sid);
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + ConstantsMKASG.TABLE_NAME_4 + " WHERE ID = ?",
                new String[]{sid});

    }


    //update information TABLE4 student

    public void updateInfoTable_4_student(String sid, String fkid, String image, String fname, String lname, String grade, String age, String gender, String phone, String email, String password, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.SR_ID, sid);
        //values.put(ConstantsMKASG.TFK_ID, fkid);
        values.put(ConstantsMKASG.SR_IMAGE, image);
        values.put(ConstantsMKASG.SR_F_NAME, fname);
        values.put(ConstantsMKASG.SR_L_NAME, lname);
        values.put(ConstantsMKASG.SR_GRADE, grade);
        values.put(ConstantsMKASG.SR_AGE, age);
        values.put(ConstantsMKASG.SR_GENDER, gender);
        values.put(ConstantsMKASG.SR_PONE, phone);
        values.put(ConstantsMKASG.SR_EMAIL, email);
        values.put(ConstantsMKASG.SR_PASSWORD, password);
        values.put(ConstantsMKASG.SR_ADD_TIMESTAMP, addTimeStamp);
        values.put(ConstantsMKASG.SR_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(ConstantsMKASG.TABLE_NAME_4, values, ConstantsMKASG.SR_ID + " = ?", new String[]{sid});
        db.close();

    }


    public void deleteInfo_table_4(String sid){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantsMKASG.TABLE_NAME_4, ConstantsMKASG.SR_ID + " = ? ", new String[]{sid});
        db.close();

    }


    //maduka workspace
    //table name5

    //insert infomation table 5

    public long insertInfo_tabel_5(String assignment, String studentid, String name, String subject, String marks, String comment) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.ASSIGNMENT, assignment);
        values.put(ConstantsMKASG.STUDENTID, studentid);
        values.put(ConstantsMKASG.NAME, name);
        values.put(ConstantsMKASG.SUBJECT, subject);
        values.put(ConstantsMKASG.MARKS, marks);
        values.put(ConstantsMKASG.COMMENT, comment);

        long id =  db.insert(ConstantsMKASG.TABLE_NAME_5, null, values);
        db.close();
        return id;

    }


    //get all data table 5

    public ArrayList<Model_TM> getAllData_table_5(String orderBy){

        ArrayList<Model_TM> arrayList = new ArrayList<>();

        //query for select all info in databse
        String selectQuery = "SELECT * FROM " + ConstantsMKASG.TABLE_NAME_5 + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //select all info from databes new gte the data from column
        if(cursor.moveToNext()){

            do{

                Model_TM model = new Model_TM(

                        ""+cursor.getInt(cursor.getColumnIndex(ConstantsMKASG.ID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.ASSIGNMENT)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.STUDENTID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SUBJECT)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.MARKS)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.COMMENT))
                );

                arrayList.add(model);
            } while(cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }


    //update infomation table5

    public void updateInfo_Table_5(String id, String assignment, String studentid, String name, String subject, String marks, String comment) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ConstantsMKASG.ASSIGNMENT, assignment);
        values.put(ConstantsMKASG.STUDENTID, studentid);
        values.put(ConstantsMKASG.NAME, name);
        values.put(ConstantsMKASG.SUBJECT, subject);
        values.put(ConstantsMKASG.MARKS, marks);
        values.put(ConstantsMKASG.COMMENT, comment);

        db.update(ConstantsMKASG.TABLE_NAME_5, values, ConstantsMKASG.ID + " = ?", new String[]{id});
        db.close();

    }

    //table delete_5
    public void deleteInfo_table_5(String id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantsMKASG.TABLE_NAME_5, ConstantsMKASG.ID + " = ? ", new String[]{id});
        db.close();

    }


    public Cursor countTotalMarks() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + ConstantsMKASG.MARKS + ") as Total FROM " + ConstantsMKASG.TABLE_NAME_5, null);

        if (cursor.moveToFirst()) {

            int total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            System.out.println("Sssss"+total);
        }
        return cursor;
    }

    public Cursor countTotalMarksAVG() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT AVG(" + ConstantsMKASG.MARKS + ") as Total FROM " + ConstantsMKASG.TABLE_NAME_5, null);

        if (cursor.moveToFirst()) {

            int avg = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            System.out.println("AAAA"+avg);
        }
        return cursor;
    }



}
