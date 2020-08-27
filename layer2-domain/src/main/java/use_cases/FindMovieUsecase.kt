package use_cases

import entity.Movie
import interfaces.IMovieRepository
import mappers.toMovie
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import use_cases.interfaces.IFindMovieUsecase

class FindMovieUsecase : IFindMovieUsecase, KoinComponent {
    private val movieRepository: IMovieRepository by inject()
    override suspend fun findMovie(id: Int): Movie {
        return movieRepository.getMovie(id).toMovie()
    }
}