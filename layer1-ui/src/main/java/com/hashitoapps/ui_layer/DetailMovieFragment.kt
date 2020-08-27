package com.hashitoapps.ui_layer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.hashitoapps.ui_layer.databinding.FragmentDetailMovieBinding
import com.hashitoapps.util_layer.util.ActionUpdateShoppingCart
import entity.*
import viewmodel.MovieViewModel


class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        val view = binding.root

        movieViewModel.currentMovie?.let {
            binding.movie = it
        }

        binding.addTCart.setOnClickListener { addMovieToCart() }
        binding.removeFromCart.setOnClickListener { removeMovieFromCart() }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailMovieFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            var movieid = it.getString("movie_id")
            movieid?.let {
                deepLinkLoadCart(movieid)
            }
        }
    }

    private fun deepLinkLoadCart(movieid: String) {
        movieViewModel.getCartWithMovie()
        movieViewModel.getCartWithMovieLiveData
            .observe(viewLifecycleOwner, Observer {
                deepLinkLoadMovie(it, movieid)
            })

    }

    private fun deepLinkLoadMovie(listCart: List<ShopingMovie>?, movieid: String) {
        movieViewModel.findMovie(movieid.toInt())
        movieViewModel.findMovieLiveData
            .observe(viewLifecycleOwner, Observer {
                deepLinkLoadData(listCart, it)
            })
    }

    private fun deepLinkLoadData(listCart: List<ShopingMovie>?, movie: Movie?) {
        var shopingMovie = listCart?.find { it.movie.id == movie?.id?.toInt() }
        var quantity: Int = 0
        shopingMovie?.let { quantity = it.shoppingCart.quantity }

        with(movie) {
            movieViewModel.currentMovie = entity.UIMovie(
                id = this?.id,
                postalPath = this?.postalPath,
                originalTitle = this?.originalTitle,
                popularity = this?.popularity,
                voteAverage = this?.voteAverage,
                overview = this?.overview,
                quantity = quantity
            )
            binding.movie = movieViewModel.currentMovie
        }
    }

    private fun removeMovieFromCart() {
        movieViewModel.currentMovie?.let { it ->
            movieViewModel.updateMovieInShoppingCart(it.toMovie(), ActionUpdateShoppingCart.REMOVE)
            movieViewModel.updateMovieInShoppinCartLiveData
                .observe(viewLifecycleOwner, Observer {
                    Log.d("log", "se actualiza remove movie" + it.quantity)
                    updateDataView(it)

                })
        }
    }

    private fun updateDataView(it: ShoppingCart?) {
        it?.let {
            binding.quantity.text = it.quantity.toString()
        }
    }

    private fun addMovieToCart() {
        movieViewModel.currentMovie?.let { it ->
            movieViewModel.updateMovieInShoppingCart(it.toMovie(), ActionUpdateShoppingCart.ADD)
            movieViewModel.updateMovieInShoppinCartLiveData
                .observe(viewLifecycleOwner, Observer {
                    Log.d("log", "se actualiza add movie" + it.quantity)
                    updateDataView(it)
                })
        }
    }
}
