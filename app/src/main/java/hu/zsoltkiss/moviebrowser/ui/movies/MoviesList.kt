package hu.zsoltkiss.moviebrowser.ui.movies

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import hu.zsoltkiss.moviebrowser.R
import hu.zsoltkiss.moviebrowser.data.model.Movie
import hu.zsoltkiss.moviebrowser.ui.theme.popularityIconTint

fun LazyListScope.moviesList(
    movies: List<Movie>,
    context: Context,
    tmdbImageUrl: String,
    onCardSelect: (String) -> Unit
) {
    items(movies) { movie ->
        Log.d("KZs", "posterPath: ${movie.posterPath}")

        MovieCard(
            title = movie.originalTitle!!,
            releaseDate = movie.releaseDate,
            popularity = movie.popularity,
            pathToImage = movie.posterPath,
            tmdbImageUrl = tmdbImageUrl,
            context = context,
            onSelect = onCardSelect
        )
    }
}

@Composable
fun MovieCard(
    title: String,
    releaseDate: String?,
    popularity: Float?,
    pathToImage: String?,
    tmdbImageUrl: String,
    context: Context,
    onSelect: (String) -> Unit
) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp).clickable {
            onSelect(title)
        }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.weight(0.7f)
                )

                Row(
                    modifier = Modifier.weight(0.3f)
                ) {
                    Icon(Icons.Filled.Star , contentDescription = "Star icon", tint = MaterialTheme.colors.popularityIconTint)
                    Text(text = popularity?.toString() ?: "")
                }
            }

            releaseDate?.let { dateString ->
                Text(text = dateString)
            }

            pathToImage?.let {
                val imageUrl = tmdbImageUrl + it

                val bitmap = loadImage(context = context, imageUrl = imageUrl, defaultImage = R.drawable.poster_placeholder).value

                bitmap?.let { bmp ->
                    Image(
                        bitmap = bmp.asImageBitmap(),
                        contentDescription = "Poster image",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop

                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun loadImage(
    context: Context,
    imageUrl: String,
    @DrawableRes defaultImage: Int
): MutableState<Bitmap?> {

    val bitmapsState: MutableState<Bitmap?> = mutableStateOf(null)

    Glide.with(context).asBitmap().load(defaultImage).into(object: CustomTarget<Bitmap>(){
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            bitmapsState.value = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {
            // do nothing
        }

    })

    Glide.with(context).asBitmap().load(imageUrl).into(object: CustomTarget<Bitmap>(){
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            bitmapsState.value = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {
            // do nothing
        }

    })

    return bitmapsState

}


