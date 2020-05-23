package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.parndt.zehntausend.ChartAdapter;
import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.GameState;

public class HistoryDetailActivity extends AppCompatActivity {

    private GameState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        state = (GameState) getIntent().getSerializableExtra(Constants.GAME_STATE);
        LineChart chart = findViewById(R.id.pointsChart);

        new ChartAdapter(state, chart, getApplicationContext()).adapt();

        ((TextView) findViewById(R.id.winnerText)).setText("Winner: " + state.getWinner().getName());
    }
}
