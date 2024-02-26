package ir.zinutech.android.flickrsearch.features.search

import android.view.View
import androidx.core.view.isVisible
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import kotlin.time.Duration

object TestUtil {

    fun delay(duration: Duration) {
        onView(isRoot()).perform(waitOnView(duration.inWholeMilliseconds))
    }

    fun isNotVisible(): Matcher<View> {
        return IsNotVisibleMatcher()
    }

    internal class IsNotVisibleMatcher : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("is not displayed on the screen to the user")
        }

        public override fun matchesSafely(view: View): Boolean {
            return !view.isVisible
        }
    }
    private fun waitOnView(millis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait a specified amount of time."
            }

            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(millis)
            }
        }
    }

}