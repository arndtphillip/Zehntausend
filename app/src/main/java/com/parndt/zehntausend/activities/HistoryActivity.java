package com.parndt.zehntausend.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.adapters.HistoryAdapter;
import com.parndt.zehntausend.data.HistoryReader;
import com.parndt.zehntausend.data.HistoryWriter;
import com.parndt.zehntausend.model.GameState;
import com.parndt.zehntausend.model.History;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.historyToolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        history = new HistoryReader().read(getApplicationContext());

        History historyNew = new History();

        for (GameState state : history.getGames()) {
            if (state.duration() > 10) {
                historyNew.add(state);
            }
        }

        new HistoryWriter(historyNew).write(getApplicationContext());

        RecyclerView historyView = (RecyclerView) findViewById(R.id.historyView);
        historyView.setLayoutManager(new GridLayoutManager(this, 1));
        RecyclerView.Adapter historyAdapter = new HistoryAdapter(history, toolbar);
        historyView.setAdapter(historyAdapter);
    }
}
