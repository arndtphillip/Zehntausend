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

    private ArrayList<Player> players = new ArrayList<Player>() {{
        add(new Player(""));
        add(new Player(""));
    }};

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
        players.add(new Player(""));
        adapter.notifyDataSetChanged();
    }

    /** starts the game with the entered players */
    public void startGame(View view) {
        replaceEmptyPlayers();

        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.PLAYERS, players);
        intent.putExtras(bundle);
        startActivity(intent);

        finish();
    }

    private void replaceEmptyPlayers() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().isEmpty()) {
                String name = getString(R.string.player) + " " + (i+1);
                players.get(i).setName(name);
            }
        }
    }
}
