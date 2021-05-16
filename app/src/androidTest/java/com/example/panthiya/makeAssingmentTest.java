package com.example.panthiya;

import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class makeAssingmentTest extends TestCase {

    @Rule
    public IntentsTestRule<makeAssingment> intentsTestRule = new IntentsTestRule<>(makeAssingment.class);

    @Test
    public void testIntent(){
        onView(withId(R.id.addFabButton)).perform(click());
        intended(hasComponent(Add_assignment_form_teacher.class.getName()));
    }

}