package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        ArrayList<Parcelable> parcelables = extras.getParcelableArrayList(NewGameActivity.EXTRA_PLAYERS);
        if (parcelables != null) {
            for (Parcelable p : parcelables) {
                if (p instanceof Player) {
                    players.add((Player) p);
                }
            }
        }
    }
}
