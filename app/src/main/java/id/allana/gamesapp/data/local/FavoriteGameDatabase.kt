package id.allana.gamesapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.allana.gamesapp.data.model.FavoriteGameEntity

@Database(entities = [FavoriteGameEntity::class], version = 1)
abstract class FavoriteGameDatabase: RoomDatabase() {

    abstract fun favoriteGameDao(): FavoriteGameDao

    companion object {
        private const val DB_NAME = "favorite_game_db"

        @Volatile
        private var INSTANCE: FavoriteGameDatabase? = null
        fun getInstance(context: Context): FavoriteGameDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteGameDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}