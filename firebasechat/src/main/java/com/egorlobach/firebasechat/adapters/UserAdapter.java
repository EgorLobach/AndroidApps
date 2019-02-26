package com.egorlobach.firebasechat.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egorlobach.firebasechat.R;
import com.egorlobach.firebasechat.activities.MessageActivity;
import com.egorlobach.firebasechat.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;
    private boolean ischat;

    public UserAdapter(Context mContext, List<User> mUsers, boolean ischat) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final User user = mUsers.get(i);
        viewHolder.username.setText(user.getUsername());
        if(user.getImageURL().equals("default"))
            viewHolder.profileImage.setImageResource(R.mipmap.ic_launcher);
        else Glide.with(mContext).load(user.getImageURL()).into(viewHolder.profileImage);

        if(ischat){
            if(user.getStatus().equals("online")){
                viewHolder.onlineImage.setVisibility(View.VISIBLE);
                viewHolder.offlineImage.setVisibility(View.GONE);
            } else {
                viewHolder.onlineImage.setVisibility(View.GONE);
                viewHolder.offlineImage.setVisibility(View.VISIBLE);
            }
        } else {
            viewHolder.onlineImage.setVisibility(View.GONE);
            viewHolder.offlineImage.setVisibility(View.GONE);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public ImageView profileImage;
        private ImageView onlineImage;
        private ImageView offlineImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profileImage = itemView.findViewById(R.id.profileImage);
            onlineImage = itemView.findViewById(R.id.onlineImage);
            offlineImage = itemView.findViewById(R.id.offlineImage);
        }
    }
}
