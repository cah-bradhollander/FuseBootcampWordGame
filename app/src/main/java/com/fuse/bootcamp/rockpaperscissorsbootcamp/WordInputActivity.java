package com.fuse.bootcamp.rockpaperscissorsbootcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordInputActivity extends AppCompatActivity {

    GameService gameService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameService = GameServiceProvider.get();
        setContentView(R.layout.activity_word_input);
    }

    public void onSubmit(View view) {
        EditText wordInputEditText = (EditText) findViewById(R.id.word_input_edit_text);
        String word = wordInputEditText.getText().toString();
        String gameId = String.valueOf(GameSession.game.getId());
        String username = GameSession.player.getUsername();

        Call<String> call = gameService.sendMessage(gameId, username, word);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Intent intent = new Intent(WordInputActivity.this, LoadingActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(WordInputActivity.this, "Could not send message. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
