package com.fuse.bootcamp.rockpaperscissorsbootcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {

    GameService gameService;
    String partnerResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        gameService = GameServiceProvider.get();
        pollServerUntilBothPlayersRespond();
    }

    private void pollServerUntilBothPlayersRespond() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (partnerResponse == null) {
                    getGame();
                } else {
                    Intent intent = new Intent(LoadingActivity.this, TurnResultActivity.class);
                    startActivity(intent);
                    cancel();
                }
            }
        }, 0, 1000);
    }

    private void getGame() {
        String gameId = String.valueOf(GameSession.game.getId());
        Call<Game> call = gameService.getGame(gameId);
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                GameSession.game = response.body();
                if (GameSession.game.getMessages().size() > 1) {
                    String partner = GameSession.game.getOpponentUsername();
                    partnerResponse = GameSession.game.getMessages().get(partner);
                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {

            }
        });
    }
}
