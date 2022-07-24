package hu.zsoltkiss.moviebrowser.ui.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import hu.zsoltkiss.moviebrowser.data.service.MovieService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MovieDetailsViewModelImpl @Inject constructor(
    private val service: MovieService,
) : ViewModel(), MovieDetailsViewModel {

    private val disposables = CompositeDisposable()

    @Inject
    @Named("baseUrl")
    lateinit var url1: String

    @Inject
    @Named("imageUrl")
    lateinit var url2: String

    override val movieDetailsState: MutableState<MovieDetails?> = mutableStateOf(null)

    override fun fetchDetails(id: Int) {

        Log.d("KZs", "url1: $url1")
        Log.d("KZs", "url2: $url2")

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