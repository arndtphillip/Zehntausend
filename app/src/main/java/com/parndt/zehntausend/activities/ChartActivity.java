package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.parndt.zehntausend.ChartAdapter;
import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.GameState;
import com.parndt.zehntausend.model.PlayerScore;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        GameState state = (GameState) getIntent().getSerializableExtra(Constants.GAME_STATE);
        LineChart chart = findViewById(R.id.pointsChart);

        new ChartAdapter(state, chart, getApplicationContext()).adapt();
    }
}
