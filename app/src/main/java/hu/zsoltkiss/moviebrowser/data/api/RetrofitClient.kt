package hu.zsoltkiss.moviebrowser.data.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import hu.zsoltkiss.moviebrowser.BuildConfig
import hu.zsoltkiss.moviebrowser.data.model.MovieDetails
import hu.zsoltkiss.moviebrowser.data.model.MoviesListResponse
import io.reactivex.rxjava3.core.Maybe
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.exp

//object RetrofitClient {
//
//    private val moviesApi: MoviesApi
//    private val API_KEY = BuildConfig.TMDB_API_KEY
//    private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
//    const val TMDB_IMAGEURL = "https://image.tmdb.org/t/p/w500/"
//
//    init {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val builder = OkHttpClient.Builder().addInterceptor(interceptor)
//        val okHttpClient = builder.build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl(TMDB_BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .client(okHttpClient)
//            .build()
//        moviesApi = retrofit.create(MoviesApi::class.java)
//    }
//
//    fun getPopularMovies(): Maybe<MoviesListResponse> {
//        return moviesApi.getPopularMovies(API_KEY)
//    }
//
//    fun getSimpsonsMovies(): Maybe<MoviesListResponse> {
//        return moviesApi.searchMovie(API_KEY, "simpsons")
//    }
//
//    fun searchMovies(expr: String): Maybe<MoviesListResponse> {
//        return moviesApi.searchMovie(API_KEY, expr)
//    }
//
//    fun getDetails(id: Int): Maybe<MovieDetails> {
//        return moviesApi.getMovieDetails(id, API_KEY)
//    }
//}