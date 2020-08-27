package use_cases.interfaces

import com.hashitoapps.util_layer.util.ActionUpdateShoppingCart
import entity.Movie
import entity.ShoppingCart

interface IUpdateMovieInShopingCartUseCase {
    suspend fun updateMovieInShopingCart(
        movie: Movie,
        action: ActionUpdateShoppingCart
    ): ShoppingCart
}