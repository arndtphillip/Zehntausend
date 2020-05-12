package com.parndt.zehntausend.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.activities.ChartActivity;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.GameState;
import com.parndt.zehntausend.model.History;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private History history;

    public HistoryAdapter(History history) {
        this.history = history;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list, parent, false);



        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        final GameState game = history.getGames().get(position);

        holder.dateText.setText(game.getDate());
        holder.winnerText.setText(game.getWinner().getName());
        holder.durationText.setText(game.getDuration());

        // launch chart activity onclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ChartActivity.class);
                intent.putExtra(Constants.GAME_STATE, game);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return history.getGames().size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView dateText, winnerText, durationText;

        HistoryViewHolder(final View v) {
            super(v);
            dateText = (TextView) v.findViewById(R.id.dateText);
            winnerText = (TextView) v.findViewById(R.id.winnerText);
            durationText = (TextView) v.findViewById(R.id.durationText);
        }
    }
}
