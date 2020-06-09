package com.parndt.zehntausend.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.Zehntausend;
import com.parndt.zehntausend.model.GameState;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setVisibilities();
    }

    private void setVisibilities() {
        GameState resumeState = Zehntausend.gameState;

        Button resumeButton = (Button) findViewById(R.id.resumeGameButton);
        if (!resumeState.isStarted() || resumeState.gameOver()) {
            resumeButton.setVisibility(View.GONE);
        } else {
            resumeButton.setVisibility(View.VISIBLE);
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
