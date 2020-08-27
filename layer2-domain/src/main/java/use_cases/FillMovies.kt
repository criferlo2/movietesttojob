package use_cases

import entity.Movie
import interfaces.IMovieRepository
import mappers.toMovieDatabaseEntity
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import use_cases.interfaces.IFillMovies

class FillMovies : IFillMovies,KoinComponent{
    private val movieRepository: IMovieRepository by inject()
    override suspend fun fillMovies(listMovies: List<Movie>) {
        movieRepository.deleteAllMoviesFromShoppingCart()
        movieRepository.deleteAllMovies()
        movieRepository.insertAllMovies(listMovies.map { e->e.toMovieDatabaseEntity() })
    }
}