package viewmodel

import androidx.lifecycle.*
import com.hashitoapps.util_layer.util.ActionUpdateShoppingCart
import entity.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import use_cases.interfaces.*

class MovieViewModel : ViewModel(), KoinComponent {

    var movieLiveData = MutableLiveData<Popular>()
    var fillMoviesLiveData = MutableLiveData<Unit>()
    var movieLocalLiveData = MutableLiveData<List<Movie>>()
    var updateMovieInShoppinCartLiveData = MutableLiveData<ShoppingCart>()
    var getCartLiveData = MutableLiveData<List<ShoppingCart>>()
    var getCartWithMovieLiveData = MutableLiveData<List<ShopingMovie>>()
    var remoteFromShoppinCartLiveData = MutableLiveData<Unit>()
    var findMovieLiveData = MutableLiveData<Movie>()
    var currentMovie: UIMovie? = null
    var currentPosition: Int? = null
    var dataLoaded = MutableLiveData<Boolean>()

    private val findListMovieUseCase: IFindListMovieUseCase by inject()
    private val fillMovies: IFillMovies by inject()
    private val updateMovieInShopingCartUseCase: IUpdateMovieInShopingCartUseCase by inject()
    private val findCartUseCase: IFindCartUseCase by inject()
    private val removeShoppingCartUseCase: IRemoveShoppingCart by inject()
    private val findMovieUsecase: IFindMovieUsecase by inject()

    fun getAllMoviesRemote() {
        movieLiveData = liveData {
            emit(findListMovieUseCase.getAllMoviesRemote())
        } as MutableLiveData
    }

    fun fillMoviesLocal(listMovies: List<Movie>) {
        fillMoviesLiveData = liveData {
            emit(fillMovies.fillMovies(listMovies))
        } as MutableLiveData<Unit>
    }

    fun getAllMoviesLocal() {
        movieLocalLiveData = liveData {
            emit(findListMovieUseCase.getAllMoviesLocal())
        } as MutableLiveData<List<Movie>>
    }

    fun updateMovieInShoppingCart(movie: Movie, action: ActionUpdateShoppingCart) {
        updateMovieInShoppinCartLiveData = liveData {
            emit(updateMovieInShopingCartUseCase.updateMovieInShopingCart(movie, action))
        } as MutableLiveData<ShoppingCart>
    }

    fun getCart() {
        getCartLiveData = liveData {
            emit(findCartUseCase.getCart())
        } as MutableLiveData<List<ShoppingCart>>
    }

    fun getCartWithMovie() {
        getCartWithMovieLiveData = liveData {
            emit(findCartUseCase.getCartWithMovies())
        } as MutableLiveData<List<ShopingMovie>>
    }

    fun removeAllFromShoppingCart() {
        remoteFromShoppinCartLiveData = liveData {
            emit(removeShoppingCartUseCase.removeAllShoppingCart())
        } as MutableLiveData<Unit>
    }

    fun findMovie(id: Int) {
        findMovieLiveData = liveData {
            emit(findMovieUsecase.findMovie(id))
        } as MutableLiveData<Movie>
    }

}