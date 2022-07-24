package hu.zsoltkiss.moviebrowser.ui.details

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
import hu.zsoltkiss.moviebrowser.R
import hu.zsoltkiss.moviebrowser.ui.common.MBAppBar
import hu.zsoltkiss.moviebrowser.ui.theme.MovieBrowserTheme

class MovieDetailsActivity : ComponentActivity() {

    private val movieDetailsViewModel: MovieDetailsViewModelImpl by viewModels()

    private var movieId: Int? = null

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = intent.extras?.getInt(extraKeyMovieId)

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
                        MovieDetails(details = movieDetails, context = baseContext)

//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .verticalScroll(rememberScrollState())
//                                .padding(10.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally
//
//                        ) {
//
//                            if (movieDetails == null) {
//                                CircularProgressIndicator(
//                                    modifier = Modifier
//                                        .padding(top = 50.dp)
//                                )
//                            } else {
//                                Text(
//                                    text = movieDetails?.originalTitle ?: "",
//                                    style = TextStyle(
//                                        fontSize = 24.sp
//                                    ),
//                                )
//
//                                Spacer(modifier = Modifier.height(5.dp))
//
//                                Row(
//                                    modifier = Modifier.fillMaxWidth()
//                                ) {
//                                    Text(
//                                        "Release date",
//                                        modifier = Modifier.weight(0.5f)
//                                    )
//                                    Text(
//                                        movieDetails?.releaseDate ?: "",
//                                        modifier = Modifier.weight(0.5f)
//                                    )
//                                }
//
//                                Spacer(modifier = Modifier.height(5.dp))
//
//                                Row(
//                                    modifier = Modifier.fillMaxWidth()
//                                ) {
//                                    Text(
//                                        "Budget",
//                                        modifier = Modifier.weight(0.5f)
//                                    )
//                                    Text(
//                                        movieDetails?.budget?.toString() ?: "",
//                                        modifier = Modifier.weight(0.5f)
//                                    )
//                                }
//
//                                Spacer(modifier = Modifier.height(5.dp))
//
//                                Row(
//                                    modifier = Modifier.fillMaxWidth()
//                                ) {
//                                    Text(
//                                        "Revenue",
//                                        modifier = Modifier.weight(0.5f)
//                                    )
//                                    Text(
//                                        movieDetails?.revenue?.toString() ?: "",
//                                        modifier = Modifier.weight(0.5f)
//                                    )
//                                }
//
//                                Spacer(modifier = Modifier.height(5.dp))
//
//                                Row(
//                                    modifier = Modifier.fillMaxWidth()
//                                ) {
//                                    Text(
//                                        "Genres",
//                                        modifier = Modifier.weight(0.5f)
//                                    )
//                                    Text(
//                                        movieDetails?.listOfGenres ?: "",
//                                        modifier = Modifier.weight(0.5f)
//                                    )
//                                }
//
//                                movieDetails?.overview?.let {
//                                    Spacer(modifier = Modifier.height(20.dp))
//                                    Text(
//                                        text = it
//                                    )
//                                }
//
//                                movieDetails?.posterPath?.let {
//                                    Spacer(modifier = Modifier.height(20.dp))
//
//                                    val imageUrl = RetrofitClient.TMDB_IMAGEURL + it
//                                    val bitmap = loadImage(
//                                        context = baseContext,
//                                        imageUrl = imageUrl,
//                                        defaultImage = R.drawable.poster_placeholder
//                                    ).value
//
//                                    bitmap?.let { bmp ->
//                                        Image(
//                                            bitmap = bmp.asImageBitmap(),
//                                            contentDescription = "Poster image",
//                                            modifier = Modifier
//                                                .width(300.dp)
//                                                .height(300.dp),
//                                            contentScale = ContentScale.Fit
//                                        )
//                                    }
//
//                                }
//
//                                if (!movieDetails?.homepage.isNullOrEmpty()) {
//                                    Spacer(modifier = Modifier.height(5.dp))
//
//
//                                    Row(
//                                        modifier = Modifier.fillMaxWidth()
//                                    ) {
//                                        Text(
//                                            "Homepage:",
//                                        )
//                                    }
//
//                                    Row(
//                                        modifier = Modifier.fillMaxWidth()
//                                    ) {
//                                        Text(
//                                            text = movieDetails?.homepage!!
//                                        )
//                                    }
//                                }
//                            }
//                        }
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

    companion object {
        const val extraKeyMovieId = "movieId"
    }

}