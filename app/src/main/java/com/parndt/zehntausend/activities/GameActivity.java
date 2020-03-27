package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.adapters.PlayerScoreAdapter;
import com.parndt.zehntausend.adapters.PlayerScoreFooterAdapter;
import com.parndt.zehntausend.adapters.PlayerScoreHeaderAdapter;
import com.parndt.zehntausend.model.GameState;
import com.parndt.zehntausend.model.Player;
import com.parndt.zehntausend.model.PlayerScore;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private GameState state;
    private RecyclerView.Adapter scoreAdapter;
    private RecyclerView.Adapter footerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        RecyclerView headerRecyclerView = (RecyclerView) findViewById(R.id.scoreHeader);
        RecyclerView scoreRecyclerView = (RecyclerView) findViewById(R.id.tableScores);
        RecyclerView footerRecyclerView = (RecyclerView) findViewById(R.id.scoreFooter);

        Bundle extras = getIntent().getExtras();
        ArrayList<Parcelable> parcelables = extras.getParcelableArrayList(NewGameActivity.EXTRA_PLAYERS);

        List<PlayerScore> players = new ArrayList<>();
        if (parcelables != null) {
            for (Parcelable p : parcelables) {
                if (p instanceof Player) {
                    players.add(new PlayerScore((Player) p));
                }
            }
        }

        state = new GameState(players);

        headerRecyclerView.setLayoutManager(new GridLayoutManager(this, players.size()));
        scoreRecyclerView.setLayoutManager(new GridLayoutManager(this, players.size()));
        footerRecyclerView.setLayoutManager(new GridLayoutManager(this, players.size()));

        PlayerScoreHeaderAdapter headerAdapter = new PlayerScoreHeaderAdapter(players);
        headerRecyclerView.setAdapter(headerAdapter);

        scoreAdapter = new PlayerScoreAdapter(players);
        scoreRecyclerView.setAdapter(scoreAdapter);

        footerAdapter = new PlayerScoreFooterAdapter(players);
        footerRecyclerView.setAdapter(footerAdapter);
    }

    /** adds new points to the player scores */
    public void addScore(View view) {
        EditText pointsText = (EditText) findViewById(R.id.pointsText);
        state.addScore(Integer.parseInt(pointsText.getText().toString()));

        scoreAdapter.notifyDataSetChanged();
        footerAdapter.notifyDataSetChanged();

        pointsText.getText().clear();
    }
}
