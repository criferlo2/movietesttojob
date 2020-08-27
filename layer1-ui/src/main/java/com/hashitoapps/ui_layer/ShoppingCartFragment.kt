package com.hashitoapps.ui_layer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hashitoapps.ui_layer.databinding.FragmentShoppingCartBinding
import com.hashitoapps.util_layer.util.ActionUpdateShoppingCart
import entity.Movie
import entity.ShopingMovie
import entity.ShoppingCart
import entity.UIMovie
import list_movie.ListMovieAdapter
import list_movie.ListMovieInteractor
import viewmodel.MovieViewModel

class ShoppingCartFragment : Fragment(), ListMovieInteractor {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by activityViewModels()
    private lateinit var adapterListMovie: ListMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.clearShoppingCart.setOnClickListener { clearAllShoppingCart() }

        prepareAdapter()
        fillList()

        return view
    }

    private fun clearAllShoppingCart() {
        movieViewModel
            .removeAllFromShoppingCart()

        movieViewModel.remoteFromShoppinCartLiveData
            .observe(viewLifecycleOwner, Observer {
                fillList()
            })
    }

    private fun prepareAdapter() {
        adapterListMovie = ListMovieAdapter(this)
        var viewManager = LinearLayoutManager(context)
        with(binding.recyclerMovies) {
            adapter = adapterListMovie
            setHasFixedSize(true)
            layoutManager = viewManager
        }
    }

    private fun fillList() {
        movieViewModel.getCartWithMovie()
        movieViewModel
            .getCartWithMovieLiveData
            .observe(viewLifecycleOwner, Observer {
                //adapterListMovie.movies = it as MutableList<Movie>
                prepareData(it)
            })
    }

    private fun prepareData(listMovie: List<ShopingMovie>?) {
        var listUIMovie = mutableListOf<UIMovie>()
        listMovie?.forEach {
            var UIMovieObj = UIMovie(
                id = it.movie.id,
                postalPath = it.movie.postalPath,
                originalTitle = it.movie.originalTitle,
                popularity = it.movie.popularity,
                voteAverage = it.movie.voteAverage,
                overview = it.movie.overview,
                quantity = it.shoppingCart.quantity
            )
            listUIMovie.add(UIMovieObj)
        }
        adapterListMovie.movies = listUIMovie

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoppingCartFragment()
    }

    override fun onClickLayout(movie: UIMovie, position: Int) {}

    override fun removeMovieFromCart(movie: Movie) {
        movieViewModel.updateMovieInShoppingCart(movie, ActionUpdateShoppingCart.REMOVE)
        movieViewModel.updateMovieInShoppinCartLiveData
            .observe(this, Observer {
                Log.d("log", "se actualiza remove movie" + it.quantity)
                updateDataView(it)

            })
    }

    private fun updateDataView(it: ShoppingCart?) {
        adapterListMovie.movies.find { e -> e.id == it?.movie_id }?.quantity = it?.quantity
        adapterListMovie.notifyDataSetChanged()
    }

    override fun addMovieToCart(movie: Movie) {
        movieViewModel.updateMovieInShoppingCart(movie, ActionUpdateShoppingCart.ADD)
        movieViewModel.updateMovieInShoppinCartLiveData
            .observe(this, Observer {
                Log.d("log", "se actualiza add movie" + it.quantity)
                updateDataView(it)
            })
    }
}
