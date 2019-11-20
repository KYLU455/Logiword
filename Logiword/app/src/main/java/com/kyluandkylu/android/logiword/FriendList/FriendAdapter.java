package com.kyluandkylu.android.logiword.FriendList;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyluandkylu.android.logiword.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {
    private List<FriendModel> friends = new ArrayList<>();

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_item, parent, false);
        return new FriendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        FriendModel currentFriend = friends.get(position);
        holder.textViewName.setText(currentFriend.getName());
        holder.textViewScore.setText(currentFriend.scoreToString());
        holder.textViewStatus.setText(currentFriend.getStatus());

        if(currentFriend.getStatus().equalsIgnoreCase("online")){
            holder.textViewStatus.setTextColor(Color.GREEN);
        }
        else {
            holder.textViewStatus.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void setFriends(List<FriendModel> friends){
        this.friends = friends;
        notifyDataSetChanged();

    }

    class FriendHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewScore;
        private TextView textViewStatus;

        public FriendHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.friend_view_name);
            textViewScore = itemView.findViewById(R.id.friend_view_score);
            textViewStatus = itemView.findViewById(R.id.friend_view_status);
        }
    }
}
