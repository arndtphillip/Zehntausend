package com.parndt.zehntausend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parndt.zehntausend.activities.GameActivity;
import com.parndt.zehntausend.activities.HistoryActivity;
import com.parndt.zehntausend.activities.NewGameActivity;
import com.parndt.zehntausend.data.NoDataException;
import com.parndt.zehntausend.data.ScoreLoader;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.GameState;

public class MainActivity extends AppCompatActivity {

    private GameState resumeState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    private void loadData() {
        try {
            resumeState = new ScoreLoader().load(getApplicationContext());

            if (resumeState.gameOver()) {
                throw new NoDataException("Game already finished! ");
            }
        } catch (NoDataException e) {
            Button resumeButton = (Button) findViewById(R.id.resumeGameButton);
            resumeButton.setVisibility(View.GONE);
        }
    }

    /** called when the user clicks the new game button */
    public void startGame(View view) {
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivity(intent);
    }

    /** called when the user clicks the resume button */
    public void resumeGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(Constants.GAME_STATE, resumeState);
        startActivity(intent);
    }

    /** called when the user clicks the history button */
    public void history(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
