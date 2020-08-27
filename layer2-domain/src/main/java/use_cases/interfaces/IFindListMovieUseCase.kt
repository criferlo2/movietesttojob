package use_cases.interfaces

import entity.Movie
import entity.Popular

interface IFindListMovieUseCase {

    suspend fun getAllMoviesRemote(): Popular
    suspend fun getAllMoviesLocal(): List<Movie>
}