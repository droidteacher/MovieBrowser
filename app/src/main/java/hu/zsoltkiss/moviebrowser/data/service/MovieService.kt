package hu.zsoltkiss.moviebrowser.data.service

import hu.zsoltkiss.moviebrowser.data.model.Movie
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import io.reactivex.rxjava3.core.Maybe

interface MovieService {
    fun getPopularMovies(): Maybe<List<Movie>>
    fun getSimpsonsMovies(): Maybe<List<Movie>>
    fun searchFor(what: String): Maybe<List<Movie>>
    fun getMovieDetails(id: Int): Maybe<MovieDetails>
}