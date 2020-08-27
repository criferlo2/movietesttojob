package com.hashitoapps.data_layer.repository

import android.content.Context
import com.hashitoapps.data_layer.repository.remote.HttpClientFactory
import com.hashitoapps.util_layer.Constants
import database.MovieDatabase
import entity.orm.MovieDatabaseEntity
import entity.orm.ShopingCartDatabaseEntity
import interfaces.IMovieRepository
import entity.remote.MovieRemoteEntity
import entity.remote.PopularRemoteEntity
import io.ktor.client.request.get

class MovieRepository(
    context: Context
) : IMovieRepository {

    private val movieDAO = MovieDatabase.getInstance(context.applicationContext).movieDAO

    override suspend fun getPopularRemote(): PopularRemoteEntity {
        val response: PopularRemoteEntity =
            HttpClientFactory.build()
                .get(Constants.buildGetPopularUrl())
        return response
    }

    override suspend fun getMoviesRemote(id: Int): MovieRemoteEntity {
        val response: MovieRemoteEntity =
            HttpClientFactory.build()
                .get(Constants.buildGetMovieUrl(id))
        return response
    }

    override suspend fun insertMovie(movie: MovieDatabaseEntity): Unit {
        return movieDAO.insertMovie(movie)
    }

    override suspend fun insertAllMovies(listMovies: List<MovieDatabaseEntity>) {
        movieDAO.insertAllMovies(listMovies)
    }

    override suspend fun getMovie(id: Int): MovieDatabaseEntity {
        return movieDAO.getMovie(id)
    }

    override suspend fun getAllMovies(): List<MovieDatabaseEntity> {
        return movieDAO.getAllMovies()
    }

    override suspend fun insertShoppingCart(shopingCartDatabaseEntity: ShopingCartDatabaseEntity): Long {
        return movieDAO.insertShoppingCart(shopingCartDatabaseEntity)
    }

    override suspend fun getShoppingCartItem(movie_id: Int): ShopingCartDatabaseEntity {
        return movieDAO.getShoppingCartItem(movie_id)
    }

    override suspend fun updateQuantityShoppingCart(shopingCartDatabaseEntity: ShopingCartDatabaseEntity): Int {
        return movieDAO.updateQuantityShoppingCart(shopingCartDatabaseEntity)
    }

    override suspend fun deleteAllMoviesFromShoppingCart() {
        movieDAO.deleteAllMoviesFromShoppingCart()
    }

    override suspend fun deleteAllMovies() {
        movieDAO.deleteAllMovies()
    }

    override suspend fun getShopingCart(): List<ShopingCartDatabaseEntity> {
        return movieDAO.getShopingCart()
    }

}