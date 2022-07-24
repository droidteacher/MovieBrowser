package hu.zsoltkiss.moviebrowser

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import hu.zsoltkiss.moviebrowser.data.model.Movie
import hu.zsoltkiss.moviebrowser.ui.movies.moviesList
import org.junit.Rule
import org.junit.Test

class MoviesListTest {

    @get:Rule
    val testRule = createComposeRule()

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    private val movies = listOf(
        Movie(
            id = 15,
            originalTitle = "KKK",
            releaseDate = "2010-10-10",
            popularity = 4.3f
        ),
        Movie(
            id = 27,
            originalTitle = "VVV",
            releaseDate = "2020-01-27",
            popularity = 2300.5f
        ),
        Movie(
            id = 18,
            originalTitle = "PPP",
            releaseDate = "2017-05-27",
            popularity = 47521f
        )
    )


    @Test
    fun listItemsOnScreen() {
        testRule.setContent {
            LazyColumn {
                moviesList(
                    movies = movies,
                    context = appContext,
                    onCardSelect = {},
                    tmdbImageUrl = "fake"
                )
            }
        }

        movies.forEach {
            testRule.onNodeWithText(it.originalTitle!!).assertIsDisplayed()
            testRule.onNodeWithText(it.releaseDate!!).assertIsDisplayed()
            testRule.onNodeWithText(it.popularity.toString()).assertIsDisplayed()
        }
    }

}