package com.fuse.bootcamp.rockpaperscissorsbootcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TurnResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_result);

        String playerWord = getPlayerWord();
        String partnerWord = getPartnerWord();

        displayPlayerWord(playerWord);
        displayPartnerWord(partnerWord);

        TextView gameStatusTextView = (TextView) findViewById(R.id.turn_result_game_status_text_view);
        if (playerWord.equals(partnerWord)) {
            gameStatusTextView.setText("You win!");
        } else {
            gameStatusTextView.setText("Try again!");
        }
    }

    public void onContinue(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    private String getPlayerWord() {
        String playerUsername = GameSession.player.getUsername();
        return GameSession.game.getMessages().get(playerUsername);
    }

    private String getPartnerWord() {
        String partnerUsername = GameSession.game.getOpponentUsername();
        return GameSession.game.getMessages().get(partnerUsername);
    }

    private void displayPlayerWord(String playerWord) {
        TextView playerWordTextView = (TextView) findViewById(R.id.turn_result_player_word_text_view);
        playerWordTextView.setText(playerWord);
    }

    private void displayPartnerWord(String partnerWord) {
        TextView partnerWordTextView = (TextView) findViewById(R.id.turn_result_partner_word_text_view);
        partnerWordTextView.setText(partnerWord);
    }
}
