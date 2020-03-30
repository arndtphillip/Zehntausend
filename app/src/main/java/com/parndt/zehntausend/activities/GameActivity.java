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
import com.parndt.zehntausend.model.GameState;
import com.parndt.zehntausend.model.Player;
import com.parndt.zehntausend.model.PlayerScore;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private GameState state;
    private RecyclerView.Adapter scoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        RecyclerView scoreRecyclerView = (RecyclerView) findViewById(R.id.tableScores);

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

        scoreRecyclerView.setLayoutManager(new GridLayoutManager(this, players.size()));

        scoreAdapter = new PlayerScoreAdapter(players);
        scoreRecyclerView.setAdapter(scoreAdapter);
    }

    /** adds new points to the player scores */
    public void addScore(View view) {
        EditText pointsText = (EditText) findViewById(R.id.pointsText);
        state.addScore(Integer.parseInt(pointsText.getText().toString()));

        scoreAdapter.notifyDataSetChanged();

        pointsText.getText().clear();
    }

    /** reverts the last move */
    public void undo(View view) {
        state.undo();
        scoreAdapter.notifyDataSetChanged();
    }
}
