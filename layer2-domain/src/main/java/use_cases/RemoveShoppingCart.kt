package use_cases

import interfaces.IMovieRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import use_cases.interfaces.IRemoveShoppingCart

class RemoveShoppingCart : IRemoveShoppingCart, KoinComponent {
    private val movieRepository: IMovieRepository by inject()

    override suspend fun removeAllShoppingCart() {
        movieRepository.deleteAllMoviesFromShoppingCart()
    }
}