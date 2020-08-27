package com.hashitoapps.movies_test_to_job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import database.MovieDatabase
import entity.Movie
import viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareDatabase()
        getMoviesLocal()
    }

    private fun prepareDatabase() {
        MovieDatabase.getInstance(application).movieDAO
    }

    private fun getMoviesLocal() {
        movieViewModel.getAllMoviesLocal()
        movieViewModel.movieLocalLiveData
            .observe(this, Observer {
                it?.let {
                    if (it.isEmpty()) {
                        getMovies()
                    }
                }
            })
    }

    private fun getMovies() {
        movieViewModel.getAllMoviesRemote()
        movieViewModel.movieLiveData
            .observe(this, Observer {
                var listMovies = it.results
                fillMovies(listMovies)
            })
    }

    private fun fillMovies(listMovies: List<Movie>) {
        movieViewModel.fillMoviesLocal(listMovies)
        movieViewModel.fillMoviesLiveData
            .observe(this, Observer {
                Log.d("log", "Movies were inserted.")
                movieViewModel.dataLoaded.postValue(true)
            })
    }
}
