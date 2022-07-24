package hu.zsoltkiss.moviebrowser.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.zsoltkiss.moviebrowser.data.service.MovieService
import hu.zsoltkiss.moviebrowser.data.service.MovieServiceImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class ServiceModule {

    @Binds
    abstract fun bindMovieService(impl: MovieServiceImpl): MovieService
}