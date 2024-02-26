package ir.zinutech.android.flickrsearch

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import ir.zinutech.android.flickrsearch.features.search.SearchAdapter
import ir.zinutech.android.flickrsearch.features.search.TestUtil
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

class MainActivityRobot {
    fun clickSearchButton() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(androidx.appcompat.R.id.search_button),
                ViewMatchers.withContentDescription("Search"),
                ViewMatchers.withParent(
                    Matchers.allOf(
                        ViewMatchers.withId(androidx.appcompat.R.id.search_bar),
                        ViewMatchers.withParent(ViewMatchers.withId(R.id.action_search))
                    )
                ),
                ViewMatchers.isDisplayed()
            )
        ).perform(ViewActions.click())
    }
    fun isSearchButtonDisplayed() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(androidx.appcompat.R.id.search_button),
                ViewMatchers.withContentDescription("Search"),
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    fun isSearchButtonHidden() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(androidx.appcompat.R.id.search_button),
                ViewMatchers.withContentDescription("Search"),
            )
        ).check(ViewAssertions.matches(TestUtil.isNotVisible()))
    }
    fun checkSearchInputVisible() {
        Espresso.onView(
            ViewMatchers.withId(androidx.appcompat.R.id.search_edit_frame)
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    fun checkSearchInputNotVisible() {
        Espresso.onView(
            ViewMatchers.withId(androidx.appcompat.R.id.search_edit_frame)
        ).check(ViewAssertions.matches(TestUtil.isNotVisible()))
    }

    fun enterQuery(query: String) {
        val searchAutoComplete = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(androidx.appcompat.R.id.search_src_text),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(androidx.appcompat.R.id.search_plate),
                        childAtPosition(
                            ViewMatchers.withId(androidx.appcompat.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        searchAutoComplete.perform(ViewActions.replaceText(query), ViewActions.closeSoftKeyboard())
    }

    fun checkResultListVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.search_fragment_result_rv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    fun checkResultListNotVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.search_fragment_result_rv))
            .check(ViewAssertions.matches(TestUtil.isNotVisible()))
    }
    fun scrollToLastPosition() {
        Espresso.onView(ViewMatchers.withId(R.id.search_fragment_result_rv))
            .perform(RecyclerViewActions.scrollToLastPosition<SearchAdapter.ViewHolder>())
    }
    fun scrollToPosition(position: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.search_fragment_result_rv))
            .perform(RecyclerViewActions.scrollToPosition<SearchAdapter.ViewHolder>(position))
    }

    fun checkResultItemWithTextVisible(text: String) {
        Espresso.onView(ViewMatchers.withId(R.id.search_fragment_result_rv))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(text))))
    }
    fun clickClearQuery() {
        val closeButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(androidx.appcompat.R.id.search_close_btn),
                ViewMatchers.withContentDescription("Clear query"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(androidx.appcompat.R.id.search_plate),
                        childAtPosition(
                            ViewMatchers.withId(androidx.appcompat.R.id.search_edit_frame),
                            1
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        closeButton.perform(ViewActions.click())
    }
    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

}