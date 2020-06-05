package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.Zehntausend;
import com.parndt.zehntausend.adapters.PlayerHeaderAdapter;
import com.parndt.zehntausend.adapters.PlayerScoreAdapter;
import com.parndt.zehntausend.model.GameState;

public class GameActivity extends AppCompatActivity {

    private GameState state;
    private RecyclerView.Adapter headerAdapter;
    private RecyclerView.Adapter scoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        state = Zehntausend.gameState;

        setHeaderAdapter();
        setScoreAdapter();

        dataChanged();
    }

    private void setHeaderAdapter() {
        RecyclerView headerView = findViewById(R.id.scoreHeader);
        headerView.setLayoutManager(new GridLayoutManager(this, state.getPlayers().size()));
        headerAdapter = new PlayerHeaderAdapter(state.getPlayers());
        headerView.setAdapter(headerAdapter);
    }

    private void setScoreAdapter() {
        RecyclerView scoreView = findViewById(R.id.tableScores);
        scoreView.setLayoutManager(new GridLayoutManager(this, state.getPlayers().size()));
        scoreAdapter = new PlayerScoreAdapter(state.getPlayers());
        scoreView.setAdapter(scoreAdapter);
    }

    /** adds new points to the player scores */
    public void addScore(View view) {
        EditText pointsText = findViewById(R.id.pointsText);

        try {
            state.addScore(Integer.parseInt(pointsText.getText().toString()));

            dataChanged();

            // scroll to bottom
            /*final ScrollView scroll = findViewById(R.id.scoreScrollView);
            scroll.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int height = 0;
                    for (int i = 0; i < scroll.getChildCount(); i++) {
                        height += scroll.getChildAt(i).getHeight();
                    }
                    scroll.scrollTo(0, height);
                }
            }, 100);*/
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (state.gameOver()) {
            Intent intent = new Intent(this, GameEndActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /** reverts the last move */
    public void undo(View view) {
        state.undo();
        dataChanged();
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
