package fi.solita.helloretrofit.views.items;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fi.solita.helloretrofit.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecordedItemsActivityViewTest {

    @Rule
    public ActivityTestRule<ItemsActivityView> mActivityTestRule = new ActivityTestRule<>(ItemsActivityView.class);

    @Test
    public void itemsActivityViewTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button1), withText("Load stuff"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

    }

}
