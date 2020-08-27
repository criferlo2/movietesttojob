package interfaces

import entity.orm.MovieDatabaseEntity
import entity.orm.ShopingCartDatabaseEntity
import entity.remote.MovieRemoteEntity
import entity.remote.PopularRemoteEntity

interface IMovieRepository {
    //from remote
    suspend fun getPopularRemote():PopularRemoteEntity
    suspend fun getMoviesRemote(id:Int): MovieRemoteEntity
    //from local
    suspend fun insertMovie(movie : MovieDatabaseEntity):Unit
    suspend fun insertAllMovies(listMovies: List<MovieDatabaseEntity>)
    suspend fun getMovie(id: Int): MovieDatabaseEntity
    suspend fun getAllMovies() : List<MovieDatabaseEntity>
    suspend fun insertShoppingCart(shopingCartDatabaseEntity: ShopingCartDatabaseEntity):Long
    suspend fun getShoppingCartItem(movie_id: Int): ShopingCartDatabaseEntity
    suspend fun updateQuantityShoppingCart(shopingCartDatabaseEntity: ShopingCartDatabaseEntity) :Int
    suspend fun deleteAllMoviesFromShoppingCart()
    suspend fun deleteAllMovies()
    suspend fun getShopingCart():List<ShopingCartDatabaseEntity>
}