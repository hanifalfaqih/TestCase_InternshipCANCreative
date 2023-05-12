package id.allana.gamesapp.ui.favoritegame

import id.allana.gamesapp.data.datasource.datalocal.FavoriteGamesDataSource
import id.allana.gamesapp.data.model.FavoriteGameEntity

class FavoriteGameRepository(private val favoriteGamesDataSource: FavoriteGamesDataSource): FavoriteGameContract.Repository {
    override suspend fun getFavoriteGame(): List<FavoriteGameEntity> {
        return favoriteGamesDataSource.getFavoriteGame()
    }
}