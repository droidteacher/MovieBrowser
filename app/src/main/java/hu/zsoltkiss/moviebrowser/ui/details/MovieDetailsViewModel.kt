package hu.zsoltkiss.moviebrowser.ui.details

import androidx.compose.runtime.MutableState
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails

interface MovieDetailsViewModel {
    val movieDetailsState: MutableState<MovieDetails?>
    fun fetchDetails(id: Int)
}