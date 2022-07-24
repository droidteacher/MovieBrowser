package hu.zsoltkiss.moviebrowser.data.service

import hu.zsoltkiss.moviebrowser.data.api.MoviesApi
import hu.zsoltkiss.moviebrowser.data.model.Movie
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject
import javax.inject.Named

class MovieServiceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MovieService {

    @Inject
    @Named("apiKey")
    lateinit var apiKey: String

    override fun getPopularMovies(): Maybe<List<Movie>> {
        return moviesApi.getPopularMovies(apiKey).map { it.results ?: emptyList() }
    }

    override fun getSimpsonsMovies(): Maybe<List<Movie>> {
        return moviesApi.searchMovie(apiKey, "simpsons").map { it.results ?: emptyList() }
    }

    override fun searchFor(what: String): Maybe<List<Movie>> {
        return moviesApi.searchMovie(apiKey, what).map { it.results ?: emptyList() }
    }

    override fun getMovieDetails(id: Int): Maybe<MovieDetails> {
        return moviesApi.getMovieDetails(id, apiKey)
    }



}