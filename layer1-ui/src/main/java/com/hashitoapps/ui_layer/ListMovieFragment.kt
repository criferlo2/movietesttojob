package com.hashitoapps.ui_layer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hashitoapps.ui_layer.databinding.FragmentListMovieBinding
import com.hashitoapps.util_layer.util.ActionUpdateShoppingCart
import entity.Movie
import entity.ShoppingCart
import entity.UIMovie
import list_movie.ListMovieAdapter
import list_movie.ListMovieInteractor
import viewmodel.MovieViewModel


class ListMovieFragment : Fragment(), ListMovieInteractor {

    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by activityViewModels()
    private lateinit var adapterListMovie: ListMovieAdapter

    private fun fillList() {
        movieViewModel.getAllMoviesLocal()
        movieViewModel
            .movieLocalLiveData
            .observe(viewLifecycleOwner, Observer {
                findCart(it)
            })
    }

    private fun findCart(listMovie: List<Movie>) {
        movieViewModel.getCart()
        movieViewModel
            .getCartLiveData
            .observe(viewLifecycleOwner, Observer {
                prepareData(listMovie, it)
            })
    }

    private fun prepareData(listMovie: List<Movie>, shoppingCart: List<ShoppingCart>) {
        var listUIMovie = mutableListOf<UIMovie>()
        listMovie
            .forEach {
                var UIMovieObj = UIMovie(
                    id = it.id,
                    postalPath = it.postalPath,
                    originalTitle = it.originalTitle,
                    popularity = it.popularity,
                    voteAverage = it.voteAverage,
                    overview = it.overview,
                    quantity = 0
                )
                var shoppingCartObj: ShoppingCart? = null
                if (shoppingCart.isNotEmpty()) {
                    shoppingCartObj = shoppingCart.firstOrNull() { e -> e.movie_id == it.id }
                }

                shoppingCartObj?.let {
                    UIMovieObj.quantity = shoppingCartObj.quantity
                }
                listUIMovie.add(UIMovieObj)
            }
        adapterListMovie.movies = listUIMovie

        movieViewModel.currentPosition?.let {
            moveToCurrentPosition(it)
        }
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        val view = binding.root

        movieViewModel.dataLoaded.observe(viewLifecycleOwner, Observer {
            fillList()
        })

        binding.fab.setOnClickListener { goToShoppingCart() }

        prepareAdapter()
        fillList()

        return view
    }

    private fun goToShoppingCart() {
        val action = ListMovieFragmentDirections
            .actionListMovieFragmentToShoppingCartFragment()

        view?.findNavController()?.navigate(action)
    }

    private fun moveToCurrentPosition(it: Int) {
        _binding?.recyclerMovies?.scrollToPosition(it)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListMovieFragment()
    }

    override fun onClickLayout(movie: UIMovie, position: Int) {
        with(movie) {
            val action = ListMovieFragmentDirections
                .actionListMovieFragmentToDetailMovieFragment(
                    id ?: 0
                )

            movieViewModel.currentMovie = movie
            movieViewModel.currentPosition = position
            view?.findNavController()?.navigate(action)

        }
    }

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
