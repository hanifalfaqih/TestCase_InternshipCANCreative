package id.allana.gamesapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteGame")
data class FavoriteGameEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "rating")
    var rating: String? = null,

    @ColumnInfo(name = "release")
    var released: String? = null,

    @ColumnInfo(name = "backgroundImage")
    var backgroundImage: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,
    )