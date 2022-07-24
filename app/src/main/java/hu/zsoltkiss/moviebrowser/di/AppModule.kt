package hu.zsoltkiss.moviebrowser.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import hu.zsoltkiss.moviebrowser.BuildConfig
import hu.zsoltkiss.moviebrowser.data.api.MoviesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl() = BuildConfig.TMDB_BASE_URL

    @Provides
    @Named("imageUrl")
    fun provideImageUrl() = BuildConfig.TMDB_IMAGE_URL

    @Provides
    @Named("apiKey")
    fun provideApiKey() = BuildConfig.TMDB_API_KEY

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor().also { it.setLevel(HttpLoggingInterceptor.Level.BODY) }
            )
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @Named("baseUrl") baseUrl: String): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()






}