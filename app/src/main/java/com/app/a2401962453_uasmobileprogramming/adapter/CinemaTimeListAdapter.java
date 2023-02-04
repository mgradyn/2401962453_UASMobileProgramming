package com.app.a2401962453_uasmobileprogramming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.list.DefaultList;
import com.app.a2401962453_uasmobileprogramming.model.CinemaTime;
import com.app.a2401962453_uasmobileprogramming.tool.OnTimeCardClickListener;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CinemaTimeListAdapter extends RecyclerView.Adapter<CinemaTimeListAdapter.CinemaTimelistViewHolder>{

    private Context context;
    private List<CinemaTime> cinemaTimeList;
    private int lastCheckedPosition = -1;
    OnTimeCardClickListener listener;

    public CinemaTimeListAdapter(Context context, OnTimeCardClickListener listener) {
        this.context = context;
        this.cinemaTimeList = DefaultList.cinemaTimeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CinemaTimeListAdapter.CinemaTimelistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cinema_time, parent, false);
        view.setClipToOutline(true);
        return new CinemaTimeListAdapter.CinemaTimelistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaTimeListAdapter.CinemaTimelistViewHolder holder, int position) {
        holder.tv_time.setText(cinemaTimeList.get(position).getTime());
        holder.c_time.setChecked(position == lastCheckedPosition);
    }


    @Override
    public int getItemCount() {
        return cinemaTimeList.size();
    }

    public class CinemaTimelistViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        MaterialCardView c_time;

        public CinemaTimelistViewHolder(@NonNull View itemView) {
            super(itemView);
            c_time = itemView.findViewById(R.id.c_time);
            tv_time = itemView.findViewById(R.id.tv_time);
            c_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int copyOfLastCheckedPosition = lastCheckedPosition;
                    lastCheckedPosition = getAdapterPosition();
                    listener.onCardClick(
                            cinemaTimeList.get(getAdapterPosition()).getTime(),
                            cinemaTimeList.get(getAdapterPosition()).getHour(),
                            cinemaTimeList.get(getAdapterPosition()).getMinute());
                    notifyItemChanged(copyOfLastCheckedPosition);
                    notifyItemChanged(lastCheckedPosition);
                }
            });

        }
    }

}
