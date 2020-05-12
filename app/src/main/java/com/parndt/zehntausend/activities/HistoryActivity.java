package com.parndt.zehntausend.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.adapters.HistoryAdapter;
import com.parndt.zehntausend.adapters.PlayerHeaderAdapter;
import com.parndt.zehntausend.data.HistoryReader;
import com.parndt.zehntausend.model.History;

public class HistoryActivity extends AppCompatActivity {

    private History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history = new HistoryReader().read(getApplicationContext());

        RecyclerView historyView = (RecyclerView) findViewById(R.id.historyView);
        historyView.setLayoutManager(new GridLayoutManager(this, 1));
        RecyclerView.Adapter historyAdapter = new HistoryAdapter(history);
        historyView.setAdapter(historyAdapter);
    }
}