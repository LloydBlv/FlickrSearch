package ir.zinutech.android.flickrsearch.features.search


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import ir.zinutech.android.flickrsearch.MainActivity
import ir.zinutech.android.flickrsearch.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMainActivity() {
        val textView = onView(
            allOf(
                withId(R.id.search_fragment_search_guide_tv),
                withText("Press on search button in the toolbar above to start searching"),
                withParent(withParent(withId(R.id.main_activity_container))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Press on search button in the toolbar above to start searching")))
    }
    @Test
    fun testMainActivity1() {
        val textView = onView(
            allOf(
                withId(R.id.search_fragment_search_guide_tv),
                withText("Press on search button in the toolbar above to start searching"),
                withParent(withParent(withId(R.id.main_activity_container))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Press on search button in the toolbar above to start searching")))
    }
}
