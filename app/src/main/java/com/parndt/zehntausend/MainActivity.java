package com.parndt.zehntausend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parndt.zehntausend.activities.GameActivity;
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

        try {
            resumeState = new ScoreLoader().load(getApplicationContext());
        } catch (NoDataException e) {
            Button resumeButton = (Button) findViewById(R.id.resumeButton);
            resumeButton.setVisibility(View.INVISIBLE);
        }

    }

    /** called when the user clicks start new game button */
    public void startGame(View view) {
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivity(intent);
    }

    public void resumeGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(Constants.GAME_STATE, resumeState);
        startActivity(intent);
    }
}
