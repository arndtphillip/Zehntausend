package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.GameState;

public class GameEndActivity extends AppCompatActivity {

    private GameState game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        game = (GameState) getIntent().getSerializableExtra(Constants.GAME_STATE);

        TextView winnerText = findViewById(R.id.textWinner);

        winnerText.setText(game.getWinner().getName() + " won!");

        Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate_top_to_bottom);
        findViewById(R.id.winnerCard).startAnimation(translate);

        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        // findViewById(R.id.rotator).startAnimation(rotate);
        findViewById(R.id.rotating_icon1).startAnimation(rotate);
        findViewById(R.id.rotating_icon2).startAnimation(rotate);
        findViewById(R.id.rotating_icon3).startAnimation(rotate);
    }

    public void graph(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        intent.putExtra(Constants.GAME_STATE, game);
        startActivity(intent);
    }

    public void end(View view) {
        finish();
    }
}
