package use_cases.interfaces

import entity.ShopingMovie
import entity.ShoppingCart

interface IFindCartUseCase {
    suspend fun getCart(): List<ShoppingCart>
    suspend fun getCartWithMovies(): List<ShopingMovie>
}