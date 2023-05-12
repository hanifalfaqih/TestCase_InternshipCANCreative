package id.allana.gamesapp.data.datasource.datalocal

import id.allana.gamesapp.data.model.FavoriteGameEntity

interface FavoriteGamesDataSource {

    suspend fun getFavoriteGame(): List<FavoriteGameEntity>
    suspend fun getFavoriteGameById(id: Int): Boolean
    suspend fun deleteGame(id: Int): Int
    suspend fun insertGame(game: FavoriteGameEntity): Long

}