package com.example.panthiya;

public class ConstantsMKASG {
    //db name
    public static final String DB_NAME = "PANTHIYA_INFO_DB";
    //db version
    public static final int DB_VERSION = 5;
    //db table
    public static final String TABLE_NAME = "TEACHERS_ASSIGNMENT_INFO_TABLE";
    public static final String TABLE_NAME_2= "STUDENT_ASSIGNMENT_INFO_TABLE";


    //table column
    public static final String A_ID = "ID";
    public static final String A_NUMBER = "NUMBER";
    public static final String A_SUBJECT = "SUBJECT";
    public static final String A_DEADLINE = "DEADLINE";
    public static final String A_DESCRIPTION = "DESCRIPTION";
    public static final String A_IMAGE = "IMAGE";
    public static final String A_ADD_TIMESTAMP = "ADD_TIMESTAPM";
    public static final String A_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP";

    //table2 column
    public static final String S_ID = "ID";
    public static final String S_NUMBER = "NUMBER";
    public static final String S_SUBJECT = "SUBJECT";
    public static final String S_DEADLINE = "DEADLINE";
    public static final String S_DESCRIPTION = "DESCRIPTION";
    public static final String S_IMAGE = "IMAGE";
    public static final String S_ADD_DATEnTIME= "ADD_DATE_AND_TIME";
    public static final String S_UPDATE_DATEnTIME = "UPDATE_DATE_AND_TIME";


    //CREATE QUERY for table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + A_NUMBER + " TEXT,"
            + A_SUBJECT + " TEXT,"
            + A_DEADLINE + " TEXT,"
            + A_DESCRIPTION + " TEXT,"
            + A_IMAGE + " TEXT,"
            + A_ADD_TIMESTAMP + " TEXT,"
            + A_UPDATE_TIMESTAMP + " TEXT"
            + ");";

    //CREATE QUERY for table2
    public static final String CREATE_TABLE_2 = "CREATE TABLE " + TABLE_NAME_2 + " ("
            + S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + S_NUMBER + " TEXT,"
            + S_SUBJECT + " TEXT,"
            + S_DEADLINE + " TEXT,"
            + S_DESCRIPTION + " TEXT,"
            + S_IMAGE + " TEXT,"
            + S_ADD_DATEnTIME + " TEXT,"
            + S_UPDATE_DATEnTIME + " TEXT"
            + ");";
}
