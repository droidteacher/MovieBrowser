package hu.zsoltkiss.moviebrowser.data.api

import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import hu.zsoltkiss.moviebrowser.data.model.MoviesListResponse
import io.reactivex.rxjava3.core.Maybe
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") api_key: String): Maybe<MoviesListResponse>

    @GET("discover/movie")
    fun getMovies(@Query("api_key") api_key: String): Maybe<MoviesListResponse>

    @GET("search/movie")
    fun searchMovie(@Query("api_key") api_key: String, @Query("query") q: String): Maybe<MoviesListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path(value = "movie_id") movieId: Int, @Query("api_key") api_key: String): Maybe<MovieDetails>
}