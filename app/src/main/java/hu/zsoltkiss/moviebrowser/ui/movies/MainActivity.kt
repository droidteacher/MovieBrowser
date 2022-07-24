package hu.zsoltkiss.moviebrowser.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import hu.zsoltkiss.moviebrowser.R
import hu.zsoltkiss.moviebrowser.ui.common.MBAppBar
import hu.zsoltkiss.moviebrowser.ui.common.MBSearchBar
import hu.zsoltkiss.moviebrowser.ui.details.MovieDetailsActivity
import hu.zsoltkiss.moviebrowser.ui.theme.MovieBrowserTheme
import hu.zsoltkiss.moviebrowser.ui.theme.noResults
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val moviesViewModel: MoviesViewModelImpl by viewModels()

    private val disposables = CompositeDisposable()

    @Inject
    @Named("imageUrl")
    lateinit var tmdbImageUrl: String

    // TODO VM teszt, text styles

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesViewModel.selectedMovieId.subscribe { movieId ->
            Intent(this, MovieDetailsActivity::class.java).apply {
                putExtra(MovieDetailsActivity.extraKeyMovieId, movieId)
            }.also {
                startActivity(it)
            }
        }.addTo(disposables)

        setContent {
            MovieBrowserTheme {
                Scaffold(
                    topBar = {
                        MBAppBar(stringResource(id = R.string.app_name))
                    },
                    content = {
                        val movies by moviesViewModel.movieListState
                        val searchExpr by moviesViewModel.searchExpressionState
                        val processing by moviesViewModel.processingState

                        Column {
                            MBSearchBar(searchExpression = searchExpr, onUserInputChange = moviesViewModel::userInputChanged, onSubmitSearch = moviesViewModel::search)

                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if(processing) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .padding(top = 50.dp)
                                    )
                                } else {
                                    if (movies.isEmpty()) {
                                        Text(
                                            text = "No results",
                                            style = noResults,
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .padding(top = 50.dp)
                                        )
                                    } else {
                                        LazyColumn(
                                            modifier = Modifier.align(Alignment.Center)
                                        ) {
                                            moviesList(movies = movies, context = baseContext, onCardSelect = moviesViewModel::movieSelected, tmdbImageUrl = tmdbImageUrl)
                                        }
                                    }
                                }
                            }
                        }
                    },
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
