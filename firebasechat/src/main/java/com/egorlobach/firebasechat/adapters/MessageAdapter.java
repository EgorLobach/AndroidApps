package com.egorlobach.firebasechat.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egorlobach.firebasechat.R;
import com.egorlobach.firebasechat.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private Context mContext;
    private List<Chat> mChat;
    private String imageURL;



    FirebaseUser firebaseUser;


    public MessageAdapter(Context mContext, List<Chat> mChat, String imageURL) {
        this.mContext = mContext;
        this.mChat = mChat;
        this.imageURL = imageURL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_chat_right, viewGroup, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_chat_left, viewGroup, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Chat chat = mChat.get(i);

        viewHolder.message.setText(chat.getMessage());

        if(imageURL.equals("default"))
            viewHolder.profileImage.setImageResource(R.mipmap.ic_launcher);
        else Glide.with(mContext).load(imageURL).into(viewHolder.profileImage);

        if(i == mChat.size()-1){
            if(chat.isIsseen()){
                viewHolder.textSeen.setText("Seen");
            } else {
                viewHolder.textSeen.setText("Delivered");
            }
        } else {
            viewHolder.textSeen.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(firebaseUser.getUid()))
            return MSG_TYPE_RIGHT;
        else return MSG_TYPE_LEFT;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView message;
        public ImageView profileImage;
        public TextView textSeen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message);
            profileImage = itemView.findViewById(R.id.profileImage);
            textSeen = itemView.findViewById(R.id.textSeen);
        }
    }
}
