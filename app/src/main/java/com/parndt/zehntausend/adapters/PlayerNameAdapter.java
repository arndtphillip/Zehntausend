package com.parndt.zehntausend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(@NonNull PlayerNameViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(players.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class PlayerNameViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public PlayerNameViewHolder(final View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.playerName);
        }
    }

}
