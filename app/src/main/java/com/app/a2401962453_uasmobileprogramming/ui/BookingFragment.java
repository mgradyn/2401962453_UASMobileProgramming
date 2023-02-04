package com.app.a2401962453_uasmobileprogramming.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.a2401962453_uasmobileprogramming.MainActivity;
import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.adapter.CinemaRoomListAdapter;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentBookingBinding;
import com.app.a2401962453_uasmobileprogramming.model.Result;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookingFragment extends Fragment {
    private FragmentBookingBinding binding;
    private Spinner locationSpinner;
    private Result movieDetail;
    BottomNavigationView bottomNavigationView;
    private CinemaRoomListAdapter cinemaRoomListAdapter;

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

        cinemaRoomListAdapter = new CinemaRoomListAdapter((MainActivity)getActivity());

        // Inflate the layout for this fragment
        binding = FragmentBookingBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        binding.tvMovieName.setText(movieDetail.getTitle());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager((MainActivity)getActivity(), LinearLayoutManager.HORIZONTAL, false);

        binding.cinemaRoomRecyclerView.setLayoutManager(layoutManager);
        binding.cinemaRoomRecyclerView.setHasFixedSize(true);
        binding.cinemaRoomRecyclerView.setAdapter(cinemaRoomListAdapter);

        setLocationSpinner();

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
}