package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.adapters.PlayerNameAdapter;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.Player;

import java.util.ArrayList;

public class NewGameActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;

    private ArrayList<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        RecyclerView recyclerView = findViewById(R.id.playerListView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new PlayerNameAdapter(players);
        recyclerView.setAdapter(adapter);
    }

    /** adds a new player */
    public void addPlayer(View view) {
        EditText playerText = findViewById(R.id.playerName);
        String playerName = playerText.getText().toString();

        if (!playerName.equals("")) {
            players.add(new Player(playerName));
            adapter.notifyDataSetChanged();
            if (players.size() > 1) {
                findViewById(R.id.startGameButton).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.startGameButton).setVisibility(View.GONE);
            }

            playerText.getText().clear();
        }
    }

    /** starts the game with the entered players */
    public void startGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.PLAYERS, players);
        intent.putExtras(bundle);
        startActivity(intent);

        finish();
    }
}
