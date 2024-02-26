package ir.zinutech.android.flickrsearch


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import ir.zinutech.android.flickrsearch.data.features.search.SearchRepositoryModule
import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import ir.zinutech.android.flickrsearch.domain.features.search.repositories.SearchRepository
import ir.zinutech.android.flickrsearch.features.search.TestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import kotlin.time.Duration.Companion.seconds

@LargeTest
@RunWith(AndroidJUnit4::class)
@UninstallModules(SearchRepositoryModule::class)
@HiltAndroidTest
class MainActivityEndToEndTestWithHilt {

    @get:Rule
    var rule: RuleChain = RuleChain.outerRule(HiltAndroidRule(this)).
        around(ActivityScenarioRule(MainActivity::class.java))

    @BindValue
    @JvmField
    val searchRepository: SearchRepository = FakeSearchRepository()


    class FakeSearchRepository : SearchRepository {
        override suspend fun search(tag: String): List<FlickrPhoto> {
            return createTestPhotos()
        }

        private fun createTestPhotos(): List<FlickrPhoto> {
            return (1..100).map {
                FlickrPhoto(
                    id = "id$it",
                    title = "title$it",
                    url = "https://url$it"
                )
            }
        }
    }

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
            checkResultItemWithTextVisible("title100")
            checkResultItemWithTextVisible("title99")
            checkResultItemWithTextVisible("title98")
            scrollToPosition(0)
            checkResultItemWithTextVisible("title1")
            checkResultItemWithTextVisible("title2")
            checkResultItemWithTextVisible("title3")
            clickClearQuery()
            TestUtil.delay(1.seconds)
            checkResultListNotVisible()
            clickClearQuery()
            isSearchButtonDisplayed()
            checkSearchInputNotVisible()
        }
    }


}
