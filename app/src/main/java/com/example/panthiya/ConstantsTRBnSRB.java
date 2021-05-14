package com.example.panthiya;

public class ConstantsTRBnSRB {

    //db name
    public static final String DB_NAME = "PANTHIYA_TECHERS_STUDENTS_RECORBOOKINFO_DB";
    //db version
    public static final int DB_VERSION = 2;
    //db table
    public static final String TRB_TABLE = "TEACHERS_RECORD_BOOK_INFO_TABLE";


    //table column
    public static final String T_ID = "ID";
    public static final String T_DATE = "DATE";
    public static final String T_SUBJECT = "SUBJECT";
    public static final String T_TITEL = "TITEL";
    public static final String T_LERNING_OUTCOMES = "OUTCOMES";
    public static final String T_DONE_PONTS = "DONEPOINTS";
    public static final String T_EXCEPTED_PONTS = "EXCEPTEDPOINTS";
    public static final String T_ADD_TIMESTAMP = "ADD_TIMESTAPM";
    public static final String T_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP";


    //CREATE QUERY for table
    public static final String CREATE_TRB_TABLE = "CREATE TABLE " + TRB_TABLE + " ("
            + T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + T_DATE + " TEXT,"
            + T_SUBJECT + " TEXT,"
            + T_TITEL + " TEXT,"
            + T_LERNING_OUTCOMES + " TEXT,"
            + T_DONE_PONTS + " TEXT,"
            + T_EXCEPTED_PONTS + " TEXT,"
            + T_ADD_TIMESTAMP + " TEXT,"
            + T_UPDATE_TIMESTAMP + " TEXT"
            + ");";

}
