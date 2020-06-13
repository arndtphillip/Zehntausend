package com.parndt.zehntausend.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.activities.HistoryDetailActivity;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.GameState;
import com.parndt.zehntausend.model.History;
import com.parndt.zehntausend.model.PlayerScore;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private History history;
    private View toolbar;

    public HistoryAdapter(History history, Toolbar toolbar) {
        this.history = history;
        this.toolbar = toolbar;
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
        int currentGame = history.getGames().size() - 1 - position;
        final GameState game = history.getGames().get(currentGame);
        final PlayerScore winner = game.getWinners().get(0);

        holder.winnerText.setText(winner.getPlayer().getName());
        holder.dateText.setText(game.getDateShort());
        holder.durationText.setText(game.getDuration());
        holder.pointsText.setText(String.format("%s", winner.getPointsSum()));

        // launch chart activity onclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, HistoryDetailActivity.class);
                intent.putExtra(Constants.GAME_STATE, game);
                //ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, toolbar,"historyActionBar");
                //context.startActivity(intent, activityOptionsCompat.toBundle());
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
        TextView dateText, winnerText, durationText, pointsText;

        HistoryViewHolder(final View v) {
            super(v);
            dateText = (TextView) v.findViewById(R.id.dateText);
            winnerText = (TextView) v.findViewById(R.id.winnerText);
            durationText = (TextView) v.findViewById(R.id.durationText);
            pointsText = (TextView) v.findViewById(R.id.historyPointsText);
        }
    }
}
