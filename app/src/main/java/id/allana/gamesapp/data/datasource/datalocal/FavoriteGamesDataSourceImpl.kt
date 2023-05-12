package id.allana.gamesapp.data.datasource.datalocal

import id.allana.gamesapp.data.local.FavoriteGameDao
import id.allana.gamesapp.data.model.FavoriteGameEntity

class FavoriteGamesDataSourceImpl(private val favoriteGameDao: FavoriteGameDao): FavoriteGamesDataSource {
    override suspend fun getFavoriteGame(): List<FavoriteGameEntity> {
        return favoriteGameDao.getFavoriteGame()
    }

    override suspend fun getFavoriteGameById(id: Int): Boolean {
        return favoriteGameDao.getFavoriteGameById(id)
    }

    override suspend fun deleteGame(id: Int): Int {
        return favoriteGameDao.deleteFavoriteGame(id)
    }

    override suspend fun insertGame(game: FavoriteGameEntity): Long {
        return favoriteGameDao.insertFavoriteGame(game)
    }
}