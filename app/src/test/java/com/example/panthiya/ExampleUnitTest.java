package com.example.panthiya;

import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.AfterTest;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private my_note_student my_note_student;

    @Before
    public void setup(){
        my_note_student = new my_note_student();
    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void TestGenerateMarksFunc(){
        float result = my_note_student.generateMarks(Float.parseFloat("20"),Float.parseFloat("30"),10,20);
        assertEquals(35.0F, result);
    }
}