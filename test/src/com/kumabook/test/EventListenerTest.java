package com.kumabook.test;

import com.jayway.android.robotium.solo.Solo;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;

public class EventListenerTest extends
ActivityInstrumentationTestCase2<FirstActivity> {
    private Solo solo;
    private Instrumentation instrumentation;
    @SuppressWarnings("deprecation")
    public EventListenerTest() {
        super("com.kumabook.demo", FirstActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        instrumentation = getInstrumentation();
    }
    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testNotifyMultiActivity() {
        final FirstActivity firstActivity = (FirstActivity) solo.getCurrentActivity();
        String nameString = "hiroki";
        solo.assertCurrentActivity("Expected FirstActivity", "FirstActivity");
        firstActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstActivity.nextButton.performClick();
            }
        });
        instrumentation.waitForIdleSync();
        solo.waitForActivity("SecondActivity");
        solo.assertCurrentActivity("Expected SecondActivity", "SecondActivity");
        solo.clearEditText(0);
        solo.enterText(0, nameString);
        final SecondActivity secondActivity = (SecondActivity) solo.getCurrentActivity();
        secondActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                secondActivity.notifyButton.performClick();
            }
        });
        instrumentation.waitForIdleSync();
        assertEquals("Expected changed string " + nameString, nameString, 
                secondActivity.peopleNameTextView.getText());
        assertEquals("Expected changed string " + nameString, nameString, 
                firstActivity.peopleNameTextView.getText());
    }
}
