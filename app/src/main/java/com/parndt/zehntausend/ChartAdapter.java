package com.parndt.zehntausend;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.parndt.zehntausend.model.GameState;
import com.parndt.zehntausend.model.PlayerScore;

import java.util.ArrayList;
import java.util.List;

public class ChartAdapter {

    private Integer[] colors = new Integer[] {
            R.color.colorChart1,
            R.color.colorChart2,
            R.color.colorChart3,
            R.color.colorChart4,
            R.color.colorChart5
    };

    private GameState state;
    private LineChart chart;
    private Context context;

    public ChartAdapter(GameState state, LineChart chart, Context context) {
        this.state = state;
        this.chart = chart;
        this.context = context;
    }

    public void adapt() {
        LineData lineData = new LineData();

        List<PlayerScore> players = state.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            PlayerScore player = players.get(i);
            int index = i % colors.length;
            Integer color = colors[i % colors.length];

            lineData.addDataSet(getData(player, color));
        }

        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setDrawBorders(true);

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

        Legend legend = chart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);

        chart.setData(lineData);
        chart.invalidate();
    }

    private LineDataSet getData(PlayerScore player, Integer color) {
        List<Entry> entries = new ArrayList<>();

        int totalPoints = 0;
        entries.add(new Entry(0, totalPoints));
        List<Integer> points = player.getPoints();

        for (int i = 0; i < points.size(); i++) {
            totalPoints += points.get(i);
            entries.add(new Entry(i + 1, totalPoints));
        }

        LineDataSet dataSet = new LineDataSet(entries, player.getPlayer().getName());

        dataSet.setColor(ContextCompat.getColor(context, color));
        dataSet.setDrawVerticalHighlightIndicator(false);
        dataSet.setDrawHorizontalHighlightIndicator(false);
        dataSet.setDrawCircles(false);
        dataSet.setLineWidth(2f);

        return dataSet;
    }
}
