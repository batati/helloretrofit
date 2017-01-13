package fi.solita.helloretrofit.views.items;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.ArrayList;

import fi.solita.helloretrofit.DaggerRule;
import fi.solita.helloretrofit.R;
import fi.solita.helloretrofit.models.items.Item;
import fi.solita.helloretrofit.models.items.ItemHolder;
import fi.solita.helloretrofit.presenters.items.ItemsActivityPresenter;
import fi.solita.helloretrofit.utils.IntentUtils;
import fi.solita.helloretrofit.views.items.ItemsActivityView;
import rx.Observable;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static utils.TestUtils.ITEM_TITLE1;
import static utils.TestUtils.ITEM_TITLE2;
import static utils.TestUtils.ITEM_TITLE3;
import static utils.TestUtils.getHolder;

/**
 * Created by eetupa on 13/09/16.
 */

@RunWith(AndroidJUnit4.class)
public class ItemsActivityViewTest {

    public final DaggerRule daggerRule = new DaggerRule(getTargetContext());

    public ActivityTestRule<ItemsActivityView> itemActivityViewActivityTestRule = new ActivityTestRule<>(ItemsActivityView.class, false, true);

    // DaggerRule needs to be setup mock dependencies before we run the tests.
    @Rule
    public TestRule testRule = RuleChain.outerRule(daggerRule).around(itemActivityViewActivityTestRule);

    @Test
    public void launchApp_buttonShowing() {
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.textview_selection)).check(matches(isDisplayed()));
        onView(withId(R.id.textview_selection)).check(matches(withText(R.string.textview_selection_placeholder)));
        onView(withId(R.id.progressbar)).check(matches(not(isDisplayed())));
    }

    @Test
    public void pressLoadButton_itemsShowing() {
        when(daggerRule.mockDataManager().getItems(anyString())).thenReturn(Observable.just(getHolder()));

        onView(withId(R.id.button1)).perform(
                click());

        onView(withText(ITEM_TITLE1)).check(matches(isDisplayed()));
        onView(withText(ITEM_TITLE2)).check(matches(isDisplayed()));
        onView(withText(ITEM_TITLE3)).check(matches(isDisplayed()));
    }

    @Test
    public void pressItems_intentBuiltWithPositionAndHolder() {
        ItemHolder holder = getHolder();
        when(daggerRule.mockDataManager().getItems(anyString())).thenReturn(Observable.just(holder));

        Intents.init();
        onView(withId(R.id.textview_selection)).check(matches(withText(R.string.textview_selection_placeholder)));
        onView(withId(R.id.button1)).perform(
                click());
        onView(withText(ITEM_TITLE1)).perform(
                click());
        intended(allOf(
                hasComponent(DetailActivityView.class.getName()),
                hasExtras(allOf(
                        hasEntry(IntentUtils.EXTRA_POSITION, 0),
                        hasEntry(IntentUtils.EXTRA_ITEMHOLDER, holder)
                ))
        ));
        pressBack();
        Intents.release();

        Intents.init();
        onView(withId(R.id.button1)).perform(
                click());
        onView(withText(ITEM_TITLE2)).perform(
                click());
        intended(allOf(
                hasComponent(DetailActivityView.class.getName()),
                hasExtras(allOf(
                        hasEntry(IntentUtils.EXTRA_POSITION, 1),
                        hasEntry(IntentUtils.EXTRA_ITEMHOLDER, holder)
                ))
        ));
        pressBack();
        Intents.release();

        Intents.init();
        onView(withId(R.id.button1)).perform(
                click());
        onView(withText(ITEM_TITLE3)).perform(
                click());
        intended(allOf(
                hasComponent(DetailActivityView.class.getName()),
                hasExtras(allOf(
                        hasEntry(IntentUtils.EXTRA_POSITION, 2),
                        hasEntry(IntentUtils.EXTRA_ITEMHOLDER, holder)
                ))
        ));
        Intents.release();
    }


}