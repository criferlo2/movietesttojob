package list_movie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import entity.Movie
import entity.UIMovie
import entity.toMovie
import kotlinx.android.synthetic.main.item_movie.view.*

class ListMovieViewHolder(itemView: View, var listener: ListMovieInteractor) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(movie: UIMovie) {
        with(itemView) {
            original_title.text = movie.originalTitle
            popularity.text = movie.popularity.toString()
            vote_average.text = movie.voteAverage.toString()
            product_quantity.text = movie.quantity.toString()


            container_item_movie.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    doClick(movie, layoutPosition)

                }
            })
            removeFromCart.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    removeFromCartF(movie.toMovie())
                }
            })
            addTCart.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    addToCart(movie.toMovie())
                }
            })
        }
    }

    private fun addToCart(movie: Movie) {
        listener.addMovieToCart(movie)
    }

    private fun removeFromCartF(movie: Movie) {
        listener.removeMovieFromCart(movie)
    }

    private fun doClick(movie: UIMovie, position: Int) {
        listener.onClickLayout(movie, position)
    }
}