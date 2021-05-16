package com.example.panthiya;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class Add_assignment_form_teacherTest extends TestCase {

    private Add_assignment_form_teacher add_assignment_form_teacher;

    boolean result = false;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testVlidateAsiingmentNo() {
        result = add_assignment_form_teacher.vlidateAsiingmentNo("50");
        assertEquals(true, result);
    }



}