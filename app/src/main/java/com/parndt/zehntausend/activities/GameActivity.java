package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.parndt.zehntausend.MainActivity;
import com.parndt.zehntausend.R;
import com.parndt.zehntausend.adapters.PlayerHeaderAdapter;
import com.parndt.zehntausend.adapters.PlayerScoreAdapter;
import com.parndt.zehntausend.model.Constants;
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

        Bundle extras = getIntent().getExtras();
        ArrayList<Parcelable> parcelables = extras.getParcelableArrayList(Constants.PLAYERS);

        if (parcelables != null) {
            newGame(parcelables);
        } else {
            state = (GameState) getIntent().getSerializableExtra(Constants.GAME_STATE);
        }

        setHeaderAdapter();
        setScoreAdapter();

        dataChanged();
    }

    private void setHeaderAdapter() {
        RecyclerView headerView = (RecyclerView) findViewById(R.id.scoreHeader);
        headerView.setLayoutManager(new GridLayoutManager(this, state.getPlayers().size()));
        RecyclerView.Adapter headerAdapter = new PlayerHeaderAdapter(state.getPlayers());
        headerView.setAdapter(headerAdapter);
    }

    private void setScoreAdapter() {
        RecyclerView scoreView = (RecyclerView) findViewById(R.id.tableScores);
        scoreView.setLayoutManager(new GridLayoutManager(this, state.getPlayers().size()));
        scoreAdapter = new PlayerScoreAdapter(state.getPlayers());
        scoreView.setAdapter(scoreAdapter);
    }

    /** adds new points to the player scores */
    public void addScore(View view) {
        EditText pointsText = (EditText) findViewById(R.id.pointsText);

        try {
            state.addScore(Integer.parseInt(pointsText.getText().toString()));

            dataChanged();

            // scroll to bottom
            //ScrollView scroll = (ScrollView) view.findViewById(R.id.scoreScrollView);
            //scroll.fullScroll(ScrollView.FOCUS_DOWN);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (state.gameOver()) {
            Intent intent = new Intent(this, ChartActivity.class);
            intent.putExtra(Constants.GAME_STATE, state);
            startActivity(intent);
        }
    }

    /** reverts the last move */
    public void undo(View view) {
        state.undo();
        dataChanged();
    }

    private void newGame(List<Parcelable> parcelables) {
        List<PlayerScore> players = new ArrayList<>();
        if (parcelables != null) {
            for (Parcelable p : parcelables) {
                if (p instanceof Player) {
                    players.add(new PlayerScore((Player) p));
                }
            }
        }

        state = new GameState(players);
    }

    private void dataChanged() {
        // only score adapter needs to update
        scoreAdapter.notifyDataSetChanged();

        EditText pointsText = (EditText) findViewById(R.id.pointsText);
        pointsText.getText().clear();
        pointsText.setHint(state.getCurrentPlayer().getName());

        state.save(getApplicationContext());
    }

    // go back to main activity when the back button is pressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
