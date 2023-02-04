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
import com.app.a2401962453_uasmobileprogramming.model.CinemaDate;
import com.app.a2401962453_uasmobileprogramming.tool.OnDateCardClickListener;
import com.app.a2401962453_uasmobileprogramming.tool.OnRoomCardClickListener;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CinemaDateListAdapter extends RecyclerView.Adapter<CinemaDateListAdapter.CinemaDatelistViewHolder> {

    private Context context;
    private List<CinemaDate> cinemaDateList;
    private int lastCheckedPosition = -1;
    OnDateCardClickListener listener;

    public CinemaDateListAdapter(Context context, OnDateCardClickListener listener) {
        this.context = context;
        this.cinemaDateList = DefaultList.cinemaDateList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CinemaDateListAdapter.CinemaDatelistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cinema_date, parent, false);
        view.setClipToOutline(true);
        return new CinemaDateListAdapter.CinemaDatelistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaDateListAdapter.CinemaDatelistViewHolder holder, int position) {
        holder.tv_date.setText(cinemaDateList.get(position).getDate());
        holder.tv_day.setText(cinemaDateList.get(position).getDay());
        holder.c_date.setChecked(position == lastCheckedPosition);
    }


    @Override
    public int getItemCount() {
        return cinemaDateList.size();
    }

    public class CinemaDatelistViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date, tv_day;
        MaterialCardView c_date;

        public CinemaDatelistViewHolder(@NonNull View itemView) {
            super(itemView);
            c_date = itemView.findViewById(R.id.c_date);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_day = itemView.findViewById(R.id.tv_day);
            c_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int copyOfLastCheckedPosition = lastCheckedPosition;
                    lastCheckedPosition = getAdapterPosition();
                    listener.onCardClick(
                            cinemaDateList.get(getAdapterPosition()).getFullDate(),
                            cinemaDateList.get(getAdapterPosition()).getDay(),
                            cinemaDateList.get(getAdapterPosition()).getDate());
                    notifyItemChanged(copyOfLastCheckedPosition);
                    notifyItemChanged(lastCheckedPosition);
                }
            });

        }
    }
}
