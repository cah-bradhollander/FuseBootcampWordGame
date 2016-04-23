package com.fuse.bootcamp.rockpaperscissorsbootcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    GameService gameService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameService = GameServiceProvider.get();
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view) {
        EditText loginEditText = (EditText) findViewById(R.id.login_username_edit_text);
        String username = loginEditText.getText().toString();

        GameSession.player = new Player(username);
        Call<Player> call = gameService.submitPlayer(GameSession.player);

        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Could not log you in. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
