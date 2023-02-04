package com.app.a2401962453_uasmobileprogramming.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.list.DefaultList;
import com.app.a2401962453_uasmobileprogramming.model.CinemaRoom;
import com.app.a2401962453_uasmobileprogramming.tool.OnRoomCardClickListener;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.List;

public class CinemaRoomListAdapter extends RecyclerView.Adapter<CinemaRoomListAdapter.CinemaRoomListViewHolder> {

    private Context context;
    private List<CinemaRoom> cinemaRoomList;
    private int lastCheckedPosition = -1;
    OnRoomCardClickListener listener;

    public CinemaRoomListAdapter(Context context, OnRoomCardClickListener listener) {
        this.context = context;
        this.cinemaRoomList = DefaultList.cinemaRoomList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public CinemaRoomListAdapter.CinemaRoomListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cinema_location, parent, false);
        view.setClipToOutline(true);
        return new CinemaRoomListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaRoomListAdapter.CinemaRoomListViewHolder holder, int position) {
        holder.tv_room.setText(cinemaRoomList.get(position).getName());
        int id = context.getResources().getIdentifier("drawable/"+ cinemaRoomList.get(position).getImage(), null, context.getPackageName());
        holder.iv_roomImage.setImageResource(id);
        holder.c_room.setChecked(position == lastCheckedPosition);
    }

    @Override
    public int getItemCount() {
        return cinemaRoomList.size();
    }

    public class CinemaRoomListViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_roomImage;
        TextView tv_room;
        MaterialCardView c_room;

        public CinemaRoomListViewHolder(@NonNull View itemView) {
            super(itemView);
            c_room = itemView.findViewById(R.id.c_room);
            iv_roomImage = itemView.findViewById(R.id.iv_room_image);
            tv_room = itemView.findViewById(R.id.tv_room);
            c_room.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int copyOfLastCheckedPosition = lastCheckedPosition;
                    lastCheckedPosition = getAdapterPosition();
                    listener.onCardClick(cinemaRoomList.get(getAdapterPosition()).getName(), cinemaRoomList.get(getAdapterPosition()).getPrice());
                    notifyItemChanged(copyOfLastCheckedPosition);
                    notifyItemChanged(lastCheckedPosition);
                }
            });

        }
    }
}
