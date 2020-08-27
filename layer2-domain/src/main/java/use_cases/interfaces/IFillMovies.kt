package use_cases.interfaces

import entity.Movie

interface IFillMovies {
    suspend fun fillMovies(listMovies: List<Movie>)
}