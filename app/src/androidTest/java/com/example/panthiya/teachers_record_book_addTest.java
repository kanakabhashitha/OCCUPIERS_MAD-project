package com.example.panthiya;

import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class teachers_record_book_addTest {

    @Rule
    //enable launching the activity
    public ActivityTestRule<teachers_record_book_add> mActivityTest =  new ActivityTestRule<teachers_record_book_add>(teachers_record_book_add.class);

    //create a reference to activity
    private teachers_record_book_add addRec = null;

    @Before
    public void setUp() throws Exception {
        //set activity to get activity
        addRec = mActivityTest.getActivity();
    }
    @Test
    public void testLaunch(){

        //if the edit field is not null, test is success
        EditText date = addRec.findViewById(R.id.tr_date);
        assertNotNull(date);
        EditText title = addRec.findViewById(R.id.tr_titel);
        assertNotNull(title);
        EditText subject = addRec.findViewById(R.id.tr_subject);
        assertNotNull(subject);
        EditText outcome = addRec.findViewById(R.id.tr_outcome);
        assertNotNull(outcome);
        EditText donePoint = addRec.findViewById(R.id.tr_done_points);
        assertNotNull(donePoint);
        EditText expectedPoint = addRec.findViewById(R.id.tr_expected_pont);
        assertNotNull(expectedPoint);
    }
    @After
    public void tearDown() throws Exception {

        addRec = null;
    }
}