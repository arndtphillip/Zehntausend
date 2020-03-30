package com.parndt.zehntausend.adapters;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.PlayerScore;

import java.util.List;

public class PlayerScoreAdapter extends RecyclerView.Adapter<PlayerScoreAdapter.PlayerScoreViewHolder> {

    private List<PlayerScore> players;

    public PlayerScoreAdapter(List<PlayerScore> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_score_list, parent, false);

        return new PlayerScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerScoreViewHolder holder, int position) {
        if (position < players.size()) {
            renderHeader(holder, position);
        } else {
            position -= players.size();
            int turnCount = players.get(0).getPoints().size() * players.size();

            if (position < turnCount) {
                renderScore(holder, position);
            } else {
                position -= turnCount;
                renderScoreSum(holder, position);
            }
        }
    }

    private void renderHeader(@NonNull PlayerScoreViewHolder holder, int position) {
        String name = players.get(position).getPlayer().getName();

        holder.scoreText.setText(name);
        holder.scoreText.setTypeface(null, Typeface.BOLD);
    }

    private void renderScore(@NonNull PlayerScoreViewHolder holder, int position) {
        PlayerScore player = players.get(position % players.size());
        int playerPointsPosition = (int) Math.floor(position / (double) players.size());
        List<Integer> playerPoints = player.getPoints();

        if (playerPointsPosition < playerPoints.size()) {
            // player points available
            int points = playerPoints.get(playerPointsPosition);

            if (points != 0) {
                holder.scoreText.setText(String.format("%d", points));
            } else {
                holder.scoreText.setText(" - ");
            }
        } else {
            // no points in this round yet
            holder.scoreText.setText("");
        }

        holder.scoreText.setTypeface(null);
    }

    private void renderScoreSum(@NonNull PlayerScoreViewHolder holder, int position) {
        int sum = players.get(position).getPointsSum();
        holder.scoreText.setText(String.format("%d", sum));
        holder.scoreText.setTypeface(null, Typeface.BOLD);
    }

    @Override
    public int getItemCount() {
        int playerCount = players.size();
        int playerTurnCount = players.get(0).getPoints().size();
        int playerSumCount = players.size();

        return playerCount + playerTurnCount * players.size() + playerSumCount;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class PlayerScoreViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView scoreText;

        PlayerScoreViewHolder(final View v) {
            super(v);
            scoreText = (TextView) v.findViewById(R.id.playerScore);
        }
    }
}
