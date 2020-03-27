package com.parndt.zehntausend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.PlayerScore;

import org.w3c.dom.Text;

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
        PlayerScore player = players.get(position % players.size());
        int points = player.getPoints().get((int)Math.floor(position / (double)players.size()));

        holder.scoreText.setText(String.format("%d", points));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (PlayerScore player : players) {
            count += player.getPoints().size();
        }
        return count;
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
