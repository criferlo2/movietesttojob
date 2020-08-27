package entity.remote

import com.google.gson.annotations.SerializedName

data class MovieRemoteEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("postal_path")
    val postalPath: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("overview")
    val overview: String
)

