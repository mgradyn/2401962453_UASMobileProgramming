package com.app.a2401962453_uasmobileprogramming.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.database.AppDatabase;
import com.app.a2401962453_uasmobileprogramming.database.DBReceiver;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentTicketBinding;
import com.app.a2401962453_uasmobileprogramming.model.Ticket;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class TicketFragment extends Fragment {
    private FragmentTicketBinding binding;
    BottomNavigationView bottomNavigationView;
    private Ticket ticket;

    private DBReceiver dbreceiver;
    private AppDatabase db;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dbreceiver = (DBReceiver) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        binding.btnOk.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_ticketFragment_to_ticketListFragment)
        );
        if (ticket != null) {
            binding.tvMovieName.setText(ticket.getMovieName());
            binding.tvDateValue.setText(ticket.getFullDate());
            binding.tvCinemaValue.setText(ticket.getCinemaLocation());
            binding.tvTimeValue.setText(ticket.getTime());
            binding.tvTicketCodeValue.setText(ticket.getUid().substring(0,8));
            binding.tvRoomTypeValue.setText(ticket.getRoomType());
            binding.tvPriceValue.setText(ticket.getRoomPrice());
        }

        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = dbreceiver.getDB();

        if (getArguments() != null) {
            ticket = getArguments().getParcelable("ticketDetail");
        }
        else {
            ticket = null;
        }

        // Inflate the layout for this fragment
        binding = FragmentTicketBinding.inflate(getLayoutInflater());
        return binding.getRoot();
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