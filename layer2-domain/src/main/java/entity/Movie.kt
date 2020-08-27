package entity

data class Movie(
    val id: Int,
    val postalPath: String,
    val originalTitle: String,
    val popularity: Float,
    val voteAverage: Float,
    val overview: String
)