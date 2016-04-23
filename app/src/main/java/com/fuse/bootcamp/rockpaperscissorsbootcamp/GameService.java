package com.fuse.bootcamp.rockpaperscissorsbootcamp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GameService {

    @POST("/player")
    Call<Player> submitPlayer(@Body Player player);

    @POST("/game")
    Call<Game> createGame();

    @POST("/game/{gameId}/player/{username}")
    Call<Game> joinGame(@Path("gameId") String gameId, @Path("username") String username);

    @GET("/game/{gameId}")
    Call<Game> getGame(@Path("gameId") String gameId);

    @POST("/game/{gameId}/player/{username}/message")
    Call<String> sendMessage(@Path("gameId") String gameId, @Path("username") String username, @Body String message);
}