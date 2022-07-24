package hu.zsoltkiss.moviebrowser.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import hu.zsoltkiss.moviebrowser.data.service.MovieService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MovieDetailsViewModelImpl : ViewModel(), MovieDetailsViewModel {

    private val service = MovieService()
    private val disposables = CompositeDisposable()

    override val movieDetailsState: MutableState<MovieDetails?> = mutableStateOf(null)

    override fun fetchDetails(id: Int) {
        service.getMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .delay(1, TimeUnit.SECONDS)
            .subscribe(
                {
                    movieDetailsState.value = it
                },
                {
                    it.printStackTrace()
                }
            )
            .addTo(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}