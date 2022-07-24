package hu.zsoltkiss.moviebrowser.ui.details

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.zsoltkiss.moviebrowser.R
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import hu.zsoltkiss.moviebrowser.ui.movies.loadImage
import hu.zsoltkiss.moviebrowser.ui.theme.detailsLabel
import hu.zsoltkiss.moviebrowser.ui.theme.detailsValue
import hu.zsoltkiss.moviebrowser.ui.theme.homepage

@Composable
fun MovieDetails(
    details: MovieDetails? = null,
    tmdbImageUrl: String,
    context: Context,
    onHomepageClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        if (details == null) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 50.dp)
            )
        } else {
            Text(
                text = details?.originalTitle ?: "",
                style = TextStyle(
                    fontSize = 24.sp
                ),
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Release date",
                    modifier = Modifier.weight(0.5f),
                    style = detailsLabel
                )
                Text(
                    details.releaseDate ?: "",
                    modifier = Modifier.weight(0.5f),
                    style = detailsValue
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Budget",
                    modifier = Modifier.weight(0.5f),
                    style = detailsLabel
                )
                Text(
                    details.budget.toString(),
                    modifier = Modifier.weight(0.5f),
                    style = detailsValue
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Revenue",
                    modifier = Modifier.weight(0.5f),
                    style = detailsLabel
                )
                Text(
                    details.revenue.toString(),
                    modifier = Modifier.weight(0.5f),
                    style = detailsValue
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Genres",
                    modifier = Modifier.weight(0.5f),
                    style = detailsLabel
                )
                Text(
                    details.listOfGenres,
                    modifier = Modifier.weight(0.5f),
                    style = detailsValue
                )
            }

            details.overview?.let {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = it
                )
            }

            details.posterPath?.let {
                Spacer(modifier = Modifier.height(20.dp))

                val imageUrl = tmdbImageUrl + it
                val bitmap = loadImage(
                    context = context,
                    imageUrl = imageUrl,
                    defaultImage = R.drawable.poster_placeholder
                ).value

                bitmap?.let { bmp ->
                    Image(
                        bitmap = bmp.asImageBitmap(),
                        contentDescription = "Poster image",
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp),
                        contentScale = ContentScale.Fit
                    )
                }

            }

            if (!details.homepage.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(5.dp))


                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Homepage:",
                        style = detailsLabel
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = details.homepage,
                        style = homepage,
                        modifier = Modifier.clickable { onHomepageClick() }
                    )
                }
            }
        }
    }

}
