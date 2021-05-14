package com.example.panthiya;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.text.ParseException;

import android.content.Context;

import java.io.FileOutputStream;


import static org.junit.Assert.*;


public class ExampleUnitTest {
    private Add_assignment_form_teacher add_assignment_form_teacher;

 @Before
    public void setUp(){
        add_assignment_form_teacher = new Add_assignment_form_teacher();;
    }

    @Test
    public void assingmentNumber_isinncorrect(){
        boolean result = add_assignment_form_teacher.vlidateAsiingmentNo("50");
        assertEquals(true, result);
    }

    @Test(expected = NullPointerException.class)
    public void nullStringTest() {
        String str = null;
        assertTrue(str.isEmpty());
    }


}

