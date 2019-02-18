package com.egorlobach.firebasechat.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.egorlobach.firebasechat.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView username;
    public ImageView profileImage;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        username = itemView.findViewById(R.id.username);
        profileImage = itemView.findViewById(R.id.profileImage);
    }
}
