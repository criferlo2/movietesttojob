package use_cases

import entity.Movie
import interfaces.IMovieRepository
import mappers.toMovieDatabaseEntity
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import use_cases.interfaces.IAddMovieUseCase

class AddMovieUseCase : IAddMovieUseCase, KoinComponent {
    private val movieRepository: IMovieRepository by inject()
    override suspend fun addMovie(movie: Movie): Unit {
        return movieRepository.insertMovie(movie.toMovieDatabaseEntity())
    }

}