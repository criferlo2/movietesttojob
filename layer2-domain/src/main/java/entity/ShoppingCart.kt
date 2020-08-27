package entity

data class ShoppingCart(
    val id: Int? = null,
    val movie_id: Int,
    var quantity: Int
)