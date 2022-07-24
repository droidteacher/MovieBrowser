package hu.zsoltkiss.moviebrowser

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import hu.zsoltkiss.moviebrowser.data.model.Genre
import hu.zsoltkiss.moviebrowser.ui.details.MovieDetails
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val testRule = createComposeRule()

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    private val movie = hu.zsoltkiss.moviebrowser.data.model.MovieDetails(
        id = 101,
        originalTitle = "Some title",
        budget = 1000,
        revenue = 10000,
        genres = listOf(
            Genre(1, "AAA"),
            Genre(3, "CCC"),
        ),
        releaseDate = "2022-03-11",
        overview = "Details of the content",
        posterPath = null,
        homepage = "www.somehost.com/asdf"
    )

    @Test
    fun hasProperDataOnScreen() {
        testRule.setContent {
            MovieDetails(
                details = movie,
                context = appContext,
                tmdbImageUrl = "fake",
                onHomepageClick = { }
            )
        }

        testRule.onNodeWithText(movie.originalTitle!!).assertIsDisplayed()
        testRule.onNodeWithText("Release date").assertIsDisplayed()
        testRule.onNodeWithText(movie.releaseDate!!).assertIsDisplayed()
        testRule.onNodeWithText("Budget").assertIsDisplayed()
        testRule.onNodeWithText(movie.budget.toString()).assertIsDisplayed()
        testRule.onNodeWithText("Revenue").assertIsDisplayed()
        testRule.onNodeWithText(movie.revenue.toString()).assertIsDisplayed()
        testRule.onNodeWithText("Genres").assertIsDisplayed()
        testRule.onNodeWithText(movie.listOfGenres).assertIsDisplayed()
        testRule.onNodeWithText(movie.homepage!!).assertIsDisplayed()
    }

}