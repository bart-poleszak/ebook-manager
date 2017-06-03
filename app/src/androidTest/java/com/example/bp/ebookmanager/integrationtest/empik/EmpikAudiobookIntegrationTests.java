package com.example.bp.ebookmanager.integrationtest.empik;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.bp.ebookmanager.MainActivity;
import com.example.bp.ebookmanager.R;
import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.config.Configuration;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.empik.AudiobookEmpikWebDataProviderFactory;
import com.example.bp.ebookmanager.dataprovider.empik.EmpikWebDataProviderFactory;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.formats.Mp3Details;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

/**
 * ebook-manager
 * Created by bart-poleszak on 29.05.2017.
 */


/**

 Before running this class you have to:
 - Open Ebook Manager app
 - In the data providers menu ("i" in the top right corner), make sure that only "Empik - audiobooki" option is selected
 - Press "done" and the sync button
 - Log in with proper Empik credentials

 Such actions are necessary, because login form behaviour is too unstable to be automated.

 */

@org.junit.runner.RunWith(AndroidJUnit4.class)
@LargeTest
public class EmpikAudiobookIntegrationTests {
    public static final int EXPECTED_BOOK_COUNT = 3;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void beforeClass() {
        MainActivity.setConfigurationFactory(EmpikTestConfiguration.getFactory(AudiobookEmpikWebDataProviderFactory.instance()));
    }

    @Ignore
    public void promptWithLoginScreenWhenThereAreNoCookies() {
        android.webkit.CookieManager.getInstance().removeAllCookie();
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.dialog_web_view)).check(matches(isDisplayed()));
    }

    @Test
    public void booksAreLoading() throws InterruptedException {
        onView(withId(R.id.fab)).perform(click());
        Thread.sleep(1000);

        ListView listView = (ListView) activityRule.getActivity().findViewById(R.id.listView);
        assertEquals(EXPECTED_BOOK_COUNT, listView.getCount());
    }

    @Test
    public void bookDetailsAreLoading() throws InterruptedException {
        onView(withId(R.id.fab)).perform(click());
        Thread.sleep(1000);

        int metro2034Position = getMetro2034Position();

        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(metro2034Position)
                .perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.detailsTitle))
                .check(matches(withText("Metro 2034")));
        onView(withId(R.id.detailsAuthor))
                .check(matches(withText("Glukhovsky Dmitry")));
        onData(anything())
                .inAdapterView(withId(R.id.detailsListView))
                .atPosition(0)
                .onChildView(withId(R.id.detailValue))
                .check(matches(withText("Wydawnictwo Insignis")));
        onData(anything())
                .inAdapterView(withId(R.id.detailsListView))
                .atPosition(1)
                .onChildView(withId(R.id.detailValue))
                .check(matches(withText("Opracowanie zbiorowe")));
        onData(anything())
                .inAdapterView(withId(R.id.detailsListView))
                .atPosition(2)
                .onChildView(withId(R.id.detailValue))
                .check(matches(withText(Mp3Details.FORMAT_NAME)));
        onData(anything())
                .inAdapterView(withId(R.id.detailsListView))
                .atPosition(3)
                .onChildView(withId(R.id.detailValue))
                .check(matches(withText("Gosztyła Krzysztof")));

        onData(anything())
                .inAdapterView(withId(R.id.formatSpinner))
                .atPosition(0)
                .check(matches(withText(Mp3Details.FORMAT_NAME)));
    }

    private int getMetro2034Position() {
        ListView listView = (ListView) activityRule.getActivity().findViewById(R.id.listView);
        ListAdapter adapter = listView.getAdapter();
        int metro2034Position = -1;
        for (int i = 0; i < adapter.getCount(); ++i) {
            Book itemData = (Book) adapter.getItem(i);
            if (itemData.getTitle().equals("Metro 2034")) {
                metro2034Position = i;
                break;
            }
        }
        return metro2034Position;
    }

    @Test
    public void downloadIsActiveAfterSynchronization() throws InterruptedException {
        onView(withId(R.id.fab)).perform(click());
        Thread.sleep(1000);

        int metro2034Position = getMetro2034Position();
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(metro2034Position)
                .perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.downloadButton))
                .check(matches(isEnabled()));
    }
}
