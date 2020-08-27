package di

import org.koin.dsl.module.module
import use_cases.*
import use_cases.interfaces.*

val moduleUserInterface = module {
    factory<IFindListMovieUseCase> { FindListMovieUseCase() }
    factory<IUpdateMovieInShopingCartUseCase> { UpdateMovieInShopingCartUseCase() }
    factory<IAddMovieUseCase> { AddMovieUseCase() }
    factory<IFillMovies> { FillMovies() }
    factory<IRemoveShoppingCart> { RemoveShoppingCart() }
    factory<IFindCartUseCase> { FindCartUseCase() }
    factory<IFindMovieUsecase> { FindMovieUsecase() }
}
