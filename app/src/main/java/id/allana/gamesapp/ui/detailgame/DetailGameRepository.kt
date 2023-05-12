package id.allana.gamesapp.ui.detailgame

import id.allana.gamesapp.data.datasource.datalocal.FavoriteGamesDataSource
import id.allana.gamesapp.data.datasource.datanetwork.GamesDataSource
import id.allana.gamesapp.data.model.FavoriteGameEntity
import id.allana.gamesapp.data.model.ResponseDetailGame

class DetailGameRepository(
    private val gamesDataSource: GamesDataSource,
    private val favoriteGamesDataSource: FavoriteGamesDataSource
): DetailGameContract.Repository {

    override suspend fun getDetailGame(id: Int): ResponseDetailGame {
        return gamesDataSource.getDetailGame(id)
    }

    override suspend fun getFavoriteGameByIdLiveData(id: Int): Boolean {
        return favoriteGamesDataSource.getFavoriteGameById(id)
    }

    override suspend fun deleteGame(id: Int): Int {
        return favoriteGamesDataSource.deleteGame(id)
    }

    override suspend fun insertGame(game: FavoriteGameEntity): Long {
        return favoriteGamesDataSource.insertGame(game)
    }
}