package hu.zsoltkiss.moviebrowser

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import hu.zsoltkiss.moviebrowser.ui.movies.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val testRule = createAndroidComposeRule<MainActivity>()

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun hasSearchBarWithInitialValue() {
        testRule.onRoot(useUnmergedTree = true).printToLog("KZs")

        testRule.onNodeWithContentDescription("Search icon").assertIsDisplayed()
        testRule.onNodeWithContentDescription("Clear icon").assertIsDisplayed()
        testRule.onNodeWithText("Search").assertIsDisplayed()
        testRule.onNodeWithText("simpsons").assertIsDisplayed()
    }

    @Test
    fun hasAppBarWithTitle() {
        testRule.onNodeWithTag("MBAppBar").assertIsDisplayed()
        testRule.onNodeWithText(appContext.getString(R.string.app_name)).assertIsDisplayed()
    }

}