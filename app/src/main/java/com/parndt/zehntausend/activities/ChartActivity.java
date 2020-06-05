package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.parndt.zehntausend.Zehntausend;
import com.parndt.zehntausend.adapters.ChartAdapter;
import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.GameState;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        GameState state = Zehntausend.gameState;
        LineChart chart = findViewById(R.id.pointsChart);

        new ChartAdapter(state, chart, getApplicationContext()).adapt();
    }

    public void finish(View view) {
        finish();
    }
}
