package ir.zinutech.android.flickrsearch


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import ir.zinutech.android.flickrsearch.features.search.TestUtil
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.time.Duration.Companion.seconds

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityEndToEndTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityEndToEndTest() {
        val robot = MainActivityRobot()
        robot.run {
            isSearchButtonDisplayed()
            clickSearchButton()
            isSearchButtonHidden()
            checkSearchInputVisible()
            enterQuery("sea")
            TestUtil.delay(4.seconds)
            checkResultListVisible()
            scrollToLastPosition()
            checkResultItemWithTextVisible("Hint of Green")
            scrollToPosition(0)
            checkResultItemWithTextVisible("Lifeguard")
            clickClearQuery()
            TestUtil.delay(1.seconds)
            checkResultListNotVisible()
            clickClearQuery()
            isSearchButtonDisplayed()
            checkSearchInputNotVisible()
        }
    }


}
