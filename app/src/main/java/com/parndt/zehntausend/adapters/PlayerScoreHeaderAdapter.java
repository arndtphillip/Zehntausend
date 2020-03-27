package com.parndt.zehntausend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.PlayerScore;

import java.util.List;

public class PlayerScoreHeaderAdapter extends RecyclerView.Adapter<PlayerScoreHeaderAdapter.PlayerScoreHeaderViewHolder> {

    private List<PlayerScore> players;

    public PlayerScoreHeaderAdapter(List<PlayerScore> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerScoreHeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_score_header, parent, false);

        return new PlayerScoreHeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerScoreHeaderViewHolder holder, int position) {
        holder.nameText.setText(players.get(position).getPlayer().getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class PlayerScoreHeaderViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView nameText;

        PlayerScoreHeaderViewHolder(final View v) {
            super(v);
            nameText = (TextView) v.findViewById(R.id.playerNameHeader);
        }
    }
}
