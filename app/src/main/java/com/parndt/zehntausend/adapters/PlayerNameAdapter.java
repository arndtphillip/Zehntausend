package com.parndt.zehntausend.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parndt.zehntausend.R;
import com.parndt.zehntausend.model.Player;

import java.util.List;

public class PlayerNameAdapter extends RecyclerView.Adapter<PlayerNameAdapter.PlayerNameViewHolder> {

    private List<Player> players;
    private Context context;

    public PlayerNameAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list, parent, false);

        return new PlayerNameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerNameViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Player player = players.get(position);
        holder.playerNameText.setTag(player);

        if (holder.watcher == null) {
            holder.watcher = new PlayerNameTextWatcher(holder.playerNameText);
            holder.playerNameText.addTextChangedListener(holder.watcher);
        }

        holder.watcher.active = false;

        holder.playerIdText.setText(String.format("%d", position + 1));
        holder.playerNameText.setText(player.getName());

        String hint = context.getString(R.string.player) + " " + (position + 1);
        holder.playerNameText.setHint(hint);

        if (players.size() > 2) {
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    players.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else {
            holder.deleteButton.setVisibility(View.GONE);
        }

        holder.watcher.active = true;
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
        EditText playerNameText;
        ImageButton deleteButton;

        PlayerNameTextWatcher watcher = null;

        PlayerNameViewHolder(final View v) {
            super(v);
            playerIdText = v.findViewById(R.id.playerId);
            playerNameText = v.findViewById(R.id.playerName);
            deleteButton = v.findViewById(R.id.deletePlayerButton);
        }
    }

    static class PlayerNameTextWatcher implements TextWatcher {

        EditText editText;
        boolean active = false;

        PlayerNameTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = s.toString().trim();
            Player player = (Player) editText.getTag();

            if (!input.equals(player.getName()) && active) {
                player.setName(input);
            }
        }
    }
}
