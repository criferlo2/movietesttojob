package com.hashitoapps.data_layer.repository.orm

import androidx.room.*
import entity.orm.MovieDatabaseEntity
import entity.orm.ShopingCartDatabaseEntity

@Dao
interface MovieDAO {

    @Insert
    suspend fun insertMovie(movie: MovieDatabaseEntity): Unit

    @Insert
    suspend fun insertAllMovies(listMovies: List<MovieDatabaseEntity>)

    @Query("select * from movie where id=:id")
    suspend fun getMovie(id: Int): MovieDatabaseEntity

    @Query("select * from movie")
    suspend fun getAllMovies(): List<MovieDatabaseEntity>

    @Insert
    suspend fun insertShoppingCart(shopingCartDatabaseEntity: ShopingCartDatabaseEntity): Long

    @Query("select * from shoping_cart where movie_id=:movie_id")
    suspend fun getShoppingCartItem(movie_id: Int): ShopingCartDatabaseEntity

    @Update
    suspend fun updateQuantityShoppingCart(shopingCartDatabaseEntity: ShopingCartDatabaseEntity): Int

    @Query("delete from shoping_cart")
    suspend fun deleteAllMoviesFromShoppingCart()

    @Query("delete from movie")
    suspend fun deleteAllMovies()

    @Query("select * from shoping_cart")
    suspend fun getShopingCart(): List<ShopingCartDatabaseEntity>

}