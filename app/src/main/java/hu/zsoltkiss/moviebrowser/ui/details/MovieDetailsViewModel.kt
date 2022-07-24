package hu.zsoltkiss.moviebrowser.ui.details

import android.net.Uri
import androidx.compose.runtime.MutableState
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface MovieDetailsViewModel {
    val movieDetailsState: MutableState<MovieDetails?>
    val homepageUri: BehaviorSubject<Uri>
    fun fetchDetails(id: Int)
    fun requestHomepageNavigation()
}