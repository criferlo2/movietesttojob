package use_cases.interfaces

import entity.Movie

interface IAddMovieUseCase {
    suspend fun addMovie(movie: Movie): Unit
}