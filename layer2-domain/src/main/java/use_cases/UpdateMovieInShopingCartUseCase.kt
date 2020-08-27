package use_cases

import com.hashitoapps.util_layer.util.ActionUpdateShoppingCart
import entity.Movie
import entity.ShoppingCart
import interfaces.IMovieRepository
import mappers.toShoppinCartDatabaseEntity
import mappers.toShopping
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import use_cases.interfaces.IUpdateMovieInShopingCartUseCase

class UpdateMovieInShopingCartUseCase : IUpdateMovieInShopingCartUseCase, KoinComponent {
    private val movieRepository: IMovieRepository by inject()

    override suspend fun updateMovieInShopingCart(
        movie: Movie,
        action: ActionUpdateShoppingCart
    ): ShoppingCart {
        var shopingCart: ShoppingCart? =
            movieRepository.getShoppingCartItem(movie.id)?.toShopping();

        if (shopingCart != null) {
            when (action) {
                ActionUpdateShoppingCart.ADD -> {
                    shopingCart.quantity = shopingCart.quantity + 1
                }
                ActionUpdateShoppingCart.REMOVE -> {
                    shopingCart.quantity = shopingCart.quantity - 1
                }
            }
            movieRepository.updateQuantityShoppingCart(shopingCart.toShoppinCartDatabaseEntity())
        } else {
            var shoppingCartNew = ShoppingCart(
                movie_id = movie.id,
                //movie = movie,
                quantity = 1

            )
            movieRepository.insertShoppingCart(shoppingCartNew?.toShoppinCartDatabaseEntity())
            shopingCart = movieRepository.getShoppingCartItem(movie.id).toShopping()
        }
        return shopingCart
    }
}