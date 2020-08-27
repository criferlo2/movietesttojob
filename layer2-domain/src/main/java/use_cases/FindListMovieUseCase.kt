package use_cases

import entity.Movie
import entity.Popular
import interfaces.IMovieRepository
import mappers.toMovie
import mappers.toPopular
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import use_cases.interfaces.IFindListMovieUseCase


open class FindListMovieUseCase : IFindListMovieUseCase, KoinComponent {
    private val movieRepository: IMovieRepository by inject()

    override suspend fun getAllMoviesRemote(): Popular {
        return movieRepository.getPopularRemote().toPopular()
    }

    override suspend fun getAllMoviesLocal(): List<Movie> {
        return movieRepository.getAllMovies().map { e -> e.toMovie() }
    }
}