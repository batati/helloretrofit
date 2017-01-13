package fi.solita.helloretrofit.views.items;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Test;
import java.util.ArrayList;

import fi.solita.helloretrofit.R;
import fi.solita.helloretrofit.models.items.Item;
import fi.solita.helloretrofit.models.items.ItemHolder;
import fi.solita.helloretrofit.utils.IntentUtils;
import utils.TestUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static utils.TestUtils.ITEM_TITLE1;
import static utils.TestUtils.ITEM_TITLE2;
import static utils.TestUtils.ITEM_TITLE3;
import static utils.TestUtils.getHolder;

/**
 * Created by eetupa on 15/09/16.
 */
public class DetailActivityViewTest {

    public ActivityTestRule<DetailActivityView> detailActivityViewActivityTestRule = new ActivityTestRule<>(DetailActivityView.class, false, false);

    @Test
    public void launchedWithPosition0_FirstItemShowed() {
        ItemHolder holder = getHolder();
        Intent intent = new Intent();
        intent.putExtra(IntentUtils.EXTRA_POSITION, 0);
        intent.putExtra(IntentUtils.EXTRA_ITEMHOLDER, holder);
        detailActivityViewActivityTestRule.launchActivity(intent);

        onView(withText(ITEM_TITLE1)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeRight());
        onView(withText(ITEM_TITLE1)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText(ITEM_TITLE2)).check(matches(isDisplayed()));
    }

    @Test
    public void launchedWithPosition1_MiddleItemShowed() {
        ItemHolder holder = getHolder();
        Intent intent = new Intent();
        intent.putExtra(IntentUtils.EXTRA_POSITION, 1);
        intent.putExtra(IntentUtils.EXTRA_ITEMHOLDER, holder);
        detailActivityViewActivityTestRule.launchActivity(intent);

        onView(withText(ITEM_TITLE2)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeRight());
        onView(withText(ITEM_TITLE1)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText(ITEM_TITLE2)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText(ITEM_TITLE3)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeRight());
        onView(withText(ITEM_TITLE2)).check(matches(isDisplayed()));
    }

    @Test
    public void launchedWithPositionLast_LastItemShowed() {
        ItemHolder holder = getHolder();
        Intent intent = new Intent();
        intent.putExtra(IntentUtils.EXTRA_POSITION, 2);
        intent.putExtra(IntentUtils.EXTRA_ITEMHOLDER, holder);
        detailActivityViewActivityTestRule.launchActivity(intent);

        onView(withText(ITEM_TITLE3)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText(ITEM_TITLE3)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeRight());
        onView(withText(ITEM_TITLE2)).check(matches(isDisplayed()));
    }

}