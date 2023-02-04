package com.app.a2401962453_uasmobileprogramming.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.databinding.ActivityMainBinding;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentDetailMovieBinding;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentHomeBinding;
import com.app.a2401962453_uasmobileprogramming.model.Result;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailMovieFragment extends Fragment {
    private FragmentDetailMovieBinding binding;
    BottomNavigationView bottomNavigationView;
    private Result movieDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            movieDetail = getArguments().getParcelable("movieResult");
        }
        else {
            movieDetail = null;
        }
        // Inflate the layout for this fragment
        binding = FragmentDetailMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        Glide.with(this).
                load("https://image.tmdb.org/t/p/w500" + movieDetail.getPosterPath()).
                into(binding.ivBackground);
        binding.tvTitle.setText(movieDetail.getTitle());
        binding.tvRating.setText(movieDetail.getVoteAverage().toString());
        binding.tvPopularityValue.setText(movieDetail.getPopularity().toString());
        binding.tvSynopsisDetail.setText(movieDetail.getOverview());
        binding.ivBack.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_detailMovieFragment_to_homeFragment)
        );
        binding.buttonBook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle bundle = new Bundle();
               bundle.putParcelable("movieDetail",movieDetail);
               Navigation.findNavController(view).navigate(R.id.action_detailMovieFragment_to_bookingFragment, bundle);
           }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        bottomNavigationView.setVisibility(View.GONE);
        super.onStart();
    }

    @Override
    public void onStop() {
        bottomNavigationView.setVisibility(View.VISIBLE);
        super.onStop();
    }
}