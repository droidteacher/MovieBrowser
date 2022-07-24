package hu.zsoltkiss.moviebrowser.ui.movies

import androidx.compose.runtime.MutableState
import hu.zsoltkiss.moviebrowser.data.model.Movie
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface MoviesViewModel {
    val processingState: MutableState<Boolean>
    val searchExpressionState: MutableState<String>
    val movieListState: MutableState<List<Movie>>
    val selectedMovieId: BehaviorSubject<Int>

    fun movieSelected(title: String)
    fun userInputChanged(userInput: String)
    fun search(expr: String)

}