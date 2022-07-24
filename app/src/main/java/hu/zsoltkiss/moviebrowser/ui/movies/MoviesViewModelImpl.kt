package hu.zsoltkiss.moviebrowser.ui.movies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import hu.zsoltkiss.moviebrowser.data.model.Movie
import hu.zsoltkiss.moviebrowser.data.service.MovieService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import kotlin.math.exp

class MoviesViewModelImpl: ViewModel(), MoviesViewModel {
    override val processingState: MutableState<Boolean> = mutableStateOf(false)
    override val searchExpressionState: MutableState<String> = mutableStateOf("simpsons")
    override val movieListState: MutableState<List<Movie>> = mutableStateOf(emptyList())

    override val selectedMovieId: BehaviorSubject<Int> = BehaviorSubject.create()

    private val service = MovieService()
    private val disposables = CompositeDisposable()

    init {
        processingState.value = true
        service.getSimpsonsMovies()
            .subscribeOn(Schedulers.io())
            .delay(1, TimeUnit.SECONDS)
            .subscribe {
                movieListState.value = it.filter { someMovie ->
                    someMovie.originalTitle != null
                }
                processingState.value = false
            }
            .addTo(disposables)

    }

    override fun movieSelected(title: String) {
        movieListState.value.firstOrNull { it.originalTitle == title }?.let {
            selectedMovieId.onNext(it.id)
        }
    }

    override fun userInputChanged(userInput: String) {
        Log.d("KZs", "userInputChanged: $userInput")
        searchExpressionState.value = userInput
    }

    override fun search(expr: String) {
        processingState.value = true
        service.searchFor(expr)
            .subscribeOn(Schedulers.io())
            .delay(1, TimeUnit.SECONDS)
            .subscribe {
                movieListState.value = it.filter { someMovie ->
                    someMovie.originalTitle != null
                }
                processingState.value = false
            }
            .addTo(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }


}