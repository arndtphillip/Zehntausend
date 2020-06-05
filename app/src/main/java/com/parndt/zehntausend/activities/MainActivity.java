package com.parndt.zehntausend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.Zehntausend;
import com.parndt.zehntausend.data.ScoreLoader;
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

        // we need to reload the data every time the user comes back to the main activity
        loadData();
    }

    /**
     * loads the data of the most recent unfinished game into memory
     * hides the resume button if there is no unfinished game
     * see {@link ScoreLoader} for more information on loading the data
     */
    private void loadData() {
        resumeState = Zehntausend.gameState;

        if (!resumeState.isStarted()) {
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
        startActivity(intent);
    }

    /** called when the user clicks the history button */
    public void history(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
