package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hashitoapps.data_layer.repository.orm.MovieDAO
import entity.orm.MovieDatabaseEntity
import entity.orm.ShopingCartDatabaseEntity

@Database(
    entities = [
        MovieDatabaseEntity::class,
        ShopingCartDatabaseEntity::class
    ], version = 1, exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDAO: MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie_data_base"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}