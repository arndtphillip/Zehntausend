package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.Zehntausend;
import com.parndt.zehntausend.adapters.PlayerHeaderAdapter;
import com.parndt.zehntausend.adapters.PlayerScoreAdapter;
import com.parndt.zehntausend.model.GameState;
import com.parndt.zehntausend.model.PlayerScore;

public class GameActivity extends AppCompatActivity {

    private GameState state;
    private RecyclerView.Adapter headerAdapter;
    private RecyclerView.Adapter scoreAdapter;

    private RecyclerView scoreView;
    private EditText pointsText;

    // this is needed to be able to scroll the recycler view to the bottom
    private int scoreViewPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        state = Zehntausend.gameState;

        scoreViewPosition = state.getPlayers().size();

        for (PlayerScore player : state.getPlayers()) {
            scoreViewPosition += player.getPoints().size();
        }

        scoreView = findViewById(R.id.tableScores);
        pointsText = findViewById(R.id.pointsText);

        setHeaderAdapter();
        setScoreAdapter();

        dataChanged();

        // scroll scoreView to bottom
        scoreView.scrollToPosition(scoreViewPosition);

        // add score on enter
        pointsText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                addScore();
                return true;
            }
        });
    }

    private void setHeaderAdapter() {
        RecyclerView headerView = findViewById(R.id.scoreHeader);
        headerView.setLayoutManager(new GridLayoutManager(this, state.getPlayers().size()));
        headerAdapter = new PlayerHeaderAdapter(state.getPlayers());
        headerView.setAdapter(headerAdapter);
    }

    private void setScoreAdapter() {
        scoreView.setLayoutManager(new GridLayoutManager(this, state.getPlayers().size()));
        scoreAdapter = new PlayerScoreAdapter(state.getPlayers());
        scoreView.setAdapter(scoreAdapter);
    }

    /** adds new points to the player scores */
    public void addScore(View view) {
        addScore();
    }

    /** reverts the last move */
    public void undo(View view) {
        state.undo();
        dataChanged();
        scoreViewPosition--;
    }

    private void addScore() {
        try {
            state.addScore(Integer.parseInt(pointsText.getText().toString()));

            dataChanged();
            scoreView.scrollToPosition(scoreViewPosition);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        scoreViewPosition++;

        if (state.gameOver()) {
            Intent intent = new Intent(this, GameEndActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void dataChanged() {
        headerAdapter.notifyDataSetChanged();
        scoreAdapter.notifyDataSetChanged();

        EditText pointsText = findViewById(R.id.pointsText);
        pointsText.getText().clear();
        pointsText.setHint(state.getCurrentPlayer().getName());

        state.save(getApplicationContext());
    }
}
