package com.app.a2401962453_uasmobileprogramming.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.model.Result;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private Context context;
    private List<Result> movieList;
    private Bundle bundle;

    public MovieListAdapter(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;
        bundle = new Bundle();
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        view.setClipToOutline(true);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        holder.tv_titleName.setText(movieList.get(position).getTitle());
        Glide.with(context).
                load("https://image.tmdb.org/t/p/w185" + movieList.get(position).getPosterPath()).
                into(holder.iv_coverImage);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               bundle.putParcelable("movieResult", movieList.get(holder.getAdapterPosition()));
               Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailMovieFragment, bundle);
           }
       }
        );
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_coverImage;
        TextView tv_titleName;
        ConstraintLayout parentLayout;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_coverImage = itemView.findViewById(R.id.iv_coverImage);
            tv_titleName = itemView.findViewById(R.id.tv_titleName);
//            tv_description = itemView.findViewById(R.id.tv_description);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }


    }

}
