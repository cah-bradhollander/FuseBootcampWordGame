package com.fuse.bootcamp.rockpaperscissorsbootcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {

    GameService gameService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameService = GameServiceProvider.get();
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onJoinGame(View view) {
        EditText gameIdEditText = (EditText) findViewById(R.id.start_game_id_edit_text);
        String gameId = gameIdEditText.getText().toString();
        String username = GameSession.player.getUsername();

        Call<Game> call = gameService.joinGame(gameId, username);
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                GameSession.game = response.body();
                Intent intent = new Intent(StartActivity.this, WordInputActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Toast.makeText(StartActivity.this, "Could not join a game. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCreateGame(View view) {
        Call<Game> call = gameService.createGame();
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                updateGameIdText(response.body().getId());
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Toast.makeText(StartActivity.this, "Could not create game. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateGameIdText(final int gameId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView gameIdTextView = (TextView) findViewById(R.id.start_game_id_text_view);
                gameIdTextView.setText(String.valueOf(gameId));
            }
        });
    }
}
