package com.parndt.zehntausend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.Player;

import java.util.List;

public class PlayerNameAdapter extends RecyclerView.Adapter<PlayerNameAdapter.PlayerNameViewHolder> {

    private List<Player> players;

    public PlayerNameAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list, parent, false);

        return new PlayerNameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerNameViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Player player = players.get(position);

        holder.playerIdText.setText(String.format("%d", position + 1));
        holder.playerNameText.setText(player.getName());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                players.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class PlayerNameViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView playerIdText;
        TextView playerNameText;
        Button deleteButton;

        PlayerNameViewHolder(final View v) {
            super(v);
            playerIdText = (TextView) v.findViewById(R.id.playerId);
            playerNameText = (TextView) v.findViewById(R.id.playerName);
            deleteButton = (Button) v.findViewById(R.id.deletePlayerButton);
        }
    }

}
