package com.parndt.zehntausend.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.adapters.HistoryAdapter;
import com.parndt.zehntausend.data.HistoryReader;
import com.parndt.zehntausend.model.History;

public class HistoryActivity extends AppCompatActivity {

    private History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setSupportActionBar((Toolbar) findViewById(R.id.historyToolbar));
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        history = new HistoryReader().read(getApplicationContext());

        RecyclerView historyView = (RecyclerView) findViewById(R.id.historyView);
        historyView.setLayoutManager(new GridLayoutManager(this, 1));
        RecyclerView.Adapter historyAdapter = new HistoryAdapter(history);
        historyView.setAdapter(historyAdapter);
    }
}
