package com.app.a2401962453_uasmobileprogramming.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.model.Result;
import com.app.a2401962453_uasmobileprogramming.model.Ticket;

import java.util.List;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.TicketListViewHolder> {

    private Context context;
    private List<Ticket> ticketList;
    private Bundle bundle;

    public TicketListAdapter(Context context, List<Ticket> ticketList) {
        this.context = context;
        this.ticketList = ticketList;
        bundle = new Bundle();
    }

    @NonNull
    @Override
    public TicketListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ticket, parent, false);
        view.setClipToOutline(true);
        return new TicketListAdapter.TicketListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketListAdapter.TicketListViewHolder holder, int position) {
        holder.tv_movieName.setText(ticketList.get(position).getMovieName());
        holder.tv_cinema_value.setText(ticketList.get(position).getCinemaLocation());
        holder.tv_time_value.setText(ticketList.get(position).getTime());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               bundle.putParcelable("ticketDetail", ticketList.get(holder.getAdapterPosition()));
               Navigation.findNavController(view).navigate(R.id.action_ticketListFragment_to_ticketFragment, bundle);
           }
       }
        );
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public class TicketListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_movieName, tv_cinema_value, tv_time_value;
        FrameLayout parentLayout;

        public TicketListViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_movieName = itemView.findViewById(R.id.tv_movieName);
            tv_cinema_value = itemView.findViewById(R.id.tv_cinema_value);
            tv_time_value = itemView.findViewById(R.id.tv_time_value);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }


    }
}
