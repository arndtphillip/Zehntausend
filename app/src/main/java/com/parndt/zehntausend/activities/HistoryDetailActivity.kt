package com.parndt.zehntausend.activities

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.mikephil.charting.charts.LineChart
import com.parndt.zehntausend.R
import com.parndt.zehntausend.adapters.ChartAdapter
import com.parndt.zehntausend.model.Constants
import com.parndt.zehntausend.model.GameState

class HistoryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)

        setSupportActionBar(findViewById<View>(R.id.historyDetailToolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val state = intent.getSerializableExtra(Constants.GAME_STATE) as GameState
        val chart = findViewById<LineChart>(R.id.pointsChart)

        ChartAdapter(state, chart, applicationContext).adapt()

        setTexts(state)
    }

    fun setTexts(state: GameState) {
        findViewById<TextView>(R.id.historyDetailDate).text = state.date
        findViewById<TextView>(R.id.historyDetailDuration).text = state.duration

        val winners = state.winners

        findViewById<TextView>(R.id.winnerName).text = winners[0].player.name
        findViewById<TextView>(R.id.winnerPoints).text = winners[0].pointsSum.toString()

        findViewById<TextView>(R.id.secondName).text = winners[1].player.name
        findViewById<TextView>(R.id.secondPoints).text = winners[1].pointsSum.toString()

        if (winners.size > 2) {
            findViewById<TextView>(R.id.thirdName).text = winners[2].player.name
            findViewById<TextView>(R.id.thirdPoints).text = winners[2].pointsSum.toString()
        } else {
            findViewById<RelativeLayout>(R.id.thirdContainer).visibility = View.GONE
        }
    }
}