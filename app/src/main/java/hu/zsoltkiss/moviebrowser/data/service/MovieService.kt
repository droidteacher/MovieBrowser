package hu.zsoltkiss.moviebrowser.data.service

import hu.zsoltkiss.moviebrowser.data.api.RetrofitClient
import hu.zsoltkiss.moviebrowser.data.model.Movie
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import hu.zsoltkiss.moviebrowser.data.model.MoviesListResponse
import io.reactivex.rxjava3.core.Maybe

class MovieService {

    fun getPopularMovies(): Maybe<List<Movie>> {
        return RetrofitClient.getPopularMovies().map { it.results ?: emptyList() }
    }

    fun getSimpsonsMovies(): Maybe<List<Movie>> {
        return RetrofitClient.getSimpsonsMovies().map { it.results ?: emptyList() }
    }

    fun searchFor(what: String): Maybe<List<Movie>> {
        return RetrofitClient.searchMovies(what).map { it.results ?: emptyList() }
    }

    fun getMovieDetails(id: Int): Maybe<MovieDetails> {
        return RetrofitClient.getDetails(id)
    }

}