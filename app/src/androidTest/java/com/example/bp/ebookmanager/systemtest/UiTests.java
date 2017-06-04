package com.example.bp.ebookmanager.systemtest;

import android.content.Context;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.LayoutAssertions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bp.ebookmanager.MainActivity;
import com.example.bp.ebookmanager.R;
import com.example.bp.ebookmanager.android.config.AndroidConfigurationFactory;
import com.example.bp.ebookmanager.config.Configuration;
import com.example.bp.ebookmanager.systemtest.utils.TestBookDataProvider;
import com.example.bp.ebookmanager.systemtest.utils.UiTestsConfiguration;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Ebook Manager
 * Created by Bartek on 2017-06-02.
 */

@org.junit.runner.RunWith(AndroidJUnit4.class)
@LargeTest
public class UiTests {

    private static TestBookDataProvider testBookDataProvider;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void beforeClass() {
        final UiTestsConfiguration configuration = new UiTestsConfiguration();
        testBookDataProvider = configuration.getTestBookDataProvider();
        MainActivity.setConfigurationFactory(new AndroidConfigurationFactory() {
            @Override
            public Configuration getConfiguration(Context context) {
                return configuration;
            }
        });
    }

    @Test
    public void checkIfNoOverlapsOnLongListOfBooks() {
        testBookDataProvider.setLongList();
        onView(withId(R.id.fab))
                .perform(click());

        checkIfNoOverlaps();
    }

    @Test
    public void checkIfNoOverlapsOnLongStringsInData() {
        testBookDataProvider.setSingleBook("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        onView(withId(R.id.fab))
                .perform(click());

        checkIfNoOverlaps();
    }

    private void checkIfNoOverlaps() {
        onView(withId(R.id.mainRelativeLayout))
                .check(LayoutAssertions.noOverlaps());

        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.detailsRelativeLayout))
                .check(LayoutAssertions.noOverlaps());
    }

    @Test
    public void checkIfProviderDialogApears() {
        onView(withId(R.id.sync_options_button))
                .perform(click());

        onView(withId(R.id.syncOptionsDialog))
                .check(matches(isDisplayed()));
    }
}

