package ir.zinutech.android.flickrsearch.features.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.zinutech.android.flickrsearch.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {
    @Test
    fun whenFragmentLoadedViewIsSetCorrectly() {
        launchFragmentInHiltContainer<SearchFragment>()
        onView(withId(R.id.search_fragment_search_guide_tv))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.press_on_search_button)))
    }
}