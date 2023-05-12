package id.allana.gamesapp.data.network

import id.allana.gamesapp.data.model.ResponseDetailGame
import id.allana.gamesapp.data.model.ResponseSearchGame
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Get Search Games
    @GET("/api/games")
    suspend fun getSearchGames(
        @Query("search") query: String,
        @Query("key") apiKey: String = "50091600f95343b2bb3e0bb03a7f27a5"
    ): ResponseSearchGame

    @GET("api/games/{id}")
    suspend fun getDetailGame(
        @Path("id") id: Int,
        @Query("key") apiKey: String = "50091600f95343b2bb3e0bb03a7f27a5"
    ): ResponseDetailGame

    @GET("api/games")
    suspend fun getListGames(
        @Query("key") apiKey: String = "50091600f95343b2bb3e0bb03a7f27a5"
    ): ResponseSearchGame
}