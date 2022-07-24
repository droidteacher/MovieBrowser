package hu.zsoltkiss.moviebrowser.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.AndroidEntryPoint
import hu.zsoltkiss.moviebrowser.R
import hu.zsoltkiss.moviebrowser.ui.common.MBAppBar
import hu.zsoltkiss.moviebrowser.ui.theme.MovieBrowserTheme
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MovieDetailsActivity : ComponentActivity() {

    private val movieDetailsViewModel: MovieDetailsViewModelImpl by viewModels()

    private val disposables = CompositeDisposable()

    private var movieId: Int? = null

    @Inject
    @Named("imageUrl")
    lateinit var tmdbImageUrl: String

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = intent.extras?.getInt(extraKeyMovieId)

        movieDetailsViewModel.homepageUri.subscribe { uri ->
            startActivity(Intent(Intent.ACTION_VIEW).also {
                it.data = uri
            })
        }
            .addTo(disposables)

        val movieDetails by movieDetailsViewModel.movieDetailsState

        setContent {
            MovieBrowserTheme {
                Scaffold(
                    topBar = {
                        MBAppBar(
                            title = stringResource(id = R.string.movie_details),
                            backNavigation = {
                                Icon(
                                    Icons.Default.ArrowBack,
                                    contentDescription = "Back navigation icon",
                                    modifier = Modifier.clickable { finish() }
                                )
                            }
                        )
                    },
                    content = {
                        MovieDetails(
                            details = movieDetails,
                            context = baseContext,
                            tmdbImageUrl = tmdbImageUrl,
                            onHomepageClick = movieDetailsViewModel::requestHomepageNavigation
                        )
                    }
                )
            }
        }

    }

    override fun onStart() {
        super.onStart()
        movieId?.let {
            movieDetailsViewModel.fetchDetails(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    companion object {
        const val extraKeyMovieId = "movieId"
    }

}