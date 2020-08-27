package use_cases.interfaces

import entity.Movie


interface IFindMovieUsecase {
    suspend fun findMovie(id: Int): Movie
}