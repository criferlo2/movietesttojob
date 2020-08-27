package list_movie

import entity.Movie
import entity.UIMovie

interface ListMovieInteractor {
    fun onClickLayout(movie: UIMovie, position: Int)
    fun removeMovieFromCart(movie: Movie)
    fun addMovieToCart(movie: Movie)
}