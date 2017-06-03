package com.example.bp.ebookmanager.integrationtest.empik;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.bp.ebookmanager.MainActivity;
import com.example.bp.ebookmanager.R;
import com.example.bp.ebookmanager.dataprovider.empik.EbookEmpikWebDataProviderFactory;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.formats.EpubDetails;
import com.example.bp.ebookmanager.model.formats.MobiDetails;
import com.example.bp.ebookmanager.model.formats.Mp3Details;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertEquals;
/**
 * Ebook Manager
 * Created by Bartek on 2017-06-02.
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
public class EmpikEbookIntegrationTests {
    public static final int EXPECTED_BOOK_COUNT = 1;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void beforeClass() {
        MainActivity.setConfigurationFactory(EmpikTestConfiguration.getFactory(EbookEmpikWebDataProviderFactory.instance()));
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

        int wegnerPosition = getWegnerPosition();

        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(wegnerPosition)
                .perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.detailsTitle))
                .check(matches(withText("Gdybym miała brata. Opowieści z meekhańskiego pogranicza")));
        onView(withId(R.id.detailsAuthor))
                .check(matches(withText("Wegner Robert M.")));
        onData(anything())
                .inAdapterView(withId(R.id.detailsListView))
                .atPosition(0)
                .onChildView(withId(R.id.detailValue))
                .check(matches(withText("Wydawnictwo Powergraph")));
        onData(anything())
                .inAdapterView(withId(R.id.detailsListView))
                .atPosition(1)
                .onChildView(withId(R.id.detailValue))
                .check(matches(withText(EpubDetails.FORMAT_NAME + ", " + MobiDetails.FORMAT_NAME)));

        onData(anything())
                .inAdapterView(withId(R.id.formatSpinner))
                .atPosition(0)
                .check(matches(withText(EpubDetails.FORMAT_NAME)));
        onData(anything())
                .inAdapterView(withId(R.id.formatSpinner))
                .atPosition(1)
                .check(matches(withText(MobiDetails.FORMAT_NAME)));
    }

    private int getWegnerPosition() {
        ListView listView = (ListView) activityRule.getActivity().findViewById(R.id.listView);
        ListAdapter adapter = listView.getAdapter();
        int metro2034Position = -1;
        for (int i = 0; i < adapter.getCount(); ++i) {
            Book itemData = (Book) adapter.getItem(i);
            if (itemData.getTitle().equals("Gdybym miała brata. Opowieści z meekhańskiego pogranicza")) {
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

        int wegnerPosition = getWegnerPosition();
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(wegnerPosition)
                .perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.downloadButton))
                .check(matches(isEnabled()));
    }
}
