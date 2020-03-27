package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.Player;

import java.util.ArrayList;
import java.util.List;

public class NewGameActivity extends AppCompatActivity {

    private List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
    }

    /** adds a new player */
    public void addPlayer(View view) {
        EditText playerText = (EditText) findViewById(R.id.playerName);
        String playerName = playerText.getText().toString();

        Player player = new Player(playerName);
        players.add(player);

        playerText.getText().clear();
    }
}
