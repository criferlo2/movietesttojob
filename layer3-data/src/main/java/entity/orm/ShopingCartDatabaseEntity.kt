package entity.orm

import androidx.room.*

@Entity(
    tableName = "shoping_cart",
    foreignKeys = [
        ForeignKey(
            entity = MovieDatabaseEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("movie_id"),
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class ShopingCartDatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "movie_id")
    val movie_id: Int,
    @ColumnInfo(name = "quantity")
    val quantity: Int
)