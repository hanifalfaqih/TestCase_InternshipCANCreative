package id.allana.gamesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.allana.gamesapp.data.model.FavoriteGameEntity

@Dao
interface FavoriteGameDao {

    @Query("SELECT * FROM FavoriteGame")
    suspend fun getFavoriteGame(): List<FavoriteGameEntity>

    @Query("SELECT EXISTS(SELECT * FROM FavoriteGame WHERE id = :id)")
    suspend fun getFavoriteGameById(id: Int): Boolean

    @Query("DELETE FROM FavoriteGame WHERE id = :id")
    suspend fun deleteFavoriteGame(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(game: FavoriteGameEntity): Long
}