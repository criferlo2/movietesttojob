package di

import com.hashitoapps.data_layer.repository.MovieRepository
import interfaces.IMovieRepository
import org.koin.dsl.module.module

val moduleDomain = module {
    factory<IMovieRepository> {
        MovieRepository(
            get()
        )
    }
}