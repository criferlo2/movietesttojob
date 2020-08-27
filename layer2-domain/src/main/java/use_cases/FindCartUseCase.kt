package use_cases

import entity.ShopingMovie
import entity.ShoppingCart
import interfaces.IMovieRepository
import mappers.toMovie
import mappers.toShopping
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import use_cases.interfaces.IFindCartUseCase

class FindCartUseCase : IFindCartUseCase, KoinComponent {
    private val movieRepository: IMovieRepository by inject()
    override suspend fun getCart(): List<ShoppingCart> {
        return movieRepository.getShopingCart().map { e -> e.toShopping() }
    }

    override suspend fun getCartWithMovies(): List<ShopingMovie> {
        var result = arrayListOf<ShopingMovie>()
        movieRepository
            .getShopingCart()
            .forEach {
                var shopingMovie = ShopingMovie(
                    shoppingCart = it.toShopping(),
                    movie = movieRepository.getMovie(it.movie_id).toMovie()
                )
                result.add(shopingMovie)
            }
        return result
    }
}