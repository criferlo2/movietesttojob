package list_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hashitoapps.ui_layer.R
import entity.UIMovie

class ListMovieAdapter(
    var listener: ListMovieInteractor
) : RecyclerView.Adapter<ListMovieViewHolder>() {

    var movies = mutableListOf<UIMovie>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ListMovieViewHolder(
            view,
            listener
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}