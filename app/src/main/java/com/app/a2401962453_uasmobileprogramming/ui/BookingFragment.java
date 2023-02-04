package com.app.a2401962453_uasmobileprogramming.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.a2401962453_uasmobileprogramming.MainActivity;
import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.adapter.CinemaDateListAdapter;
import com.app.a2401962453_uasmobileprogramming.adapter.CinemaRoomListAdapter;
import com.app.a2401962453_uasmobileprogramming.adapter.CinemaTimeListAdapter;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentBookingBinding;
import com.app.a2401962453_uasmobileprogramming.model.Result;
import com.app.a2401962453_uasmobileprogramming.tool.OnDateCardClickListener;
import com.app.a2401962453_uasmobileprogramming.tool.OnRoomCardClickListener;
import com.app.a2401962453_uasmobileprogramming.tool.OnTimeCardClickListener;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class BookingFragment extends Fragment implements OnRoomCardClickListener, OnDateCardClickListener, OnTimeCardClickListener {
    private FragmentBookingBinding binding;
    private Spinner locationSpinner;
    private Result movieDetail;
    BottomNavigationView bottomNavigationView;
    private CinemaRoomListAdapter cinemaRoomListAdapter;
    private CinemaDateListAdapter cinemaDateListAdapter;
    private CinemaTimeListAdapter cinemaTimeListAdapter;

    private String roomType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            movieDetail = getArguments().getParcelable("movieDetail");
        }
        else {
            movieDetail = null;
        }

        cinemaRoomListAdapter = new CinemaRoomListAdapter((MainActivity)getActivity(), this);
        cinemaDateListAdapter = new CinemaDateListAdapter((MainActivity)getActivity(), this);
        cinemaTimeListAdapter = new CinemaTimeListAdapter((MainActivity)getActivity(), this);

        // Inflate the layout for this fragment
        binding = FragmentBookingBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        Glide.with(this).
                load("https://image.tmdb.org/t/p/w500" + movieDetail.getPosterPath()).
                into(binding.ivBackground);
        binding.tvMovieName.setText(movieDetail.getTitle());
        setLocationSpinner();

        setCinemaRoomAdapter();
        setCinemaDateAdapter();
        setCinemaTimeAdapter();

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

    private void setLocationSpinner() {
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.cinema_locations,
                android.R.layout.simple_spinner_item
        );
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sCinemaLocation.setAdapter(locationAdapter);
        binding.sCinemaLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setCinemaRoomAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager((MainActivity)getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.cinemaRoomRecyclerView.setLayoutManager(layoutManager);
        binding.cinemaRoomRecyclerView.setHasFixedSize(true);
        binding.cinemaRoomRecyclerView.setAdapter(cinemaRoomListAdapter);
    }

    private void setCinemaDateAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager((MainActivity)getActivity(), LinearLayoutManager.HORIZONTAL, false);

        binding.cinemaDateRecyclerView.setLayoutManager(layoutManager);
        binding.cinemaDateRecyclerView.setHasFixedSize(true);
        binding.cinemaDateRecyclerView.setAdapter(cinemaDateListAdapter);
    }

    private void setCinemaTimeAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        binding.cinemaTimeRecyclerView.setLayoutManager(layoutManager);
        binding.cinemaTimeRecyclerView.setHasFixedSize(true);
        binding.cinemaTimeRecyclerView.setAdapter(cinemaTimeListAdapter);
    }

    @Override
    public void onCardClick(String type) {
        Log.d("roomType", type);
        this.roomType = type;
    }

    @Override
    public void onCardClick(Date fullDate, String day, String date) {
        Log.d("cinemaDate", date);
        Log.d("cinemaDay", day);
        Log.d("cinemaFullDate", fullDate.toString());
    }

    @Override
    public void onCardClick(String time, int hour, int minute) {
        Log.d("cinemaTime", time);
        Log.d("cinemaHour", String.valueOf(hour));
        Log.d("cinemaMinute", String.valueOf(minute));

    }
}