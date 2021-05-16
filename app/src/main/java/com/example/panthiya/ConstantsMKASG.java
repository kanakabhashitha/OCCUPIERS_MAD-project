package com.example.panthiya;

public class ConstantsMKASG {
    //db name
    public static final String DB_NAME = "PANTHIYA_INFO_DB";
    //db version
    public static final int DB_VERSION = 5;
    //db table
    public static final String TABLE_NAME = "TEACHERS_ASSIGNMENT_INFO_TABLE";
    public static final String TABLE_NAME_2= "STUDENT_ASSIGNMENT_INFO_TABLE";
    public static final String TABLE_NAME_3= "TEACHER_REGISTER_INFO_TABLE";
    public static final String TABLE_NAME_4= "STUDENT_REGISTER_INFO_TABLE";

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

    //TABLE3 COLUMN
    public static final String T_ID = "ID";
    public static final String T_IMAGE = "IMAGE";
    public static final String T_F_NAME = "FIRST_NAME";
    public static final String T_L_NAME = "LAST_NAME";
    public static final String T_EMAIL = "EMAIL";
    public static final String T_PASSWORD = "PASSWORD";
    public static final String T_ADD_TIMESTAMP = "ADD_TIMESTAPM";
    public static final String T_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP";

    //TABLE4 COLUMN
    public static final String SR_ID = "ID";
    public static final String TFK_ID = "TFK_ID";
    public static final String SR_IMAGE = "IMAGE";
    public static final String SR_F_NAME = "FIRST_NAME";
    public static final String SR_L_NAME = "LAST_NAME";
    public static final String SR_GRADE = "GRADE";
    public static final String SR_AGE = "AGE";
    public static final String SR_GENDER = "GENDER";
    public static final String SR_PONE = "PHONE";
    public static final String SR_EMAIL = "EMAIL";
    public static final String SR_PASSWORD = "PASSWORD";
    public static final String SR_ADD_TIMESTAMP = "ADD_TIMESTAPM";
    public static final String SR_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP";


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

    //CREATE QUERY for table3
    public static final String CREATE_TABLE_3 = "CREATE TABLE " + TABLE_NAME_3 + " ("
            + T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + T_IMAGE + " TEXT,"
            + T_F_NAME + " TEXT,"
            + T_L_NAME + " TEXT,"
            + T_EMAIL + " TEXT,"
            + T_PASSWORD + " TEXT,"
            + T_ADD_TIMESTAMP + " TEXT,"
            + T_UPDATE_TIMESTAMP + " TEXT"
            + ");";

    //CREATE QUERY for table 4
    public static final String CREATE_TABLE_4 = "CREATE TABLE " + TABLE_NAME_4 + " ("
            + SR_ID + " STRING PRIMARY KEY,"
            + TFK_ID + " STRING REFERENCES " + T_ID + ","
            + SR_IMAGE + " TEXT,"
            + SR_F_NAME + " TEXT,"
            + SR_L_NAME + " TEXT,"
            + SR_GRADE + " TEXT,"
            + SR_AGE + " TEXT,"
            + SR_GENDER + " TEXT,"
            + SR_PONE + " TEXT,"
            + SR_EMAIL + " TEXT,"
            + SR_PASSWORD + " TEXT,"
            + SR_ADD_TIMESTAMP + " TEXT,"
            + SR_UPDATE_TIMESTAMP + " TEXT"
            + ");";

    //maduka workspace/////////////////////////////////////////////////////////////////////////////////////////////////////////

    //db table 5
    public static final String TABLE_NAME_5 = "Marks_Details";
    //table column
    public static final String ID = "ID";
    public static final String ASSIGNMENT = "assignment";
    public static final String STUDENTID = "studentID";
    public static final String NAME = "name";
    public static final String SUBJECT = "subject";
    public static final String MARKS = "mark";
    public static final String COMMENT = "comment";

    //CREATE QUERY for table
    public static final String CREATE_TABLE_5 = "CREATE TABLE " + TABLE_NAME_5 + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ASSIGNMENT + " TEXT,"
            + STUDENTID + " TEXT,"
            + NAME + " TEXT,"
            + SUBJECT + " TEXT,"
            + MARKS + " TEXT,"
            + COMMENT + " TEXT"
            + ");";
}
