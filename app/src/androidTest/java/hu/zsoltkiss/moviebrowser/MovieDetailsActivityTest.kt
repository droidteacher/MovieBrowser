package hu.zsoltkiss.moviebrowser

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import hu.zsoltkiss.moviebrowser.ui.details.MovieDetailsActivity
import hu.zsoltkiss.moviebrowser.ui.movies.MainActivity
import org.junit.Rule
import org.junit.Test

class MovieDetailsActivityTest {

    @get:Rule
    val testRule = createAndroidComposeRule<MovieDetailsActivity>()

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun hasAppBarWithTitle() {
        testRule.onNodeWithTag("MBAppBar").assertIsDisplayed()
        testRule.onNodeWithText(appContext.getString(R.string.movie_details)).assertIsDisplayed()
    }
}