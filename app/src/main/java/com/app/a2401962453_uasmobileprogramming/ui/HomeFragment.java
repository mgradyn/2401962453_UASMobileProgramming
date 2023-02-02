package com.app.a2401962453_uasmobileprogramming.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.a2401962453_uasmobileprogramming.MainActivity;
import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.adapter.MovieListAdapter;
import com.app.a2401962453_uasmobileprogramming.config.NetworkConfig;
import com.app.a2401962453_uasmobileprogramming.databinding.ActivityMainBinding;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentHomeBinding;
import com.app.a2401962453_uasmobileprogramming.model.Result;
import com.app.a2401962453_uasmobileprogramming.model.Response;
import com.app.a2401962453_uasmobileprogramming.network.MovieApi;
import com.app.a2401962453_uasmobileprogramming.network.MovieApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private MovieListAdapter adapter;
    List<Result> movieList = new ArrayList<>();
    private String LANGUAGE = "en-US";
    private String CATEGORY = "now_playing";
    private String REGION = "ID";
    int PAGE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getMovies();
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.homeRecyclerView.setLayoutManager(new GridLayoutManager((MainActivity)getActivity(), 2));
        binding.homeRecyclerView.setHasFixedSize(true);

        super.onViewCreated(view, savedInstanceState);
    }

    private void getMovies() {
        MovieApiInterface apiInterface = MovieApi.getClient().create(MovieApiInterface.class);
        Call<Response> callAsync = apiInterface.getMovie(CATEGORY, NetworkConfig.API_KEY, LANGUAGE, PAGE, REGION);
        callAsync .enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    movieList = response.body().getResults();
                    adapter = new MovieListAdapter((MainActivity)getActivity(), movieList);
                    binding.homeRecyclerView.setAdapter(adapter);
                }
                else {
                    Log.w("exception", "Movie List Exception");
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.fillInStackTrace();

            }
        });
    }
}