package entity

data class UIMovie(
    var id: Int?,
    var postalPath: String?,
    var originalTitle: String?,
    var popularity: Float?,
    var voteAverage: Float?,
    var overview: String?,
    var quantity: Int?
)

fun UIMovie.toMovie() = Movie(
    id = id ?: 0,
    postalPath = postalPath.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    popularity = popularity ?: 0f,
    voteAverage = voteAverage ?: 0f,
    overview = overview.orEmpty()
)