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

public class PlayerHeaderAdapter extends RecyclerView.Adapter<PlayerHeaderAdapter.PlayerHeaderViewHolder> {

    private List<PlayerScore> players;

    public PlayerHeaderAdapter(List<PlayerScore> players) { this.players = players; }

    @NonNull
    @Override
    public PlayerHeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_score_header, parent, false);

        return new PlayerHeaderAdapter.PlayerHeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHeaderViewHolder holder, int position) {
        String name = players.get(position).getPlayer().getName();

        holder.nameText.setTypeface(null, Typeface.BOLD);
        holder.nameText.setText(name);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class PlayerHeaderViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView nameText;

        PlayerHeaderViewHolder(final View v) {
            super(v);
            nameText = (TextView) v.findViewById(R.id.playerName);
        }
    }
}
