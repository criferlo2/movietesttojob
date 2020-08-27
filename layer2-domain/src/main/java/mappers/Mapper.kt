package mappers

import entity.Movie
import entity.Popular
import entity.ShoppingCart
import entity.orm.MovieDatabaseEntity
import entity.orm.ShopingCartDatabaseEntity
import entity.remote.MovieRemoteEntity
import entity.remote.PopularRemoteEntity


fun PopularRemoteEntity.toPopular() = Popular(
    page = page ?: -1,
    total_results = total_results ?: -1,
    total_pages = total_pages ?: -1,
    results = results.map { e -> e.toMovie() }
)

fun MovieRemoteEntity.toMovie() = Movie(
    id = id,
    postalPath = postalPath.orEmpty(),
    originalTitle = originalTitle,
    popularity = popularity,
    voteAverage = voteAverage,
    overview = overview
)

fun ShoppingCart.toShoppinCartDatabaseEntity() = ShopingCartDatabaseEntity(
    id = id ?: 0,
    movie_id = movie_id,
    quantity = quantity
)

fun Movie.toMovieDatabaseEntity() = MovieDatabaseEntity(
    id = id,
    postalPath = postalPath,
    originalTitle = originalTitle,
    popularity = popularity,
    voteAverage = voteAverage,
    overview = overview
)

fun MovieDatabaseEntity.toMovie() = Movie(
    id = id,
    postalPath = postalPath,
    originalTitle = originalTitle,
    popularity = popularity,
    voteAverage = voteAverage,
    overview = overview
)

fun ShopingCartDatabaseEntity.toShopping() = ShoppingCart(
    id = id,
    movie_id = movie_id,
    quantity = quantity
)
