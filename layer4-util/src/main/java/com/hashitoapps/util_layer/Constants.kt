package com.hashitoapps.util_layer

object Constants {
    private const val api_key = "?api_key=a4b654eef45d92cb6134236d2ac35d77"
    private const val url_api = "https://api.themoviedb.org/3"
    private const val method_get_popular = "/movie/popular"
    private const val method_get_movie = "/movie"

    fun buildGetPopularUrl(): String {
        return "$url_api$method_get_popular$api_key"
    }

    fun buildGetMovieUrl(id: Int): String {
        return "$url_api$method_get_movie/$id$api_key"
    }

}